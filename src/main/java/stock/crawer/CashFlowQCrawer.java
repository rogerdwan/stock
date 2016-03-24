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

import stock.db.entity.CashFlowQ;
import stock.db.entity.StockQuarterId;
import stock.db.repo.CashFlowQRepo;

@Component
public class CashFlowQCrawer extends AbstractStockCrawer<CashFlowQ> {

  @Inject
  private CashFlowQRepo cashFlowRepo;

  @Override
  public List<CashFlowQ> crawAction(Integer stockNum, String url) {
    List<CashFlowQ> entities = new ArrayList<>();
    try {
      Document doc = Jsoup.connect(url).timeout(10000).get();
      Elements elements = doc.getElementsByTag("table").get(2).getElementsByTag("tr");
      Elements tdTime = elements.get(1).getElementsByTag("td");
      Elements tdInvesting = elements.get(17).getElementsByTag("td");
      Elements tdOperation = elements.get(24).getElementsByTag("td");
      for (int i = 1; i < tdTime.size(); i++) {

        CashFlowQ entity = new CashFlowQ();
        StockQuarterId id = new StockQuarterId();
        entity.setId(id);

        id.setYear(removeWebSpaceInteger(tdTime.get(i).text().substring(0, 3)));
        id.setQuarter(removeWebSpaceInteger(tdTime.get(i).text().substring(4, 5)));
        id.setNumber(stockNum);
        entity.setInvestin(removeWebSpaceLong(tdInvesting.get(i).text()) * MILLION);
        entity.setOperation(removeWebSpaceLong(tdOperation.get(i).text()) * MILLION);
        entities.add(entity);
      }
      cashFlowRepo.save(entities);
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
