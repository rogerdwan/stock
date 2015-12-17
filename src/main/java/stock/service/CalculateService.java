package stock.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {

  @Inject
  private PerformanceService performanceService;
  @Inject
  private EarningsService earningsService;

  @PostConstruct
  public void getStockByPerformanceAndEarnings() {
    List<Integer> stockNum = performanceService.getContiniousPlusEightQ();
    System.out.println(stockNum.size());
    earningsService.getContinousIncreaseStockNum(stockNum);
  }

}
