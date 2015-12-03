package stock.db.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Performance {

  @EmbeddedId
  private StockQuarterId id;

  @Column
  private Double rps;
  @Column
  private Double eps;

  public StockQuarterId getId() {
    return id;
  }

  public void setId(StockQuarterId id) {
    this.id = id;
  }

  public Double getRps() {
    return rps;
  }

  public void setRps(Double rps) {
    this.rps = rps;
  }

  public Double getEps() {
    return eps;
  }

  public void setEps(Double eps) {
    this.eps = eps;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((eps == null) ? 0 : eps.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((rps == null) ? 0 : rps.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Performance other = (Performance) obj;
    if (eps == null) {
      if (other.eps != null) return false;
    } else if (!eps.equals(other.eps)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (rps == null) {
      if (other.rps != null) return false;
    } else if (!rps.equals(other.rps)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "Peformance [id=" + id + ", rps=" + rps + ", eps=" + eps + "]";
  }

}
