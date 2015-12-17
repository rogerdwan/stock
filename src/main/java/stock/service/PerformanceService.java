package stock.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import stock.db.entity.Performance;
import stock.db.entity.StockQuarterId;
import stock.db.repo.PerformanceRepo;
import stock.db.repo.StockRepo;
import stock.utils.DateQuarter;
import stock.utils.TimeUtils;

@Service
public class PerformanceService {

  private static final Integer EIGHT_Q = 8;

  @Inject
  private PerformanceRepo performanceRepo;
  @Inject
  private StockRepo stockRepo;

  public List<Integer> getContiniousPlusEightQ() {
    List<Integer> stockNums = stockRepo.getNumbers();
    List<Integer> results = new ArrayList<>();

    for (Integer stockNum : stockNums) {
      List<Object[]> yearQuarter = performanceRepo.getMaxQuarterByStockNum(stockNum);
      List<DateQuarter> dqs =
          TimeUtils.getClosestDateQuarter(Integer.valueOf(yearQuarter.get(0)[0].toString()),
              Integer.valueOf(yearQuarter.get(0)[1].toString()), EIGHT_Q);
      List<StockQuarterId> ids = new ArrayList<>();
      for (DateQuarter dq : dqs) {
        StockQuarterId id = new StockQuarterId();
        id.setNumber(stockNum);
        id.setQuarter(dq.getQuarter());
        id.setYear(dq.getYear());
        ids.add(id);
      }
      List<Performance> performances = performanceRepo.findByIdIn(ids);
      boolean isIncrease = true;
      for (Performance performance : performances) {
        isIncrease = isIncrease && performance.getEps() > 0;
      }
      if (isIncrease) {
        results.add(stockNum);
      }
    }

    return results;
  }

}
