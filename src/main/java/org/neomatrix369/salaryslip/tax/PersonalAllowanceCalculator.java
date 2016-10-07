package org.neomatrix369.salaryslip.tax;

import org.neomatrix369.salaryslip.components.Money;

public class PersonalAllowanceCalculator {
  private static final Money FULL_PERSONAL_ALLOWANCE = new Money(11_000.00);
  private static final Money PERSONAL_ALLOWANCE_REDUCTION_THRESHOLD = new Money(100_000.00);
  private static final Money MAXIMUM_ALLOWED_EXCESS_FOR_REDUCTION = FULL_PERSONAL_ALLOWANCE.times(2);

  public Money taxFreeAllowance(Money annualSalary) {
    Money reduction = taxableExcessAfterReductionFor(annualSalary);

    return FULL_PERSONAL_ALLOWANCE.subtract(reduction);
  }

  public Money taxableExcessAfterReductionFor(Money annualSalary) {
    Money excessOver100K = calculateExcessOver100K(annualSalary);

    if (excessOver100K.isGreaterThan(MAXIMUM_ALLOWED_EXCESS_FOR_REDUCTION)) {
      return FULL_PERSONAL_ALLOWANCE;
    }

    return reduceBy_1_PoundForEvery_2_PoundsEarned(excessOver100K);
  }

  private Money reduceBy_1_PoundForEvery_2_PoundsEarned(Money amount) {
    return amount.divisionBy(2);
  }

  private Money calculateExcessOver100K(Money annualSalary) {
    return annualSalary.subtract(PERSONAL_ALLOWANCE_REDUCTION_THRESHOLD);
  }
}
