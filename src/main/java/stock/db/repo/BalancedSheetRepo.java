package stock.db.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stock.db.entity.BalancedSheetY;

@Repository
public interface BalancedSheetRepo extends CrudRepository<BalancedSheetY, Long> {

}
