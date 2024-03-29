package com.semiramide.operationsportal.adapters.persistence.mapper;

import com.semiramide.operationsportal.adapters.persistence.dto.EmployeeDtoDB;
import com.semiramide.operationsportal.core.entity.Employee;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapperDB {

  EmployeeMapperDB INSTANCE = Mappers.getMapper(EmployeeMapperDB.class);

  List<EmployeeDtoDB> employeeListToEmployeeDtoDBList(List<Employee> employeeList);

  EmployeeDtoDB employeeToEmployeeDtoDB(Employee employee);

  List<Employee> employeeDtoDBListToEmployeeList(List<EmployeeDtoDB> employeeDtoDBList);

  Employee employeeDtoDBToEmployee(EmployeeDtoDB employeeDtoDB);
}
