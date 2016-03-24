package stock.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.CashFlowQ;
import stock.db.entity.StockQuarterId;

@Repository
public interface CashFlowQRepo extends CrudRepository<CashFlowQ, StockQuarterId> {

  @Query(value = "select max(month) from (select * from cash_flowq where year = (select max(year) from cash_flowq)) as a where number = ?1", nativeQuery = true)
  public Integer getMaxMonthByStockNum(Integer stockNum);

  @Query(value = "select max(year) from cash_flowq where number = ?1", nativeQuery = true)
  public Integer getMaxYearByStockNum(Integer stockNum);

  public List<CashFlowQ> findByIdIn(List<StockQuarterId> ids);

}
