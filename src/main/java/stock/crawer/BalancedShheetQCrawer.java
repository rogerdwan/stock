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

import stock.db.entity.BalancedSheetQ;
import stock.db.entity.StockQuarterId;
import stock.db.repo.BalancedSheetQRepo;

@Component
public class BalancedShheetQCrawer extends AbstractStockCrawer<BalancedSheetQ> {

  @Inject
  private BalancedSheetQRepo balancedSheetQRepo;

  @Override
  public List<BalancedSheetQ> crawAction(Integer stockNum, String url) {
    List<BalancedSheetQ> entities = new ArrayList<>();
    try {
      Document doc = Jsoup.connect(url).timeout(10000).get();
      Elements elements = doc.getElementsByTag("table").get(2).getElementsByTag("tr");
      Elements tdTime = elements.get(1).getElementsByTag("td");
      Elements tdIntangibleAssets = elements.get(23).getElementsByTag("td");
      Elements tdTotalAssets = elements.get(26).getElementsByTag("td");
      for (int i = 1; i < tdTime.size(); i++) {
        BalancedSheetQ entity = new BalancedSheetQ();
        StockQuarterId id = new StockQuarterId();
        entity.setId(id);

        id.setYear(removeWebSpaceInteger(tdTime.get(i).text().substring(0, 3)));
        id.setQuarter(removeWebSpaceInteger(tdTime.get(i).text().substring(4, 5)));
        id.setNumber(stockNum);
        entity.setIntangibleAssets(removeWebSpaceLong(tdIntangibleAssets.get(i).text()) * MILLION);
        entity.setTotalAssets(removeWebSpaceLong(tdTotalAssets.get(i).text()) * MILLION);
        entities.add(entity);
      }
      balancedSheetQRepo.save(entities);
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
