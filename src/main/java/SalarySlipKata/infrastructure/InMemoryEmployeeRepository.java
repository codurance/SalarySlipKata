package SalarySlipKata.infrastructure;

import java.util.HashMap;
import java.util.Map;

import SalarySlipKata.domain.Employee;
import SalarySlipKata.domain.EmployeeId;

public class InMemoryEmployeeRepository {
  private final Map<EmployeeId, Employee> employees = new HashMap<>();

  public void add(EmployeeId id, String name, int annualSalary) {
    employees.put(id, new Employee(id, name, annualSalary));
  }

  public Employee get(EmployeeId id) {
    return employees.get(id);
  }
}
