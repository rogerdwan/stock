package stock.rule;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.Capital;
import stock.db.entity.StockYearId;
import stock.db.repo.CapitalRepo;
import stock.utils.TimeUtils;

@Component
public class CapitalRule implements Rule {

  @Inject
  private CapitalRepo capitalRepo;

  public boolean apply(Integer stockNum, Condition condition, LocalDate date) {
    List<Integer> years = TimeUtils.getClosestDateYear(date.getYear(), condition.getPeriod());

    boolean isLess = true;
    Double currentRatio = 0.0;
    Double lastRatio = 0.0;
    for (Integer year : years) {
      lastRatio = currentRatio;
      StockYearId id = new StockYearId();
      id.setNumber(stockNum);
      id.setTaiwanYear(year);
      Capital capital = capitalRepo.findOne(id);
      if (capital == null) {
        break;
      }
      currentRatio = capital.getCashRatio();
      isLess = isLess && currentRatio >= lastRatio;
    }
    if (isLess) {
      return true;
    }
    return false;
  }
}
