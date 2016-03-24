package stock.rule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.Performance;
import stock.db.entity.StockQuarterId;
import stock.db.repo.PerformanceRepo;
import stock.utils.DateQuarter;
import stock.utils.TimeUtils;

@Component
public class EpsRule implements Rule {

  @Inject
  private PerformanceRepo performanceRepo;

  public boolean apply(Integer stockNum, Condition condition, LocalDate date) {
    List<DateQuarter> dqs = TimeUtils.getClosestDateQuarter(date.getYear(),
        (int) Math.ceil(date.getMonthValue() / 4), condition.getPeriod() + 1);

    List<StockQuarterId> ids = new ArrayList<>();
    for (DateQuarter dq : dqs) {
      StockQuarterId id = new StockQuarterId();
      id.setNumber(stockNum);
      id.setQuarter(dq.getQuarter());
      id.setTaiwanYear(dq.getYear());
      ids.add(id);
    }
    List<Performance> performances = performanceRepo.findByIdInOrderByIdYearDescIdQuarterDesc(ids);
    if (performances.size() > condition.getPeriod()) {
      performances.remove(condition.getPeriod());
    }
    boolean isIncrease = true;
    for (Performance performance : performances) {
      isIncrease = isIncrease && performance.getEps() > 0;
    }
    if (isIncrease) {
      return true;
    }
    return false;
  }
}
