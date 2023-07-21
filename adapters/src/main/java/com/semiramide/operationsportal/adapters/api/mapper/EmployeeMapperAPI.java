package com.semiramide.operationsportal.adapters.api.mapper;

import com.semiramide.operationsportal.adapters.api.dto.EmployeeDtoAPI;
import com.semiramide.operationsportal.core.entity.Employee;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface EmployeeMapperAPI {

  EmployeeMapperAPI INSTANCE = Mappers.getMapper(EmployeeMapperAPI.class);

  List<EmployeeDtoAPI> employeeListToEmployeeDtoAPIList(List<Employee> employeeList);

  EmployeeDtoAPI employeeToEmployeeDtoAPI(Employee employee);

  List<Employee> employeeDtoAPIListToEmployeeList(List<EmployeeDtoAPI> employeeDtoAPIList);

  Employee employeeDtoAPIToEmployee(EmployeeDtoAPI employeeDtoAPI);
}
