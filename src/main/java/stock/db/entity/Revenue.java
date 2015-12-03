package stock.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Revenue {

  @EmbeddedId
  private StockDateId id;
  private Integer revenue;
  private Integer increaseM;
  private Integer lastYear;
  private Integer increaseY;
  private Integer totalRevenueY;
  private Integer totalIncreaseY;

  public StockDateId getId() {
    return id;
  }

  public void setId(StockDateId id) {
    this.id = id;
  }

  public Integer getRevenue() {
    return revenue;
  }

  public void setRevenue(Integer revenue) {
    this.revenue = revenue;
  }

  public Integer getIncreaseM() {
    return increaseM;
  }

  public void setIncreaseM(Integer increaseM) {
    this.increaseM = increaseM;
  }

  public Integer getLastYear() {
    return lastYear;
  }

  public void setLastYear(Integer lastYear) {
    this.lastYear = lastYear;
  }

  public Integer getIncreaseY() {
    return increaseY;
  }

  public void setIncreaseY(Integer increaseY) {
    this.increaseY = increaseY;
  }

  public Integer getTotalRevenueY() {
    return totalRevenueY;
  }

  public void setTotalRevenueY(Integer totalRevenueY) {
    this.totalRevenueY = totalRevenueY;
  }

  public Integer getTotalIncreaseY() {
    return totalIncreaseY;
  }

  public void setTotalIncreaseY(Integer totalIncreaseY) {
    this.totalIncreaseY = totalIncreaseY;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((increaseM == null) ? 0 : increaseM.hashCode());
    result = prime * result + ((increaseY == null) ? 0 : increaseY.hashCode());
    result = prime * result + ((lastYear == null) ? 0 : lastYear.hashCode());
    result = prime * result + ((revenue == null) ? 0 : revenue.hashCode());
    result = prime * result + ((totalIncreaseY == null) ? 0 : totalIncreaseY.hashCode());
    result = prime * result + ((totalRevenueY == null) ? 0 : totalRevenueY.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Revenue other = (Revenue) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (increaseM == null) {
      if (other.increaseM != null) return false;
    } else if (!increaseM.equals(other.increaseM)) return false;
    if (increaseY == null) {
      if (other.increaseY != null) return false;
    } else if (!increaseY.equals(other.increaseY)) return false;
    if (lastYear == null) {
      if (other.lastYear != null) return false;
    } else if (!lastYear.equals(other.lastYear)) return false;
    if (revenue == null) {
      if (other.revenue != null) return false;
    } else if (!revenue.equals(other.revenue)) return false;
    if (totalIncreaseY == null) {
      if (other.totalIncreaseY != null) return false;
    } else if (!totalIncreaseY.equals(other.totalIncreaseY)) return false;
    if (totalRevenueY == null) {
      if (other.totalRevenueY != null) return false;
    } else if (!totalRevenueY.equals(other.totalRevenueY)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Revenue [id=" + id + ", revenue=" + revenue + ", increaseM=" + increaseM + ", lastYear="
        + lastYear + ", increaseY=" + increaseY + ", totalRevenueY=" + totalRevenueY
        + ", totalIncreaseY=" + totalIncreaseY + "]";
  }

}
