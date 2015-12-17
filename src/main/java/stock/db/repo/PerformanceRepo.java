package stock.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Performance;
import stock.db.entity.StockQuarterId;

@Repository
public interface PerformanceRepo extends CrudRepository<Performance, StockQuarterId> {

  // @Query(value="select a from Performance a where a.id.number = ?1")
  public List<Performance> findByIdIn(List<StockQuarterId> ids);

  @Query(value = "select year, max(quarter) from (select * from performance where year = (select max(year) from performance)) as a where number = ?1 group by year", nativeQuery = true)
  public List<Object[]> getMaxQuarterByStockNum(Integer stockNum);
}
