package stock.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.Stock;

@Repository
public interface StockRepo extends CrudRepository<Stock, Integer> {

  @Query(value = "select a.number from Stock a")
  public List<Integer> getNumbers();
}
