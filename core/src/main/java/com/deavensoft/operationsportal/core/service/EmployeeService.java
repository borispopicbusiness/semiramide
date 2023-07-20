package com.deavensoft.operationsportal.core.service;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.exception.EmployeeNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {

  List<Employee> findAllEmployees();

  List<Employee> findAllEmployeesByIds(List<UUID> employeeIds);

  Optional<Employee> findEmployeeByEmail(String email);

  Employee addEmployee(Employee employee);

  Employee updateEmployee(Employee employee);

  void changePassword(String principalId, String newPassword);

  Optional<Employee> findEmployeeById(UUID id);

  void deleteEmployeeById(UUID id) throws EmployeeNotFoundException;

  void deleteAllEmployees();

  Optional<Employee> findEmployeeByPrincipalId(String name);

  Employee calculateFreeDaysLeft(Integer daysTaken, Employee employee);
}
