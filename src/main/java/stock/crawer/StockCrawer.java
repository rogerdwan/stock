package stock.crawer;

import java.util.List;
import java.util.Map;

public interface StockCrawer<T> {

  List<T> craw(Map<Integer, String> urls);

}
