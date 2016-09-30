package org.neomatrix369.salaryslip;

import static org.neomatrix369.salaryslip.SalarySlipBuilder.aSalarySlip;
import static org.neomatrix369.salaryslip.tax.TaxDetailsBuilder.aTaxDetails;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static java.util.Arrays.asList;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.neomatrix369.salaryslip.components.Money;
import org.neomatrix369.salaryslip.components.SalarySlip;
import org.neomatrix369.salaryslip.national_insurance.NationalInsuranceCalculator;
import org.neomatrix369.salaryslip.personal_allowance.PersonalAllowanceCalculator;

import org.neomatrix369.salaryslip.components.Employee;
import org.neomatrix369.salaryslip.tax.TaxCalculator;

@RunWith(Parameterized.class)
public class SalarySlipGeneratorShould {

  private SalarySlipGenerator salarySlipGenerator;

  private Money annualSalary;
  private SalarySlip salarySlip;

  public SalarySlipGeneratorShould(Money annualSalary, SalarySlip salarySlip) {
    this.annualSalary = annualSalary;
    this.salarySlip = salarySlip;
  }

  @Before
  public void setUp() throws Exception {
    NationalInsuranceCalculator nationalInsuranceCalculator = new NationalInsuranceCalculator();
    PersonalAllowanceCalculator personalAllowanceCalculator = new PersonalAllowanceCalculator();
    TaxCalculator taxCalculator = new TaxCalculator(personalAllowanceCalculator);

    salarySlipGenerator = new SalarySlipGenerator(nationalInsuranceCalculator, taxCalculator);
  }

  @Parameterized.Parameters(name = "For an annual salary of {0}, a monthly salary slip contains {1}")
  public static Collection<Object[]> data() {
    return asList(
        new Object[][] {
            {
                annualSalaryOf(24_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(24_000.00))
                    .withGrossSalary(monthly(2_000.00))
                    .withTaxDetails(aTaxDetails()
                        .withTaxFreeAllowance(monthly(916.67))
                        .withTaxableIncome(monthly(1_083.33))
                        .withTaxPayable(monthly(216.67))
                        .build()
                    )
                    .withNIContributions(monthly(159.40))
                    .build()
            },
            {
                annualSalaryOf(5_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(5_000.00))
                    .withGrossSalary(monthly(416.67))
                    .withTaxDetails(aTaxDetails()
                        .withTaxFreeAllowance(monthly(916.67))
                      .withTaxableIncome(monthly(0.00))
                      .withTaxPayable(monthly(0.00))
                      .build()
                )
                    .withNIContributions(monthly(0.00))
                .build()
            },
            {
                annualSalaryOf(8_060.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(8_060.00))
                    .withGrossSalary(monthly(671.67))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(916.67))
                      .withTaxableIncome(monthly(0.00))
                      .withTaxPayable(monthly(0.00))
                      .build()
                    )
                    .withNIContributions(monthly(0.00))
                    .build()
            },
            {
                annualSalaryOf(12_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(12_000.00))
                    .withGrossSalary(monthly(1_000.00))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(916.67))
                      .withTaxableIncome(monthly(83.33))
                      .withTaxPayable(monthly(16.67))
                      .build()
                    )
                    .withNIContributions(monthly(39.40))
                    .build()
            },
            {
                annualSalaryOf(40_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(40_000.00))
                    .withGrossSalary(monthly(3_333.33))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(916.67))
                      .withTaxableIncome(monthly(2_416.67))
                      .withTaxPayable(monthly(483.33))
                      .build()
                    )
                    .withNIContributions(monthly(319.40))
                    .build()
            },
            {
                annualSalaryOf(43_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(43_000.00))
                    .withGrossSalary(monthly(3_583.33))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(916.67))
                      .withTaxableIncome(monthly(2_666.67))
                      .withTaxPayable(monthly(533.33))
                      .build()
                    )
                    .withNIContributions(monthly(349.40))
                    .build()
            },
            {
                annualSalaryOf(60_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(60_000.00))
                    .withGrossSalary(monthly(5_000.00))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(916.67))
                      .withTaxableIncome(monthly(4_083.33))
                      .withTaxPayable(monthly(1_100.00))
                      .build()
                    )
                    .withNIContributions(monthly(377.73))
                    .build()
            },
            {
                annualSalaryOf(100_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(100_000.00))
                    .withGrossSalary(monthly(8_333.33))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(916.67))
                      .withTaxableIncome(monthly(7_416.67))
                      .withTaxPayable(monthly(2_433.33))
                      .build()
                    )
                    .withNIContributions(monthly(444.40))
                    .build()
            },
            {
                annualSalaryOf(105_500.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(105_500.00))
                    .withGrossSalary(monthly(8_791.67))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(687.50))
                      .withTaxableIncome(monthly(8_104.17))
                      .withTaxPayable(monthly(2_708.33))
                      .build()
                    )
                    .withNIContributions(monthly(453.57))
                    .build()
            },
            {
                annualSalaryOf(111_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(111_000.00))
                    .withGrossSalary(monthly(9_250.00))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(458.33))
                      .withTaxableIncome(monthly(8_791.67))
                      .withTaxPayable(monthly(2_983.33))
                      .build()
                    )
                    .withNIContributions(monthly(462.73))
                    .build()
            },
            {
                annualSalaryOf(122_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(122_000.00))
                    .withGrossSalary(monthly(10_166.67))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(0.00))
                      .withTaxableIncome(monthly(10_166.67))
                      .withTaxPayable(monthly(3_533.33))
                      .build()
                    )
                    .withNIContributions(monthly(481.07))
                    .build()
            },
            {
                annualSalaryOf(150_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(150_000.00))
                    .withGrossSalary(monthly(12_500.00))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(0.00))
                      .withTaxableIncome(monthly(12_500.00))
                      .withTaxPayable(monthly(4_466.67))
                      .build()
                    )
                    .withNIContributions(monthly(527.73))
                    .build()
            },
            {
                annualSalaryOf(160_000.00),
                aSalarySlip()
                    .withEmployee(employeeWithAnnualSalaryOf(160_000.00))
                    .withGrossSalary(monthly(13_333.33))
                    .withTaxDetails(aTaxDetails()
                      .withTaxFreeAllowance(monthly(0.00))
                      .withTaxableIncome(monthly(13_333.33))
                      .withTaxPayable(monthly(4_841.67))
                      .build()
                    )
                    .withNIContributions(monthly(544.40))
                    .build()
            }
        }
    );
  }

  private static Money monthly(double amount) {
    return new Money(amount);
  }

  private static Money annualSalaryOf(double amount) {
    return new Money(amount);
  }

  private static Employee employeeWithAnnualSalaryOf(double annualSalary) {
    return EmployeeBuilder.aEmployee()
                .withId(12345)
                .withName("John J Doe")
                .withAnnualSalary(new Money(annualSalary))
                .build();
  }

  @Test public void
  return_generated_monthly_salary_slip_for_a_given_annual_salary() {
    Employee employee = EmployeeBuilder.aEmployee()
                          .withId(12345)
                          .withName("John J Doe")
                          .withAnnualSalary(new Money(annualSalary))
                          .build();
    assertThat(
        salarySlipGenerator.generateFor(employee),
        is(salarySlip)
    );
  }
}
