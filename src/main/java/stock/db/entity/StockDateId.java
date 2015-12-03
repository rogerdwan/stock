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
  @Column(name = "date")
  String date;
  @Column(name = "number")
  Integer number;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    StockDateId other = (StockDateId) obj;
    if (date == null) {
      if (other.date != null) return false;
    } else if (!date.equals(other.date)) return false;
    if (number == null) {
      if (other.number != null) return false;
    } else if (!number.equals(other.number)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "PriceId [date=" + date + ", number=" + number + "]";
  }
}
