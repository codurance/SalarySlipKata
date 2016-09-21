package SalarySlipKata.domain;

import static java.lang.String.*;

import java.util.Objects;

public class TaxDetails {
  private final GBP taxFreeAllowance;
  private final GBP taxableIncome;
  private final GBP taxPayable;

  public TaxDetails(GBP taxFreeAllowance, GBP taxableIncome, GBP taxPayable) {
    this.taxFreeAllowance = taxFreeAllowance;
    this.taxableIncome = taxableIncome;
    this.taxPayable = taxPayable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaxDetails that = (TaxDetails) o;
    return Objects.equals(taxFreeAllowance, that.taxFreeAllowance) &&
        Objects.equals(taxableIncome, that.taxableIncome) &&
        Objects.equals(taxPayable, that.taxPayable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taxFreeAllowance, taxableIncome, taxPayable);
  }

  @Override
  public String toString() {
    return format("taxFreeAllowance=%s, taxableIncome=%s, taxPayable=%s",
        taxFreeAllowance, taxableIncome, taxPayable);
  }
}
