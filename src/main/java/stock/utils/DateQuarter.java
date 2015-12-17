package stock.utils;

public class DateQuarter {

  private Integer year;
  private Integer quarter;

  private DateQuarter(Integer year, Integer quarter) {
    this.year = year;
    this.quarter = quarter;
  }

  public static DateQuarter of(Integer year, Integer quarter) {
    return new DateQuarter(year, quarter);
  }

  public DateQuarter minusYear(Integer year) {
    Integer newYear = this.year - year;

    return DateQuarter.of(newYear, this.quarter);
  }

  public DateQuarter minusQuarter(Integer quarter) {
    Integer newQuarter = this.quarter - quarter;
    Integer newYear = this.year;
    while (newQuarter <= 0) {
      newQuarter += 4;
      newYear -= 1;
    }
    while (newQuarter > 4) {
      newQuarter -= 4;
      newYear += 1;
    }
    return DateQuarter.of(newYear, newQuarter);
  }

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

  @Override
  public String toString() {
    return "DateQuarter [year=" + year + ", quarter=" + quarter + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((quarter == null) ? 0 : quarter.hashCode());
    result = prime * result + ((year == null) ? 0 : year.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    DateQuarter other = (DateQuarter) obj;
    if (quarter == null) {
      if (other.quarter != null) return false;
    } else if (!quarter.equals(other.quarter)) return false;
    if (year == null) {
      if (other.year != null) return false;
    } else if (!year.equals(other.year)) return false;
    return true;
  }
}
