package stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum UrlPattern {

  PRICE(
      "http://5850web.moneydj.com/z/zc/zc0/zc05/CZCPP.djbcd?a=%d&b=E&c=E&d=E&e=E&f=E&g=0&h=E&i=5"),

  EARNINGS("http://5850web.moneydj.com/z/zc/zch/zch_%d.djhtm"),

  PERFORMANCE("http://5850web.moneydj.com/z/zc/zcd/zcd_%d.djhtm"),

  CAPITAL("http://5850web.moneydj.com/z/zc/zcb/zcb_%d.djhtm");
  String pattern;

  UrlPattern(String pattern) {
    this.pattern = pattern;
  }

  // static final String basicInfo = "http://5850web.moneydj.com/z/zc/zca/zca_%d.djhtm";
  // static final String stockCapital = "http://5850web.moneydj.com/z/zc/zca/zca_%d.djhtm";
  // static final String profitPolicy = "http://5850web.moneydj.com/z/zc/zcc/zcc_%d.djhtm";
  // static final String performanceYAggregate = "http://5850web.moneydj.com/z/zc/zcd/zcd_%d.djhtm";
  // static final String performanceQAggregate = "http://5850web.moneydj.com/z/zc/zcdj_%d.djhtm";
  // static final String performanceY = "http://5850web.moneydj.com/z/zc/zcd_%d_2.djhtm";
  // static final String performanceQ = "http://5850web.moneydj.com/z/zc/zcdj_%d_2.djhtm";
  // static final String profitAbilityAggregate =
  // "http://5850web.moneydj.com/z/zc/zce/zce_%d.djhtm";
  // static final String profitAbility = "http://5850web.moneydj.com/z/zc/zce/zce0_%d.djhtm";
  // static final String reinvestment = "http://5850web.moneydj.com/z/zc/zcg/zcg_%d.djhtm";
  // static final String profitMAggregate = "http://5850web.moneydj.com/z/zc/zch/zch_%d.djhtm";
  // static final String profitQAggregate = "http://5850web.moneydj.com/z/zc/zch/zcha_%d.djhtm";
  // static final String profitM = "http://5850web.moneydj.com/z/zc/zch/zchb_%d.djhtm";
  //
  // static final String balancedSheetAggregate =
  // "http://5850web.moneydj.com/z/zc/zcp/zcp_%d.djhtm";
  // static final String balancedSheet = "http://5850web.moneydj.com/z/zc/zcp/zcp0_%d.djhtm";

  public Map<Integer, String> getUrl(List<Integer> stockNums) {
    Map<Integer, String> urls = new HashMap<>();
    for (int stockNum : stockNums) {
      urls.put(stockNum, String.format(pattern, stockNum));
    }
    return urls;
  }

}
