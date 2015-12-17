package stock.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.repo.EarningsRepo;
import stock.db.repo.StockRepo;
import stock.utils.TimeUtils;

@Component
public class EarningsService {

  private static final Double MIN_RATE = 10.0;

  @Inject
  private EarningsRepo earningsRepo;

  public void getContinousIncreaseStockNum(List<Integer> stockNums) {
    Map<Integer, Double> results = new HashMap<>();
    for (int num : stockNums) {
      try {
        Integer maxMonth = earningsRepo.getMaxMonthByStockNum(num);
        Integer maxYear = earningsRepo.getMaxYearByStockNum(num);
        List<LocalDate> lds = TimeUtils.getClosestThreeMonth(maxYear, maxMonth);
        List<Double> rates = new ArrayList<>();
        for (LocalDate ld : lds) {
          rates.add(earningsRepo.getRates(ld.getYear(), ld.getMonth().getValue(), num));
        }
        boolean isIncrease = true;
        double rateSum = 0;
        for (Double rate : rates) {
          rateSum += rate;
          isIncrease = isIncrease && rate >= MIN_RATE;
        }
        if (isIncrease) {
          results.put(num, new BigDecimal(rateSum).setScale(2, RoundingMode.HALF_UP)
              .divide(new BigDecimal(3)).doubleValue());
        }
      } catch (Exception e) {
        // e.printStackTrace();
      }
    }

    for (Integer key : results.keySet()) {
      System.out.println(String.format("%d, %.2f", key, results.get(key)));
    }
  }

}
