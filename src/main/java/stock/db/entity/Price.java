package stock.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Price {

  @EmbeddedId
  private StockDateId priceId;
  private Double price;

  public StockDateId getPriceId() {
    return priceId;
  }

  public void setPriceId(StockDateId priceId) {
    this.priceId = priceId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Price [priceId=" + priceId + ", price=" + price + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    result = prime * result + ((priceId == null) ? 0 : priceId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Price other = (Price) obj;
    if (price == null) {
      if (other.price != null) return false;
    } else if (!price.equals(other.price)) return false;
    if (priceId == null) {
      if (other.priceId != null) return false;
    } else if (!priceId.equals(other.priceId)) return false;
    return true;
  }

}
