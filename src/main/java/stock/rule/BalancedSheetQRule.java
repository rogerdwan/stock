package stock.rule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.BalancedSheetQ;
import stock.db.entity.StockQuarterId;
import stock.db.repo.BalancedSheetQRepo;
import stock.utils.DateQuarter;
import stock.utils.TimeUtils;

@Component
public class BalancedSheetQRule implements Rule {

  @Inject
  private BalancedSheetQRepo balancedSheetQRepo;

  public boolean apply(Integer stockNum, Condition condition, LocalDate date) {
    List<DateQuarter> dqs = TimeUtils.getClosestDateQuarter(date.getYear(),
        (int) Math.ceil(date.getMonthValue() / 4), condition.getPeriod());

    List<StockQuarterId> ids = new ArrayList<>();
    for (DateQuarter dq : dqs) {
      StockQuarterId id = new StockQuarterId();
      id.setNumber(stockNum);
      id.setQuarter(dq.getQuarter());
      id.setTaiwanYear(dq.getYear());
      ids.add(id);
    }
    List<BalancedSheetQ> balancedSheetQs = balancedSheetQRepo.findByIdIn(ids);
    boolean isLess = true;
    for (BalancedSheetQ balancedSheetQ : balancedSheetQs) {
      isLess = isLess && (balancedSheetQ.getIntangibleAssets() > 0
          || balancedSheetQ.getIntangibleAssets() / balancedSheetQ.getTotalAssets() < 0.005);
    }
    if (isLess) {
      return true;
    }
    return false;
  }
}
