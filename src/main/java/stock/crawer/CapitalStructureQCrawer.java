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

import stock.db.entity.CapitalStructureQ;
import stock.db.entity.StockQuarterId;
import stock.db.repo.CapitalStructureQRepo;

@Component
public class CapitalStructureQCrawer extends AbstractStockCrawer<CapitalStructureQ> {

  @Inject
  private CapitalStructureQRepo capitalStructureRepo;

  @Override
  public List<CapitalStructureQ> crawAction(Integer stockNum, String url) {
    List<CapitalStructureQ> entities = new ArrayList<>();
    try {
      Document doc = Jsoup.connect(url).timeout(10000).get();
      Elements elements = doc.getElementsByTag("table").get(2).getElementsByTag("tr");
      Elements tdTime = elements.get(1).getElementsByTag("td");
      Elements tdNetIncomeRatio = elements.get(6).getElementsByTag("td");
      Elements tdReceivablesTurnoverRatio = elements.get(28).getElementsByTag("td");
      Elements tdInventoryTurnoverRatio = elements.get(29).getElementsByTag("td");
      for (int i = 1; i < tdTime.size(); i++) {
        CapitalStructureQ entity = new CapitalStructureQ();
        StockQuarterId id = new StockQuarterId();
        entity.setId(id);

        id.setYear(removeWebSpaceInteger(tdTime.get(i).text().substring(0, 3)));
        id.setQuarter(removeWebSpaceInteger(tdTime.get(i).text().substring(4, 5)));
        id.setNumber(stockNum);
        entity.setNetIncomeRatio(removeWebSpaceDouble(tdNetIncomeRatio.get(i).text()) * PERCENT);
        entity.setInventoryTurnoverRatio(
            removeWebSpaceDouble(tdInventoryTurnoverRatio.get(i).text()));
        entity.setReceivablesTurnoverRatio(
            removeWebSpaceDouble(tdReceivablesTurnoverRatio.get(i).text()));
        entities.add(entity);
      }
      capitalStructureRepo.save(entities);
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
