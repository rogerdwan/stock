package stock.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeUtils {

  public static List<LocalDate> getClosestMonth(Integer year, Integer month) {
    LocalDate ld = LocalDate.of(year, month, 1);
    List<LocalDate> lds = new ArrayList<>();
    for (int i = 0; i < 1; i++) {
      lds.add(ld.minusMonths(i));
    }
    return lds;
  }

  public static List<LocalDate> getClosestMonthes(Integer year, Integer month, Integer numOfMonth) {
    LocalDate ld = LocalDate.of(year, month, 1);
    List<LocalDate> lds = new ArrayList<>();
    for (int i = 0; i < numOfMonth; i++) {
      lds.add(ld.minusMonths(i));
    }
    return lds;
  }

  public static List<LocalDate> getClosestThreeMonth(Integer year, Integer month) {
    LocalDate ld = LocalDate.of(year, month, 1);
    List<LocalDate> lds = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      lds.add(ld.minusMonths(i));
    }
    return lds;
  }

  public static List<DateQuarter> getClosestDateQuarter(Integer year, Integer quarter,
      Integer numQ) {
    List<DateQuarter> results = new ArrayList<>();
    DateQuarter dq = DateQuarter.of(year, quarter);
    for (int i = 0; i < numQ; i++) {
      results.add(dq.minusQuarter(i));
    }
    return results;
  }

  public static List<Integer> getClosestDateYear(Integer year, Integer numY) {
    List<Integer> results = new ArrayList<>();

    for (int i = 0; i < numY; i++) {
      results.add(year - i);
    }
    return results;
  }
}
