package stock.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="balancedSheetY")
public class BalancedSheetY {


  @Id
  Integer year;
  Integer currentAsset;
  Integer longTermInvestment;
  Integer fixedAsset;
  Integer otherAsset;
  Integer totalAsset;
  Integer currentLiability;
  Integer longTermLiability;
  Integer otherLiability;
  Integer totalIndebtedness;
  Integer totalShareholdersEquity;
}
