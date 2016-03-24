package stock.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class CashFlowQ {


  @EmbeddedId
  private StockQuarterId id;
  private Long operation;
  private Long investin;

  public StockQuarterId getId() {
    return id;
  }

  public void setId(StockQuarterId id) {
    this.id = id;
  }

  public Long getOperation() {
    return operation;
  }

  public void setOperation(Long operation) {
    this.operation = operation;
  }

  public Long getInvestin() {
    return investin;
  }

  public void setInvestin(Long investin) {
    this.investin = investin;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((investin == null) ? 0 : investin.hashCode());
    result = prime * result + ((operation == null) ? 0 : operation.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    CashFlowQ other = (CashFlowQ) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (investin == null) {
      if (other.investin != null) return false;
    } else if (!investin.equals(other.investin)) return false;
    if (operation == null) {
      if (other.operation != null) return false;
    } else if (!operation.equals(other.operation)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "CashFlow [id=" + id + ", operation=" + operation + ", investin=" + investin + "]";
  }


}
