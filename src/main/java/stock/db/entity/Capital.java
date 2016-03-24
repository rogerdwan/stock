package stock.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Capital {

  @Id
  private StockYearId id;

  private Long cashIncrease;

  private Double cashRatio;

  private Long profitIncreate;

  private Double profitRatio;

  private Long other;

  private Double otherRatio;

  public StockYearId getId() {
    return id;
  }

  public void setId(StockYearId id) {
    this.id = id;
  }

  public Long getCashIncrease() {
    return cashIncrease;
  }

  public void setCashIncrease(Long cashIncrease) {
    this.cashIncrease = cashIncrease;
  }

  public Double getCashRatio() {
    return cashRatio;
  }

  public void setCashRatio(Double cashRatio) {
    this.cashRatio = cashRatio;
  }

  public Long getProfitIncreate() {
    return profitIncreate;
  }

  public void setProfitIncreate(Long profitIncreate) {
    this.profitIncreate = profitIncreate;
  }

  public Double getProfitRatio() {
    return profitRatio;
  }

  public void setProfitRatio(Double profitRatio) {
    this.profitRatio = profitRatio;
  }

  public Long getOther() {
    return other;
  }

  public void setOther(Long other) {
    this.other = other;
  }

  public Double getOtherRatio() {
    return otherRatio;
  }

  public void setOtherRatio(Double otherRatio) {
    this.otherRatio = otherRatio;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cashIncrease == null) ? 0 : cashIncrease.hashCode());
    result = prime * result + ((cashRatio == null) ? 0 : cashRatio.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((other == null) ? 0 : other.hashCode());
    result = prime * result + ((otherRatio == null) ? 0 : otherRatio.hashCode());
    result = prime * result + ((profitIncreate == null) ? 0 : profitIncreate.hashCode());
    result = prime * result + ((profitRatio == null) ? 0 : profitRatio.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Capital other = (Capital) obj;
    if (cashIncrease == null) {
      if (other.cashIncrease != null) return false;
    } else if (!cashIncrease.equals(other.cashIncrease)) return false;
    if (cashRatio == null) {
      if (other.cashRatio != null) return false;
    } else if (!cashRatio.equals(other.cashRatio)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (this.other == null) {
      if (other.other != null) return false;
    } else if (!this.other.equals(other.other)) return false;
    if (otherRatio == null) {
      if (other.otherRatio != null) return false;
    } else if (!otherRatio.equals(other.otherRatio)) return false;
    if (profitIncreate == null) {
      if (other.profitIncreate != null) return false;
    } else if (!profitIncreate.equals(other.profitIncreate)) return false;
    if (profitRatio == null) {
      if (other.profitRatio != null) return false;
    } else if (!profitRatio.equals(other.profitRatio)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Capital [id=" + id + ", cashIncrease=" + cashIncrease + ", cashRatio=" + cashRatio
        + ", profitIncreate=" + profitIncreate + ", profitRatio=" + profitRatio + ", other=" + other
        + ", otherRatio=" + otherRatio + "]";
  }

}
