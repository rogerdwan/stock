package stock.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import stock.db.entity.StockMonthId;
import stock.db.repo.StockRepo;
import stock.prediction.EarningPrediction;
import stock.prediction.EpsPrediction;
import stock.prediction.NetIncomePrediction;
import stock.rule.BalancedSheetQRule;
import stock.rule.CapitalRule;
import stock.rule.CapitalStructrueQRule;
import stock.rule.CashFlowQRule;
import stock.rule.Condition;
import stock.rule.EpsRule;
import stock.rule.IncomeStatementQRule;

@Service
public class CalculateService {

  @Inject
  private PerformanceService performanceService;
  @Inject
  private EarningsService earningsService;
  @Inject
  private StockRepo stockRepo;
  @Inject
  private EpsRule epsRule;
  @Inject
  private CapitalRule capitalRule;
  @Inject
  private CapitalStructrueQRule capitalStructrueQRule;
  @Inject
  private IncomeStatementQRule incomeStatementQRule;
  @Inject
  private BalancedSheetQRule balancedSheetQRule;
  @Inject
  private CashFlowQRule cashFlowQRule;
  @Inject
  private EarningPrediction earningPrediction;
  @Inject
  private NetIncomePrediction netIncomePrediction;
  @Inject
  private EpsPrediction epsPrediction;

  @Scheduled(fixedDelay = 60 * 60 * 1000 * 24)
  public void getStockByPerformanceAndEarnings() {
    List<Integer> stockNums = stockRepo.getNumbers();
    List<Integer> needRemove = new ArrayList<>();
    for (Integer stockNum : stockNums) {
      if (!epsRule.apply(stockNum, new Condition(4), LocalDate.now().minusMonths(3))) {
        needRemove.add(stockNum);
      }
    }
    stockNums.removeAll(needRemove);
    needRemove.clear();
    for (Integer stockNum : stockNums) {
      if (!capitalRule.apply(stockNum, new Condition(3), LocalDate.now())) {
        needRemove.add(stockNum);
      }
    }
    stockNums.removeAll(needRemove);
    needRemove.clear();
    for (Integer stockNum : stockNums) {
      if (!cashFlowQRule.apply(stockNum, new Condition(36), LocalDate.now().minusMonths(1))) {
        needRemove.add(stockNum);
      }
    }
    stockNums.removeAll(needRemove);
    needRemove.clear();
    for (Integer stockNum : stockNums) {
      if (!capitalStructrueQRule.apply(stockNum, new Condition(2),
          LocalDate.now().minusMonths(3))) {
        needRemove.add(stockNum);
      }
    }
    stockNums.removeAll(needRemove);
    needRemove.clear();

    for (Integer stockNum : stockNums) {
      if (!incomeStatementQRule.apply(stockNum, new Condition(1), LocalDate.now().minusMonths(3))) {
        needRemove.add(stockNum);
      }
    }
    stockNums.removeAll(needRemove);
    needRemove.clear();
    for (Integer stockNum : stockNums) {
      if (!balancedSheetQRule.apply(stockNum, new Condition(1), LocalDate.now().minusMonths(3))) {
        needRemove.add(stockNum);
      }
    }
    stockNums.removeAll(needRemove);
    needRemove.clear();

    for (Integer stockNum : stockNums) {
      try {
        System.out.println(stockNum);
        Map<StockMonthId, Long> earninges =
            earningPrediction.predicYearTotal(stockNum, LocalDate.now().minusMonths(1));
        Long netIncomeLastYear =
            netIncomePrediction.predictLastYearTotal(stockNum, LocalDate.now(), earninges);
        System.out.println(String.format("%d, %d", stockNum,
            epsPrediction.predicTotalEps(stockNum, netIncomeLastYear, LocalDate.now())));
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
    }

    // List<Integer> stockNum = performanceService.getContiniousPlusNQ(0);
    // System.out.println(stockNum.size());
    // earningsService.getContinousSumIncreaseStockNum(stockNums, 2);
  }

}
