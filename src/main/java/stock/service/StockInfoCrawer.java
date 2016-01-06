package stock.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import stock.StockConstant;
import stock.db.entity.Capital;
import stock.db.entity.Earnings;
import stock.db.entity.Performance;
import stock.db.entity.Price;
import stock.db.entity.Stock;
import stock.db.entity.StockDateId;
import stock.db.entity.StockMonthId;
import stock.db.entity.StockQuarterId;
import stock.db.entity.StockYearId;
import stock.db.repo.CapitalRepo;
import stock.db.repo.EarningsRepo;
import stock.db.repo.PerformanceRepo;
import stock.db.repo.PriceRepo;
import stock.db.repo.StockRepo;

@Service
public class StockInfoCrawer {

  @Inject
  private PriceRepo priceRepo;
  @Inject
  private StockRepo stockRepo;
  @Inject
  private EarningsRepo earningsRepo;
  @Inject
  private PerformanceRepo performanceRepo;
  @Inject
  private CapitalRepo capitalRepo;

  @Scheduled(fixedDelay = 60 * 60 * 1000 * 24)
  public void parseData() {
    List<Integer> stockNums = stockRepo.getNumbers();
    // parsePerformance(UrlPattern.PERFORMANCE.getUrl(stockNums));
    parseEarnings(UrlPattern.EARNINGS.getUrl(stockNums));
    parseCapital(UrlPattern.CAPITAL.getUrl(stockNums));
    parsePrice(UrlPattern.PRICE.getUrl(stockNums));
    // parseStockNumber("http://isin.twse.com.tw/isin/C_public.jsp?strMode=2");
  }

