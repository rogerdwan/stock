package stock.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Performance;
import stock.db.entity.StockQuarterId;

@Repository
public interface PerformanceRepo extends CrudRepository<Performance, StockQuarterId> {

}
