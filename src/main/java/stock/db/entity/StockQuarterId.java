package stock.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StockQuarterId implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3009788005461154881L;
  /**
   * 
   */
  @Column(name = "year")
  Integer year;
  @Column(name = "quarter")
  Integer quarter;
  @Column(name = "number")
  Integer number;

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getQuarter() {
    return quarter;
  }

  public void setQuarter(Integer quarter) {
    this.quarter = quarter;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((quarter == null) ? 0 : quarter.hashCode());
    result = prime * result + ((year == null) ? 0 : year.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    StockQuarterId other = (StockQuarterId) obj;
    if (number == null) {
      if (other.number != null) return false;
    } else if (!number.equals(other.number)) return false;
    if (quarter == null) {
      if (other.quarter != null) return false;
    } else if (!quarter.equals(other.quarter)) return false;
    if (year == null) {
      if (other.year != null) return false;
    } else if (!year.equals(other.year)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "StockQuarterId [year=" + year + ", quarter=" + quarter + ", number=" + number + "]";
  }

}
