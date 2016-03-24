package stock.rule;

import java.time.LocalDate;

public interface Rule {

  public boolean apply(Integer stockNum, Condition condition, LocalDate date);
}
