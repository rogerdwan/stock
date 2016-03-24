package stock.prediction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import stock.db.entity.Capital;
import stock.db.entity.Performance;
import stock.db.entity.StockQuarterId;
import stock.db.entity.StockYearId;
import stock.db.repo.CapitalRepo;
import stock.db.repo.PerformanceRepo;
import stock.utils.DateQuarter;
import stock.utils.TimeUtils;

@Component
public class EpsPrediction {

  @Inject
  private PerformanceRepo performanceRepo;
  @Inject
  private CapitalRepo capitalRepo;

  public Double predicTotalEps(Integer stockNum, Long lastYearNetIncome, LocalDate localDate) {
    List<DateQuarter> dqs = TimeUtils.getClosestDateQuarter(localDate.getYear(), 4, 4);
    List<StockQuarterId> ids = new ArrayList<>();
    for (DateQuarter dq : dqs) {
      StockQuarterId id = new StockQuarterId();
      id.setNumber(stockNum);
      id.setQuarter(dq.getQuarter());
      id.setTaiwanYear(dq.getYear());
      ids.add(id);
    }
    List<Performance> performances = performanceRepo.findByIdInOrderByIdYearDescIdQuarterDesc(ids);
    double totalEps = 0;
    for (Performance performance : performances) {
      totalEps += performance.getEps();
    }
    StockYearId id = new StockYearId();
    id.setNumber(stockNum);
    id.setTaiwanYear(localDate.getYear());
    Capital capital = capitalRepo.findOne(id);
    long capitalSum = capital.getCashIncrease() + capital.getOther() + capital.getProfitIncreate();
    totalEps += lastYearNetIncome / capitalSum;
    return totalEps;
  }
}
