package stock.html.parsing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import stock.StockConstant;
import stock.db.entity.Price;
import stock.db.entity.Stock;
import stock.db.entity.StockDateId;
import stock.db.repo.PriceRepo;
import stock.db.repo.StockRepo;

@Service
public class StockInfoPasing {

  @Inject
  private PriceRepo priceRepo;
  @Inject
  private StockRepo stockRepo;

  @Scheduled(fixedDelay = 60 * 60 * 1000 * 24)
  public void parseData() {
    List<Integer> stockNums = stockRepo.getNumbers();
    // parsePrice(UrlPattern.PRICE.getUrl(stockNums));
    // parseStockNumber("http://isin.twse.com.tw/isin/C_public.jsp?strMode=2");
  }

  public void parsePrice(Map<Integer, String> urls) {
    for (Map.Entry<Integer, String> entity : urls.entrySet()) {
      parsePrice(entity.getKey(), entity.getValue());
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
        id.setDate(dates[i]);
        id.setNumber(stockNum);
        entity.setPriceId(id);
        entity.setPrice(Double.valueOf(prices[i]));
        entities.add(entity);
      }
      priceRepo.save(entities);
    } catch (IOException e) {
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("===========================" + stockNum + "=====================");
      e.printStackTrace();
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
    return Integer.valueOf(value.replace(String.valueOf((char) 160), " ").trim());
  }
}

