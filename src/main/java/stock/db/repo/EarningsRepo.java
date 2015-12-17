package stock.db.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Earnings;
import stock.db.entity.StockMonthId;

@Repository
public interface EarningsRepo extends CrudRepository<Earnings, StockMonthId> {

  @Query(value = "select max(month) from (select * from earnings where year = (select max(year) from earnings)) as a where number = ?1", nativeQuery = true)
  public Integer getMaxMonthByStockNum(Integer stockNum);

  @Query(value = "select max(year) from earnings where number = ?1", nativeQuery = true)
  public Integer getMaxYearByStockNum(Integer stockNum);

  @Query(value = "select increase_ratey from earnings where year =?1 and month = ?2 and number = ?3", nativeQuery = true)
  public Double getRates(Integer year1, Integer month1, Integer stockNum);
}
