package SalarySlipKata.infrastructure;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import SalarySlipKata.domain.Employee;
import SalarySlipKata.domain.EmployeeId;

public class StandardSalarySlipPrinterShould {

  private Console console;
  private Clock clock;

  private StandardSalarySlipPrinter standardSalarySlipPrinter;

  @Before
  public void setUp() throws Exception {
    console = mock(Console.class);
    clock = mock(Clock.class);

    standardSalarySlipPrinter = new StandardSalarySlipPrinter(clock, console);
  }

  @Test public void
  print_a_salary_slip() {
    when(clock.todayAsString()).thenReturn("01 Sep 2016");
    Employee employee = new Employee(new EmployeeId(12345), "John J Doe", 24000);

    standardSalarySlipPrinter.print(employee, "Sep 2016");

    verify(console).print(
      "Date: 01 Sep 2016             Salary for period: Sep 2016\n" +
      "                                                         \n" +
      "Employee ID: 12345            Employee Name: John J Doe  \n" +
      "                                                         \n" +
      "EARNINGS                                                 \n" +
      "Basic            £2000.00                                \n"
    );
  } 

}
