package stock.prediction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.Earnings;
import stock.db.entity.StockMonthId;
import stock.db.repo.EarningsRepo;
import stock.utils.TimeUtils;

@Component
public class EarningPrediction {

  @Inject
  private EarningsRepo earningsRepo;

  public Double predicRatio(Integer stockNum, LocalDate localDate) {

    List<LocalDate> twelveLds =
        TimeUtils.getClosestMonthes(localDate.getYear(), localDate.getMonthValue(), 12);

    List<StockMonthId> ids = new ArrayList<>();
    for (LocalDate ld : twelveLds) {
      StockMonthId id = new StockMonthId();
      id.setNumber(stockNum);
      id.setMonth(ld.getMonthValue());
      id.setTaiwanYear(ld.getYear());
      ids.add(id);
    }

    long currentSixTotal = 0;
    long preSixTotal = 0;

    List<Earnings> earninges = earningsRepo.findByIdInOrderByIdYearDescIdMonthDesc(ids);

    if (earninges.size() < 12) {
      throw new IllegalArgumentException(
          String.format("Stock Number: %d, not enought data for ratio prediction", stockNum));
    }

    for (Earnings earnings : earninges.subList(0, 6)) {
      currentSixTotal += earnings.getEarnings();
      preSixTotal += earnings.getLastYear();
    }
    long currentElevenTotal = currentSixTotal;
    long preElevenTotal = preSixTotal;
    for (Earnings earnings : earninges.subList(6, 11)) {
      currentElevenTotal += earnings.getEarnings();
      preElevenTotal += earnings.getLastYear();
    }
    return Math.min(((double) currentElevenTotal / preElevenTotal) - 1,
        ((double) currentSixTotal / preSixTotal) - 1);
  }

  public Map<StockMonthId, Long> predicYearTotal(Integer stockNum, LocalDate localDate)
      throws IllegalArgumentException {

    Map<StockMonthId, Long> results = new HashMap<>();
    Double predicRatio = predicRatio(stockNum, localDate);

    List<LocalDate> twelveLds = TimeUtils.getClosestMonthes(localDate.getYear(), 12, 12);

    List<StockMonthId> ids = new ArrayList<>();
    for (LocalDate ld : twelveLds) {
      StockMonthId id = new StockMonthId();
      id.setNumber(stockNum);
      id.setMonth(ld.getMonthValue());
      id.setTaiwanYear(ld.getYear());
      ids.add(id);
    }

    List<Earnings> earninges = earningsRepo.findByIdInOrderByIdYearDescIdMonthDesc(ids);

    List<LocalDate> lastYear = new ArrayList<>();
    if (earninges.size() < 12) {
      lastYear = TimeUtils.getClosestMonthes(localDate.getYear() - 1, 12, 12 - earninges.size());
    }
    List<StockMonthId> lastYearIds = new ArrayList<>();
    for (LocalDate ld : lastYear) {
      StockMonthId id = new StockMonthId();
      id.setNumber(stockNum);
      id.setMonth(ld.getMonthValue());
      id.setTaiwanYear(ld.getYear());
      lastYearIds.add(id);
    }

    List<Earnings> lastYearEarninges =
        earningsRepo.findByIdInOrderByIdYearDescIdMonthDesc(lastYearIds);
    if (lastYearEarninges.size() < 12 - earninges.size()) {
      throw new IllegalArgumentException(
          String.format("Stock Number: %d, not enought data for prediction", stockNum));
    }

    for (Earnings earnings : lastYearEarninges) {
      results.put(earnings.getId(), (long) (earnings.getEarnings() * predicRatio));
    }
    for (Earnings earnings : earninges) {
      results.put(earnings.getId(), earnings.getEarnings());
    }
    return results;
  }
}
