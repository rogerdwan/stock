package stock.rule;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.CapitalStructureQ;
import stock.db.entity.StockQuarterId;
import stock.db.repo.CapitalStructureQRepo;
import stock.utils.DateQuarter;
import stock.utils.TimeUtils;

@Component
public class CapitalStructrueQRule implements Rule {

  @Inject
  private CapitalStructureQRepo capitalStructureQRepo;

  public boolean apply(Integer stockNum, Condition condition, LocalDate date) {
    List<DateQuarter> dqs = TimeUtils.getClosestDateQuarter(date.getYear(),
        (int) Math.ceil(date.getMonthValue() / 4), condition.getPeriod());
    boolean isMore = true;
    Double currentRTR = 100.0;
    Double currentITR = 100.0;
    Double lastRTR = 100.0;
    Double lastITR = 100.0;
    for (DateQuarter dq : dqs) {
      currentITR = lastITR;
      currentRTR = lastRTR;
      StockQuarterId id = new StockQuarterId();
      id.setNumber(stockNum);
      id.setQuarter(dq.getQuarter());
      id.setTaiwanYear(dq.getYear());
      CapitalStructureQ capitalStructureQ = capitalStructureQRepo.findOne(id);
      if (capitalStructureQ == null) {
        break;
      }
      currentRTR = capitalStructureQ.getReceivablesTurnoverRatio();
      currentITR = capitalStructureQ.getInventoryTurnoverRatio();
      isMore = isMore && currentITR < lastITR && currentRTR < lastRTR;
    }
    if (isMore) {
      return true;
    }
    return false;
  }
}