  public void parsePerformance(Map<Integer, String> urls) {
    ThreadPoolExecutor tpe =
        new ThreadPoolExecutor(1, 2, 6, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
    System.out.println("Start performance parsing");
    for (Map.Entry<Integer, String> entity : urls.entrySet()) {
      tpe.execute(new Runnable() {

        @Override
        public void run() {
          parsePerformance(entity.getKey(), entity.getValue());
        }
      });
    }
    try {
      tpe.shutdown();
      while (true) {
        if (tpe.awaitTermination(1, TimeUnit.SECONDS)) {
          System.out.println("Job all done!");
          break;
        }
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("End performance parsing");
  }

  public void parseEarnings(Map<Integer, String> urls) {
    ThreadPoolExecutor tpe =
        new ThreadPoolExecutor(1, 2, 6, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
    for (Map.Entry<Integer, String> entity : urls.entrySet()) {
      tpe.execute(new Runnable() {

        @Override
        public void run() {
          parseEarnings(entity.getKey(), entity.getValue());
        }
      });
    }
    try {
      tpe.shutdown();
      while (true) {
        if (tpe.awaitTermination(1, TimeUnit.SECONDS)) {
          System.out.println("Job all done!");
          break;
        }
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void parseCapital(Map<Integer, String> urls) {
    ThreadPoolExecutor tpe =
        new ThreadPoolExecutor(1, 2, 6, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
    for (Map.Entry<Integer, String> entity : urls.entrySet()) {
      tpe.execute(new Runnable() {

        @Override
        public void run() {
          parseCapital(entity.getKey(), entity.getValue());
        }
      });
    }
    try {
      tpe.shutdown();
      while (true) {
        if (tpe.awaitTermination(1, TimeUnit.SECONDS)) {
          System.out.println("Job all done!");
          break;
        }
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void parsePrice(Map<Integer, String> urls) {
    ThreadPoolExecutor tpe =
        new ThreadPoolExecutor(1, 2, 6, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
    for (Map.Entry<Integer, String> entity : urls.entrySet()) {
      tpe.execute(new Runnable() {

        @Override
        public void run() {
          parsePrice(entity.getKey(), entity.getValue());
        }
      });
    }
    try {
      tpe.shutdown();
      while (true) {
        if (tpe.awaitTermination(1, TimeUnit.SECONDS)) {
          System.out.println("Job all done!");
          break;
        }
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void parsePerformance(Integer stockNum, String url) {
    List<Performance> entities = new ArrayList<>();
    try {
      Document doc = Jsoup.connect(url).timeout(10000).get();
      Elements elements = doc.getElementsByTag("table").get(2).getElementsByTag("tr");
      for (Element element : elements) {
        if (!element.attr("id").contains("oScroll")) {
          Elements tds = element.getElementsByTag("td");
          Performance entity = new Performance();
          StockQuarterId id = new StockQuarterId();
          entity.setId(id);
          String[] yearQuarter = tds.get(0).text().split("\\.");
          id.setYear(removeWebSpaceInteger(yearQuarter[0]));
          id.setQuarter(removeWebSpaceInteger(yearQuarter[1].substring(0, 1)));
          id.setNumber(stockNum);
          entity.setRps(removeWebSpaceDouble(tds.get(5).text()));
          entity.setEps(removeWebSpaceDouble(tds.get(7).text()));
          entities.add(entity);
        }
      }
      performanceRepo.save(entities);
    } catch (IOException e) {
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
      parsePerformance(stockNum, url);
    } catch (Exception e) {
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
      parsePerformance(stockNum, url);
    }
  }

  public void parseEarnings(Integer stockNum, String url) {
    List<Earnings> entities = new ArrayList<>();
    try {
      Document doc = Jsoup.connect(url).timeout(10000).get();
      Elements elements = doc.getElementsByTag("table").get(2).getElementsByTag("tr");
      for (Element element : elements) {
        if (!element.attr("id").contains("oScroll")) {
          Elements tds = element.getElementsByTag("td");
          Earnings entity = new Earnings();
          StockMonthId id = new StockMonthId();
          entity.setId(id);
          String[] yearMonth = tds.get(0).text().split("/");
          id.setYear(removeWebSpaceInteger(yearMonth[0]));
          id.setMonth(removeWebSpaceInteger(yearMonth[1]));
          id.setNumber(stockNum);
          entity.setEarnings(removeWebSpaceLong(tds.get(1).text()) * 1000);
          entity.setIncreaseRateM(removeWebSpacePercentage(tds.get(2).text()));
          entity.setLastYear(removeWebSpaceLong(tds.get(3).text()) * 1000);
          entity.setIncreaseRateY(removeWebSpacePercentage(tds.get(4).text()));
          entity.setTotalEarningsY(removeWebSpaceLong(tds.get(5).text()) * 1000);
          entity.setTotalIncreaseRateY(removeWebSpacePercentage(tds.get(6).text()));
          entities.add(entity);
        }
      }
      earningsRepo.save(entities);
    } catch (IOException e) {
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
      parseEarnings(stockNum, url);
    } catch (Exception e) {
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
      parseEarnings(stockNum, url);
    }
  }

  public void parsePrice(Integer stockNum, String url) {
    try {
      List<Price> entities = new ArrayList<>();
      Document doc = Jsoup.connect(url).timeout(100000).get();
      Elements elements = doc.getElementsByTag("body");
      if (elements.size() > 1) {
        throw new IllegalArgumentException("Elemnts size should be 1");
      }
      String body = elements.get(0).text();
      String datas[] = body.split(" ");
      String dates[] = datas[0].split(",");
      String prices[] = datas[1].split(",");
      if (dates.length != prices.length) {
        throw new IllegalArgumentException("Array size should be same");
      }
      for (int i = 0; i < dates.length; i++) {
        Price entity = new Price();
        StockDateId id = new StockDateId();
        id.setDay(removeWebSpaceInteger(dates[i].substring(5, 7)));
        id.setMonth(removeWebSpaceInteger(dates[i].substring(3, 5)));
        id.setYear(removeWebSpaceInteger(dates[i].substring(0, 3)));
        id.setNumber(stockNum);
        entity.setPriceId(id);
        entity.setPrice(Double.valueOf(prices[i]));
        entities.add(entity);
      }
      priceRepo.save(entities);
    } catch (IOException e) {
      parsePrice(stockNum, url);
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
    } catch (Exception e) {
      parsePrice(stockNum, url);
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
    }
  }


  public void parseCapital(Integer stockNum, String url) {
    List<Capital> entities = new ArrayList<>();
    try {
      Document doc = Jsoup.connect(url).timeout(10000).get();
      Elements elements = doc.getElementsByTag("table").get(0).getElementsByTag("tr");
      elements.remove(0);
      elements.remove(0);
      for (Element element : elements) {
        Elements tds = element.getElementsByTag("td");
        if (!StringUtils.isNumeric(tds.get(0).text()) || StringUtils.isEmpty(tds.get(0).text())) {
          continue;
        }
        Capital entity = new Capital();
        StockYearId id = new StockYearId();
        Integer year = removeWebSpaceInteger(tds.get(0).text());
        id.setNumber(stockNum);
        id.setYear(year);
        entity.setId(id);
        entity.setCashIncrease(removeWebSpaceDouble(tds.get(1).text()) * 100000000);
        entity.setCashRatio(removeWebSpacePercentage(tds.get(2).text()));
        entity.setProfitIncreate(removeWebSpaceDouble(tds.get(3).text()) * 100000000);
        entity.setProfitRatio(removeWebSpacePercentage(tds.get(4).text()));
        entity.setOther(removeWebSpaceDouble(tds.get(5).text()) * 100000000);
        entity.setOtherRatio(removeWebSpacePercentage(tds.get(6).text()));
        entities.add(entity);
      }
      capitalRepo.save(entities);
    } catch (IOException e) {
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
      parseCapital(stockNum, url);
    } catch (Exception e) {
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
      parseCapital(stockNum, url);
    }
  }

  public void parseStockNumber(String url) {
    try {
      List<Stock> entities = new ArrayList<>();
      int type = StockConstant.TYPE_EXCHANGE;
      String industry = "";
      Document doc = Jsoup.connect(url).get();
      Elements trs = doc.getElementsByTag("tr");
      for (Element tr : trs) {
        if (tr.equals(trs.get(0))) {
          continue;
        }
        Elements tds = tr.getElementsByTag("td");
        if (tds.size() < 2) {
          continue;
        }
        industry = removeWebSpaceString(tds.get(4).text());
        if (industry.isEmpty()) {
          break;
        }

        Stock entity = new Stock();
        entity.setIndustry(industry);
        entity.setType(type);


        Integer number = removeWebSpaceInteger(tds.get(0).text().substring(0, 4));
        String name = removeWebSpaceString(tds.get(0).text().substring(4));
        entity.setName(name);
        entity.setNumber(number);
        entities.add(entity);
      }
      stockRepo.save(entities);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String removeWebSpaceString(String value) {
    return value.replace(String.valueOf((char) 160), " ").trim();
  }

  public static Integer removeWebSpaceInteger(String value) {
    value = value.replace(String.valueOf((char) 160), " ").replace(",", "").trim();
    if (value.isEmpty()) {
      return 0;
    }
    return Integer.valueOf(value);
  }

  public static Long removeWebSpaceLong(String value) {
    value = value.replace(String.valueOf((char) 160), " ").replace(",", "").trim();
    if (value.isEmpty()) {
      return 0L;
    }
    return Long.valueOf(value);
  }

  public static Double removeWebSpaceDouble(String value) {
    try {
      value = value.replace(String.valueOf((char) 160), " ").replace(",", "").trim();
      if (value.isEmpty()) {
        return 0.0;
      }
      return Double.valueOf(value);
    } catch (NumberFormatException e) {
      if (value.equals("N/A")) {
        return 0.0;
      }
      e.printStackTrace();
      System.out.println(value);
    }
    return null;
  }

  public static Double removeWebSpacePercentage(String value) {
    try {
      value = value.replace(String.valueOf((char) 160), " ").replace(".", "").replace(",", "")
          .replace("%", "").trim();
      if (value.isEmpty()) {
        return 0.0;
      }
      return new BigDecimal(value).setScale(4).divide(new BigDecimal("100")).doubleValue();
    } catch (NumberFormatException e) {
      e.printStackTrace();
      System.out.println(value);
    }
    return null;
  }

  public static void main(String[] args) {
    System.out.println("104.Q4".split(".")[1]);
  }
}

