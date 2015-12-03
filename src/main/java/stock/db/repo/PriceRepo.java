package stock.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Price;

@Repository
public interface PriceRepo extends CrudRepository<Price, Integer> {

}
