package stock.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Earnings;
import stock.db.entity.IncomeStatementQ;
import stock.db.entity.StockQuarterId;

@Repository
public interface IncomeStatementQRepo extends CrudRepository<IncomeStatementQ, StockQuarterId> {

  @Query(value = "select max(month) from (select * from income_statementq where year = (select max(year) from income_statementq)) as a where number = ?1", nativeQuery = true)
  public Integer getMaxMonthByStockNum(Integer stockNum);

  @Query(value = "select max(year) from income_statementq where number = ?1", nativeQuery = true)
  public Integer getMaxYearByStockNum(Integer stockNum);

  public List<IncomeStatementQ> findByIdIn(List<StockQuarterId> ids);

  public List<IncomeStatementQ> findByIdInOrderByIdYearDescIdQuarterDesc(List<StockQuarterId> ids);

}
