package stock.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Price;
import stock.db.entity.StockDateId;

@Repository
public interface PriceRepo extends CrudRepository<Price, StockDateId> {

}
