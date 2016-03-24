package stock.crawer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractStockCrawer<T> implements StockCrawer<T> {

  static final Long MILLION = 1000000L;
  static final Double PERCENT = 0.01;

  public List<T> craw(Map<Integer, String> urls) {
    System.out.println("Parsing start: " + this.getClass().getSimpleName());
    removeUnnecessaryNums(urls);
    List<T> results = new ArrayList<>();
    for (Integer stockNum : urls.keySet()) {
      results.addAll(crawAction(stockNum, urls.get(stockNum)));
    }
    System.out.println("Parsing successful: " + this.getClass().getSimpleName());
    return results;
  }

  abstract protected List<T> crawAction(Integer stockNum, String url);

  abstract protected Map<Integer, String> removeUnnecessaryNums(Map<Integer, String> urls);

  private static boolean isNA(String value) {
    return value.equals("N/A");
  }

  static String removeWebSpaceString(String value) {
    return value.replace(String.valueOf((char) 160), " ").trim();
  }

  static Integer removeWebSpaceInteger(String value) {
    value = value.replace(String.valueOf((char) 160), " ").replace(",", "").trim();
    if (value.isEmpty() || isNA(value)) {
      return 0;
    }
    return Integer.valueOf(value);
  }

  static Long removeWebSpaceLong(String value) {
    value = value.replace(String.valueOf((char) 160), " ").replace(",", "").trim();
    if (value.isEmpty() || isNA(value)) {
      return 0L;
    }
    return Long.valueOf(value);
  }

  static Double removeWebSpaceDouble(String value) {
    try {
      value = value.replace(String.valueOf((char) 160), " ").replace(",", "").trim();
      if (value.isEmpty() || isNA(value)) {
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

  static Double removeWebSpacePercentage(String value) {
    try {
      value = value.replace(String.valueOf((char) 160), " ").replace(".", "").replace(",", "")
          .replace("%", "").trim();
      if (value.isEmpty() || isNA(value)) {
        return 0.0;
      }
      return new BigDecimal(value).setScale(4).divide(new BigDecimal("100")).doubleValue();
    } catch (NumberFormatException e) {
      e.printStackTrace();
      System.out.println(value);
    }
    return null;
  }
}
