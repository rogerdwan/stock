package stock.crawer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import stock.db.entity.IncomeStatementQ;
import stock.db.entity.StockQuarterId;
import stock.db.repo.IncomeStatementQRepo;

@Component
public class IncomeStatementQCrawer extends AbstractStockCrawer<IncomeStatementQ> {

  @Inject
  private IncomeStatementQRepo incomeStatementQRepo;

  @Override
  public List<IncomeStatementQ> crawAction(Integer stockNum, String url) {
    List<IncomeStatementQ> entities = new ArrayList<>();
    try {
      Document doc = Jsoup.connect(url).timeout(10000).get();
      Elements elements = doc.getElementsByTag("table").get(2).getElementsByTag("tr");
      Elements tdTime = elements.get(1).getElementsByTag("td");
      Elements tdNetSales = elements.get(2).getElementsByTag("td");
      Elements tdGross = elements.get(4).getElementsByTag("td");
      Elements tdPreTaxIncome = elements.get(39).getElementsByTag("td");
      Elements tdNetIncome = elements.get(49).getElementsByTag("td");
      Elements tdEbit = elements.get(57).getElementsByTag("td");
      Elements tdEbitda = elements.get(58).getElementsByTag("td");
      for (int i = 1; i < tdTime.size(); i++) {
        IncomeStatementQ entity = new IncomeStatementQ();
        StockQuarterId id = new StockQuarterId();
        entity.setId(id);

        id.setYear(removeWebSpaceInteger(tdTime.get(i).text().substring(0, 3)));
        id.setQuarter(removeWebSpaceInteger(tdTime.get(i).text().substring(4, 5)));
        id.setNumber(stockNum);
        entity.setNetSales(removeWebSpaceLong(tdNetSales.get(i).text()) * MILLION);
        entity.setGross(removeWebSpaceLong(tdGross.get(i).text()) * MILLION);
        entity.setPreTaxIncome(removeWebSpaceLong(tdPreTaxIncome.get(i).text()) * MILLION);
        entity.setNetIncome(removeWebSpaceLong(tdNetIncome.get(i).text()) * MILLION);
        entity.setEbit(removeWebSpaceLong(tdEbit.get(i).text()) * MILLION);
        entity.setEbitda(removeWebSpaceLong(tdEbitda.get(i).text()) * MILLION);
        entities.add(entity);
      }
      incomeStatementQRepo.save(entities);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(stockNum);
    } catch (IOException e) {
      try {
        Thread.sleep(3000L);
      } catch (InterruptedException e1) {
        e1.printStackTrace();
      }
      System.out.println(stockNum);
      crawAction(stockNum, url);
    } catch (Exception e) {
      try {
        Thread.sleep(3000L);
      } catch (InterruptedException e1) {
        e1.printStackTrace();
      }
      System.out.println(stockNum);
      crawAction(stockNum, url);
    }
    return entities;
  }

  @Override
  protected Map<Integer, String> removeUnnecessaryNums(Map<Integer, String> urls) {
    // TODO Auto-generated method stub
    return urls;
  }

}
