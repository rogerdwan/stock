package stock.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Earnings {

  @EmbeddedId
  private StockDateId id;
  private Integer earnings;
  private Integer increaseQ;
  private Integer lastYear;
  private Integer increaseY;
  private Integer totalEarningsY;
  private Integer totalIncreaseY;

  public StockDateId getId() {
    return id;
  }

  public void setId(StockDateId id) {
    this.id = id;
  }

  public Integer getEarnings() {
    return earnings;
  }

  public void setEarnings(Integer earnings) {
    this.earnings = earnings;
  }

  public Integer getIncreaseQ() {
    return increaseQ;
  }

  public void setIncreaseQ(Integer increaseQ) {
    this.increaseQ = increaseQ;
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

  public Integer getTotalEarningsY() {
    return totalEarningsY;
  }

  public void setTotalEarningsY(Integer totalEarningsY) {
    this.totalEarningsY = totalEarningsY;
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
    result = prime * result + ((earnings == null) ? 0 : earnings.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((increaseQ == null) ? 0 : increaseQ.hashCode());
    result = prime * result + ((increaseY == null) ? 0 : increaseY.hashCode());
    result = prime * result + ((lastYear == null) ? 0 : lastYear.hashCode());
    result = prime * result + ((totalEarningsY == null) ? 0 : totalEarningsY.hashCode());
    result = prime * result + ((totalIncreaseY == null) ? 0 : totalIncreaseY.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Earnings other = (Earnings) obj;
    if (earnings == null) {
      if (other.earnings != null) return false;
    } else if (!earnings.equals(other.earnings)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (increaseQ == null) {
      if (other.increaseQ != null) return false;
    } else if (!increaseQ.equals(other.increaseQ)) return false;
    if (increaseY == null) {
      if (other.increaseY != null) return false;
    } else if (!increaseY.equals(other.increaseY)) return false;
    if (lastYear == null) {
      if (other.lastYear != null) return false;
    } else if (!lastYear.equals(other.lastYear)) return false;
    if (totalEarningsY == null) {
      if (other.totalEarningsY != null) return false;
    } else if (!totalEarningsY.equals(other.totalEarningsY)) return false;
    if (totalIncreaseY == null) {
      if (other.totalIncreaseY != null) return false;
    } else if (!totalIncreaseY.equals(other.totalIncreaseY)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Earnings [id=" + id + ", earnings=" + earnings + ", increaseQ=" + increaseQ
        + ", lastYear=" + lastYear + ", increaseY=" + increaseY + ", totalEarningsY="
        + totalEarningsY + ", totalIncreaseY=" + totalIncreaseY + "]";
  }


}
