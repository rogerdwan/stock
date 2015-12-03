package stock.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {

  @Id
  private Integer number;
  private String name;
  private Integer type;
  private Integer special;
  private String industry;

  @Override
  public String toString() {
    return "Stock [number=" + number + ", name=" + name + ", type=" + type + ", special=" + special
        + ", industry=" + industry + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((industry == null) ? 0 : industry.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((special == null) ? 0 : special.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Stock other = (Stock) obj;
    if (industry == null) {
      if (other.industry != null) return false;
    } else if (!industry.equals(other.industry)) return false;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    if (number == null) {
      if (other.number != null) return false;
    } else if (!number.equals(other.number)) return false;
    if (special == null) {
      if (other.special != null) return false;
    } else if (!special.equals(other.special)) return false;
    if (type == null) {
      if (other.type != null) return false;
    } else if (!type.equals(other.type)) return false;
    return true;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getSpecial() {
    return special;
  }

  public void setSpecial(Integer special) {
    this.special = special;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

}
