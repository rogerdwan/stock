package stock.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Capital;
import stock.db.entity.StockYearId;

@Repository
public interface CapitalRepo extends CrudRepository<Capital, StockYearId> {


}
