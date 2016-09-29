package com.codurance.salaryslip.tax;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static java.util.Arrays.asList;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.codurance.salaryslip.components.Money;
import com.codurance.salaryslip.personal_allowance.PersonalAllowanceCalculator;

@RunWith(Parameterized.class)
public class HigherTaxWithPersonalAllowanceReductionRuleBandShould {
  private Money expectedHigherTaxPayable;
  private Money annualSalary;

  private TaxBand higherTaxBand = new StandardTaxBand(new Money(43_000.00), new Money(150_000.00), 0.40);

  private PersonalAllowanceCalculator personalAllowanceCalculator = new PersonalAllowanceCalculator();
  private TaxBand higherTaxWithPersonalAllowanceReductionRuleBand;

  @Parameterized.Parameters(name = "For an annual salary of {0}, the higher tax payable is {1}")
  public static Collection<Object[]> data() {
    return asList(
        new Object[][] {
            { annualSalaryOf( 40_000.00), expectedTaxPayableOf(     0.00) },
            { annualSalaryOf( 43_000.00), expectedTaxPayableOf(     0.00) },
            { annualSalaryOf( 50_000.00), expectedTaxPayableOf( 2_800.00) },
            { annualSalaryOf(100_000.00), expectedTaxPayableOf(22_800.00) },
            { annualSalaryOf(111_000.00), expectedTaxPayableOf(29_400.00) },
            { annualSalaryOf(122_000.00), expectedTaxPayableOf(36_000.00) },
            { annualSalaryOf(150_000.00), expectedTaxPayableOf(47_200.00) },
            { annualSalaryOf(160_000.00), expectedTaxPayableOf(47_200.00) }
        }
    );
  }

  @Before
  public void initialise() {
    higherTaxWithPersonalAllowanceReductionRuleBand =
          new HigherTaxWithPersonalAllowanceReductionRuleBand(higherTaxBand, personalAllowanceCalculator);
  }

  private static Money annualSalaryOf(double amount) {
    return new Money(amount);
  }

  private static Money expectedTaxPayableOf(double amount) {
    return new Money(amount);
  }

  public HigherTaxWithPersonalAllowanceReductionRuleBandShould(
      Money annualSalary,
      Money expectedHigherTaxPayable) {
    this.annualSalary = annualSalary;
    this.expectedHigherTaxPayable = expectedHigherTaxPayable;
  }

  @Test public void
  return_the_tax_payable_for_an_annual_salary_applicable_for_higher_tax() {
    Money actualHigherTaxPayable =
        higherTaxWithPersonalAllowanceReductionRuleBand.calculateFrom(annualSalary);
    assertThat(actualHigherTaxPayable, is(expectedHigherTaxPayable));
  } 
}
