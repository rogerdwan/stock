package stock.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Earnings;
import stock.db.entity.StockMonthId;

@Repository
public interface EarningsRepo extends CrudRepository<Earnings, StockMonthId> {

}
