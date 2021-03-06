package stock.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StockDateId implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8821248668806424631L;
  @Column(name = "year")
  Integer year;
  @Column(name = "month")
  Integer month;
  @Column(name = "day")
  Integer day;
  @Column(name = "number")
  Integer number;

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  @Override
  public String toString() {
    return "StockDateId [year=" + year + ", month=" + month + ", day=" + day + ", number=" + number
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((day == null) ? 0 : day.hashCode());
    result = prime * result + ((month == null) ? 0 : month.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((year == null) ? 0 : year.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    StockDateId other = (StockDateId) obj;
    if (day == null) {
      if (other.day != null) return false;
    } else if (!day.equals(other.day)) return false;
    if (month == null) {
      if (other.month != null) return false;
    } else if (!month.equals(other.month)) return false;
    if (number == null) {
      if (other.number != null) return false;
    } else if (!number.equals(other.number)) return false;
    if (year == null) {
      if (other.year != null) return false;
    } else if (!year.equals(other.year)) return false;
    return true;
  }

}
