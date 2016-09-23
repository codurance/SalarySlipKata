package SalarySlipKata.domain_service;

import static SalarySlipKata.domain.Money.zero;

import SalarySlipKata.domain.Money;
import SalarySlipKata.domain.TaxDetails;

public class TaxCalculator {

  private enum TaxBands {
    ADDITIONAL_TAX(new Money(150_000.00), 0.45),
    HIGHER_TAX    (new Money(43_000.00),  0.40),
    BASIC_TAX     (new Money(11_000.00),  0.20);

    private final Money limit;
    private final double rate;

    TaxBands(Money limit, double rate) {
      this.limit = limit;
      this.rate = rate;
    }
  }

  private static final Money PERSONAL_ALLOWANCE = TaxBands.BASIC_TAX.limit;
  private static final Money
      UPPER_LIMIT_FOR_PERSONAL_ALLOWANCE_REDUCTION_RULE = new Money(100_000.00);

  private static final int TWELVE_MONTHS = 12;

  public TaxDetails calculateMonthlyTaxDetailsFor(Money annualSalary) {
    return new TaxDetails(
        calculateMonthlyTaxFreeAllowanceFor(annualSalary),
        calculateMonthlyTaxableIncomeFor(annualSalary),
        calculateMonthlyTaxPayableFor(annualSalary)
    );
  }

  private Money calculateMonthlyTaxFreeAllowanceFor(Money annualSalary) {
    return calculateTaxFreeAllowanceFor(annualSalary).divideBy(TWELVE_MONTHS);
  }

  private Money calculateTaxFreeAllowanceFor(Money annualSalary) {
    final Money differenceAbove100k = calculateDifferenceAbove100kOf(annualSalary);

    return differenceAbove100k.isGreaterThanZero()
                ? reduce1PoundForEvery2PoundsEarnedOn(differenceAbove100k)
                : PERSONAL_ALLOWANCE;
  }

  private Money calculateMonthlyTaxableIncomeFor(Money annualSalary) {
    return calculateTaxableIncomeFor(annualSalary).divideBy(TWELVE_MONTHS);
  }

  private Money calculateTaxableIncomeFor(Money annualSalary) {
    return annualSalary.minus(calculateTaxFreeAllowanceFor(annualSalary));
  }

  private Money calculateMonthlyTaxPayableFor(Money annualSalary) {
    return calculateTaxPayableFor(annualSalary).divideBy(TWELVE_MONTHS);
  }

  private Money calculateTaxPayableFor(Money originalAnnualSalary) {
    Money annualSalary = new Money(originalAnnualSalary);
    Money adjustmentDueToPersonalAllowanceReductionRule = zero();
    Money contributions = zero();

    for (TaxBands taxBand: TaxBands.values()) {
      Money difference = annualSalary
          .minus(taxBand.limit)
          .plus(adjustmentDueToPersonalAllowanceReductionRule);
      contributions = contributions.plus(difference.multiplyBy(taxBand.rate));
      adjustmentDueToPersonalAllowanceReductionRule =
          calculateAdjustmentDueTo100KPersonalAllowanceReductionRuleWith(annualSalary);
      annualSalary = annualSalary.minus(difference);
    }

    return contributions;
  }

  private Money calculateAdjustmentDueTo100KPersonalAllowanceReductionRuleWith(Money annualSalary) {
    final Money differenceAbove100K = calculateDifferenceAbove100kOf(annualSalary);

    if (differenceAbove100K.isGreaterThanZero()) {
      final Money actualPersonalAllowance = reduce1PoundForEvery2PoundsEarnedOn(differenceAbove100K);
      return actualPersonalAllowance.isGreaterThanZero()
          ? actualPersonalAllowance
          : PERSONAL_ALLOWANCE;
    }

    return zero();
  }

  private Money calculateDifferenceAbove100kOf(Money annualSalary) {
    return annualSalary.minus(UPPER_LIMIT_FOR_PERSONAL_ALLOWANCE_REDUCTION_RULE);
  }

  private Money reduce1PoundForEvery2PoundsEarnedOn(Money differenceAbove100k) {
    Money halfOfTheDifference = differenceAbove100k.divideBy(2);
    return PERSONAL_ALLOWANCE.minus(halfOfTheDifference);
  }
}
