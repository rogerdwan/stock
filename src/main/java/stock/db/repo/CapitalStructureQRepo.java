package stock.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.CapitalStructureQ;
import stock.db.entity.StockQuarterId;

@Repository
public interface CapitalStructureQRepo extends CrudRepository<CapitalStructureQ, StockQuarterId> {

  @Query(value = "select max(month) from (select * from capital_structureq where year = (select max(year) from capital_structureq)) as a where number = ?1", nativeQuery = true)
  public Integer getMaxMonthByStockNum(Integer stockNum);

  @Query(value = "select max(year) from capital_structureq where number = ?1", nativeQuery = true)
  public Integer getMaxYearByStockNum(Integer stockNum);

  public List<CapitalStructureQ> findByIdIn(List<StockQuarterId> ids);

}
