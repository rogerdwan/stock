package stock.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class IncomeStatementQ {

  @EmbeddedId
  private StockQuarterId id;
  private Long netSales;
  private Long preTaxIncome;
  private Long ebit;
  private Long ebitda;
  private Long waCapital;
  private Long gross;
  private Long netIncome;

  public Long getNetIncome() {
    return netIncome;
  }

  public void setNetIncome(Long netIncome) {
    this.netIncome = netIncome;
  }

  public StockQuarterId getId() {
    return id;
  }

  public void setId(StockQuarterId id) {
    this.id = id;
  }

  public Long getNetSales() {
    return netSales;
  }

  public void setNetSales(Long netSales) {
    this.netSales = netSales;
  }

  public Long getPreTaxIncome() {
    return preTaxIncome;
  }

  public void setPreTaxIncome(Long preTaxIncome) {
    this.preTaxIncome = preTaxIncome;
  }

  public Long getEbit() {
    return ebit;
  }

  public void setEbit(Long ebit) {
    this.ebit = ebit;
  }

  public Long getEbitda() {
    return ebitda;
  }

  public void setEbitda(Long ebitda) {
    this.ebitda = ebitda;
  }

  public Long getWaCapital() {
    return waCapital;
  }

  public void setWaCapital(Long waCapital) {
    this.waCapital = waCapital;
  }

  public Long getGross() {
    return gross;
  }

  public void setGross(Long gross) {
    this.gross = gross;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((ebit == null) ? 0 : ebit.hashCode());
    result = prime * result + ((ebitda == null) ? 0 : ebitda.hashCode());
    result = prime * result + ((gross == null) ? 0 : gross.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((netIncome == null) ? 0 : netIncome.hashCode());
    result = prime * result + ((netSales == null) ? 0 : netSales.hashCode());
    result = prime * result + ((preTaxIncome == null) ? 0 : preTaxIncome.hashCode());
    result = prime * result + ((waCapital == null) ? 0 : waCapital.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    IncomeStatementQ other = (IncomeStatementQ) obj;
    if (ebit == null) {
      if (other.ebit != null) return false;
    } else if (!ebit.equals(other.ebit)) return false;
    if (ebitda == null) {
      if (other.ebitda != null) return false;
    } else if (!ebitda.equals(other.ebitda)) return false;
    if (gross == null) {
      if (other.gross != null) return false;
    } else if (!gross.equals(other.gross)) return false;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (netIncome == null) {
      if (other.netIncome != null) return false;
    } else if (!netIncome.equals(other.netIncome)) return false;
    if (netSales == null) {
      if (other.netSales != null) return false;
    } else if (!netSales.equals(other.netSales)) return false;
    if (preTaxIncome == null) {
      if (other.preTaxIncome != null) return false;
    } else if (!preTaxIncome.equals(other.preTaxIncome)) return false;
    if (waCapital == null) {
      if (other.waCapital != null) return false;
    } else if (!waCapital.equals(other.waCapital)) return false;
    return true;
  }

  @Override
  public String toString() {
    return "IncomeStatementQ [id=" + id + ", netSales=" + netSales + ", preTaxIncome="
        + preTaxIncome + ", ebit=" + ebit + ", ebitda=" + ebitda + ", waCapital=" + waCapital
        + ", gross=" + gross + ", netIncome=" + netIncome + "]";
  }

}
