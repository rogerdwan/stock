package stock.prediction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.IncomeStatementQ;
import stock.db.entity.StockMonthId;
import stock.db.entity.StockQuarterId;
import stock.db.repo.IncomeStatementQRepo;
import stock.utils.DateQuarter;
import stock.utils.TimeUtils;

@Component
public class NetIncomePrediction {

  @Inject
  private IncomeStatementQRepo incomeStatementQRepo;

  private static final List<Integer> MONTHES = initMonthes();

  private static final List<Integer> initMonthes() {
    List<Integer> results = new ArrayList<>();
    for (int i = 12; i > 0; i--) {
      results.add(i);
    }
    return results;
  }

  public Double predicRatio(Integer stockNum, LocalDate localDate) throws IllegalArgumentException {

    List<DateQuarter> dqs = TimeUtils.getClosestDateQuarter(localDate.getYear(),
        (int) Math.ceil(localDate.getMonthValue() / 4), 4);

    List<StockQuarterId> ids = new ArrayList<>();
    for (DateQuarter dq : dqs) {
      StockQuarterId id = new StockQuarterId();
      id.setNumber(stockNum);
      id.setQuarter(dq.getQuarter());
      id.setTaiwanYear(dq.getYear());
      ids.add(id);
    }

    long totalNetIncome = 0;
    long totalIncome = 0;

    List<IncomeStatementQ> incomeStatementQs =
        incomeStatementQRepo.findByIdInOrderByIdYearDescIdQuarterDesc(ids);
    if (incomeStatementQs.size() < 4) {
      throw new IllegalArgumentException(
          String.format("Stock Number: %d, not enought data for ratio prediction", stockNum));
    }
    for (IncomeStatementQ incomeStatementQ : incomeStatementQs) {
      totalIncome += incomeStatementQ.getNetIncome();
      totalIncome += incomeStatementQ.getNetSales();
    }

    long currentNetIncome = incomeStatementQs.get(0).getNetIncome();
    long currentIncome = incomeStatementQs.get(0).getNetSales();

    return Math.min(((double) totalNetIncome / totalIncome),
        ((double) currentNetIncome / currentIncome));
  }

  public Long predictLastYearTotal(Integer stockNum, LocalDate localDate,
      Map<StockMonthId, Long> earninges) throws IllegalArgumentException {
    Double predicRatio = predicRatio(stockNum, localDate);

    List<DateQuarter> dqs = TimeUtils.getClosestDateQuarter(localDate.getYear(), 4, 4);
    List<StockQuarterId> ids = new ArrayList<>();
    for (DateQuarter dq : dqs) {
      StockQuarterId id = new StockQuarterId();
      id.setNumber(stockNum);
      id.setQuarter(dq.getQuarter());
      id.setTaiwanYear(dq.getYear());
      ids.add(id);
    }
    List<IncomeStatementQ> incomeStatementQs =
        incomeStatementQRepo.findByIdInOrderByIdYearDescIdQuarterDesc(ids);
    List<Integer> monthes = MONTHES.subList(0, (4 - incomeStatementQs.size()) * 3);
    long totalNetIncome = 0;
    for (StockMonthId monthId : earninges.keySet()) {
      if (monthes.contains(monthId.getMonth())) {
        totalNetIncome += earninges.get(monthId) * predicRatio;
      }
    }
    return totalNetIncome;

  }
}
