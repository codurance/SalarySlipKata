package SalarySlipKata.application_service;

import SalarySlipKata.domain.EmployeeId;
import SalarySlipKata.infrastructure.InMemoryEmployeeRepository;
import SalarySlipKata.infrastructure.SalarySlipPrinter;

public class SalarySlipApplication {
  private SalarySlipPrinter salarySlipPrinter;
  private InMemoryEmployeeRepository inMemoryEmployeeRepository;

  public SalarySlipApplication(
      InMemoryEmployeeRepository inMemoryEmployeeRepository,
      SalarySlipPrinter salarySlipPrinter) {
    this.inMemoryEmployeeRepository = inMemoryEmployeeRepository;
    this.salarySlipPrinter = salarySlipPrinter;
  }

  public void printFor(EmployeeId id, String salaryPeriod) {
    salarySlipPrinter.print(inMemoryEmployeeRepository.get(id), salaryPeriod);
  }
}
