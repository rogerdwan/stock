package stock.rule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.CashFlowQ;
import stock.db.entity.StockQuarterId;
import stock.db.repo.CashFlowQRepo;
import stock.utils.DateQuarter;
import stock.utils.TimeUtils;

@Component
public class CashFlowQRule implements Rule {

  @Inject
  private CashFlowQRepo cashFlowQRepo;

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
    List<CashFlowQ> cashFlowQs = cashFlowQRepo.findByIdIn(ids);
    boolean isMore = true;
    for (CashFlowQ cashFlowQ : cashFlowQs) {
      isMore = isMore && cashFlowQ.getInvestin() + cashFlowQ.getOperation() > 0;
    }
    if (isMore) {
      return true;
    }
    return false;
  }
}
