package stock.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.BalancedSheetQ;
import stock.db.entity.StockQuarterId;

@Repository
public interface BalancedSheetQRepo extends CrudRepository<BalancedSheetQ, StockQuarterId> {

  @Query(value = "select max(month) from (select * from balanced_sheetq where year = (select max(year) from balanced_sheetq)) as a where number = ?1", nativeQuery = true)
  public Integer getMaxMonthByStockNum(Integer stockNum);

  @Query(value = "select max(year) from balanced_sheetq where number = ?1", nativeQuery = true)
  public Integer getMaxYearByStockNum(Integer stockNum);

  public List<BalancedSheetQ> findByIdIn(List<StockQuarterId> ids);
}
