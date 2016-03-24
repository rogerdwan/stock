package stock.rule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.IncomeStatementQ;
import stock.db.entity.StockQuarterId;
import stock.db.repo.IncomeStatementQRepo;
import stock.utils.DateQuarter;
import stock.utils.TimeUtils;

@Component
public class IncomeStatementQRule {

  @Inject
  IncomeStatementQRepo incomeStatementQRepo;

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
    List<IncomeStatementQ> incomeStatementQs = incomeStatementQRepo.findByIdIn(ids);
    boolean isLess = true;
    for (IncomeStatementQ incomeStatementQ : incomeStatementQs) {
      Long interest = incomeStatementQ.getEbit() - incomeStatementQ.getPreTaxIncome();
      Long depreciation = incomeStatementQ.getEbitda() - incomeStatementQ.getPreTaxIncome();
      isLess = Math.abs((interest + depreciation)) < incomeStatementQ.getGross();
    }
    if (isLess) {
      return true;
    }
    return false;
  }
}
