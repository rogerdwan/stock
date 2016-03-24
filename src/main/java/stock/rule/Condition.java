package stock.rule;

public class Condition {

  private Comparator comparator;
  private Double condition;
  private Integer period;

  public Condition() {

  }

  public Condition(Integer period) {
    this.period = period;
  }

  public Condition(Comparator comparator, Double condition, Integer period) {
    super();
    this.comparator = comparator;
    this.condition = condition;
    this.period = period;
  }

  public Integer getPeriod() {
    return period;
  }

  public void setPeriod(Integer period) {
    this.period = period;
  }

  public Comparator getComparator() {
    return comparator;
  }

  public void setComparator(Comparator comparator) {
    this.comparator = comparator;
  }

  public Double getCondition() {
    return condition;
  }

  public void setCondition(Double condition) {
    this.condition = condition;
  }

  public enum Comparator {
    MORE, LESS;
  }
}
