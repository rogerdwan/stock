package stock.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Earnings {

  @EmbeddedId
  private StockMonthId id;
  private Long earnings;
  private Double increaseRateM;
  private Long lastYear;
  private Double increaseRateY;
  private Long totalEarningsY;
  private Double totalIncreaseRateY;

  public StockMonthId getId() {
    return id;
  }

  public void setId(StockMonthId id) {
    this.id = id;
  }

  public Long getEarnings() {
    return earnings;
  }

  public void setEarnings(Long earnings) {
    this.earnings = earnings;
  }

  public Double getIncreaseRateM() {
    return increaseRateM;
  }

  public void setIncreaseRateM(Double increaseRateM) {
    this.increaseRateM = increaseRateM;
  }

  public Long getLastYear() {
    return lastYear;
  }

  public void setLastYear(Long lastYear) {
    this.lastYear = lastYear;
  }

  public Double getIncreaseRateY() {
    return increaseRateY;
  }

  public void setIncreaseRateY(Double increaseRateY) {
    this.increaseRateY = increaseRateY;
  }

  public Long getTotalEarningsY() {
    return totalEarningsY;
  }

  public void setTotalEarningsY(Long totalEarningsY) {
    this.totalEarningsY = totalEarningsY;
  }

  public Double getTotalIncreaseRateY() {
    return totalIncreaseRateY;
  }

  public void setTotalIncreaseRateY(Double totalIncreaseRateY) {
    this.totalIncreaseRateY = totalIncreaseRateY;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((earnings == null) ? 0 : earnings.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((increaseRateM == null) ? 0 : increaseRateM.hashCode());
    result = prime * result + ((increaseRateY == null) ? 0 : increaseRateY.hashCode());
    result = prime * result + ((lastYear == null) ? 0 : lastYear.hashCode());
    result = prime * result + ((totalEarningsY == null) ? 0 : totalEarningsY.hashCode());
    result = prime * result + ((totalIncreaseRateY == null) ? 0 : totalIncreaseRateY.hashCode());
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
    if (increaseRateM == null) {
      if (other.increaseRateM != null) return false;
    } else if (!increaseRateM.equals(other.increaseRateM)) return false;
    if (increaseRateY == null) {
      if (other.increaseRateY != null) return false;
    } else if (!increaseRateY.equals(other.increaseRateY)) return false;
    if (lastYear == null) {
      if (other.lastYear != null) return false;
    } else if (!lastYear.equals(other.lastYear)) return false;
    if (totalEarningsY == null) {
      if (other.totalEarningsY != null) return false;
    } else if (!totalEarningsY.equals(other.totalEarningsY)) return false;
    if (totalIncreaseRateY == null) {
      if (other.totalIncreaseRateY != null) return false;
    } else if (!totalIncreaseRateY.equals(other.totalIncreaseRateY)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Earnings [id=" + id + ", earnings=" + earnings + ", increaseRateM=" + increaseRateM
        + ", lastYear=" + lastYear + ", increaseRateY=" + increaseRateY + ", totalEarningsY="
        + totalEarningsY + ", totalIncreaseRateY=" + totalIncreaseRateY + "]";
  }

}
