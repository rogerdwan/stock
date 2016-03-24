package stock.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class CapitalStructureQ {

  @EmbeddedId
  public StockQuarterId id;
  public Double receivablesTurnoverRatio;
  public Double inventoryTurnoverRatio;
  public Double netIncomeRatio;

  public StockQuarterId getId() {
    return id;
  }

  public void setId(StockQuarterId id) {
    this.id = id;
  }

  public Double getNetIncomeRatio() {
    return netIncomeRatio;
  }

  public void setNetIncomeRatio(Double netIncomeRatio) {
    this.netIncomeRatio = netIncomeRatio;
  }

  public Double getReceivablesTurnoverRatio() {
    return receivablesTurnoverRatio;
  }

  public void setReceivablesTurnoverRatio(Double receivablesTurnoverRatio) {
    this.receivablesTurnoverRatio = receivablesTurnoverRatio;
  }

  public Double getInventoryTurnoverRatio() {
    return inventoryTurnoverRatio;
  }

  public void setInventoryTurnoverRatio(Double inventoryTurnoverRatio) {
    this.inventoryTurnoverRatio = inventoryTurnoverRatio;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result =
        prime * result + ((inventoryTurnoverRatio == null) ? 0 : inventoryTurnoverRatio.hashCode());
    result = prime * result + ((netIncomeRatio == null) ? 0 : netIncomeRatio.hashCode());
    result = prime * result
        + ((receivablesTurnoverRatio == null) ? 0 : receivablesTurnoverRatio.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    CapitalStructureQ other = (CapitalStructureQ) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (inventoryTurnoverRatio == null) {
      if (other.inventoryTurnoverRatio != null) return false;
    } else if (!inventoryTurnoverRatio.equals(other.inventoryTurnoverRatio)) return false;
    if (netIncomeRatio == null) {
      if (other.netIncomeRatio != null) return false;
    } else if (!netIncomeRatio.equals(other.netIncomeRatio)) return false;
    if (receivablesTurnoverRatio == null) {
      if (other.receivablesTurnoverRatio != null) return false;
    } else if (!receivablesTurnoverRatio.equals(other.receivablesTurnoverRatio)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "CapitalStructureQ [id=" + id + ", receivablesTurnoverRatio=" + receivablesTurnoverRatio
        + ", inventoryTurnoverRatio=" + inventoryTurnoverRatio + ", netIncomeRatio="
        + netIncomeRatio + "]";
  }


}
