package stock.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class BalancedSheetQ {

  @EmbeddedId
  public StockQuarterId id;
  public Long intangibleAssets;
  public Long totalAssets;
  public Long currentAssets;

  public StockQuarterId getId() {
    return id;
  }

  public void setId(StockQuarterId id) {
    this.id = id;
  }

  public Long getIntangibleAssets() {
    return intangibleAssets;
  }

  public void setIntangibleAssets(Long intangibleAssets) {
    this.intangibleAssets = intangibleAssets;
  }

  public Long getTotalAssets() {
    return totalAssets;
  }

  public void setTotalAssets(Long totalAssets) {
    this.totalAssets = totalAssets;
  }

  public Long getCurrentAssets() {
    return currentAssets;
  }

  public void setCurrentAssets(Long currentAssets) {
    this.currentAssets = currentAssets;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((currentAssets == null) ? 0 : currentAssets.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((intangibleAssets == null) ? 0 : intangibleAssets.hashCode());
    result = prime * result + ((totalAssets == null) ? 0 : totalAssets.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    BalancedSheetQ other = (BalancedSheetQ) obj;
    if (currentAssets == null) {
      if (other.currentAssets != null) return false;
    } else if (!currentAssets.equals(other.currentAssets)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (intangibleAssets == null) {
      if (other.intangibleAssets != null) return false;
    } else if (!intangibleAssets.equals(other.intangibleAssets)) return false;
    if (totalAssets == null) {
      if (other.totalAssets != null) return false;
    } else if (!totalAssets.equals(other.totalAssets)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "BalancedSheetQ [id=" + id + ", intangibleAssets=" + intangibleAssets + ", totalAssets="
        + totalAssets + ", currentAssets=" + currentAssets + "]";
  }

}
