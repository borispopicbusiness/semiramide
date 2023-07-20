package com.deavensoft.operationsportal.core.usecase;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.entity.ProjectEmployee;
import java.util.List;
import java.util.UUID;

public interface ProjectEmployeeUseCase {

  List<ProjectEmployee> findAll();

  ProjectEmployee save(ProjectEmployee projectEmployee);

  void deleteById(UUID id);

  void deleteByProjectIdAndEmployeeId(UUID projectId, UUID employeeId);

  List<ProjectEmployee> findByLoggedUser(UUID parentId);

  List<ProjectEmployee> findByProjectId(UUID projectId);

  List<Employee> findAssignedEmployees(UUID projectId);

  List<Employee> findNonAssignedEmployees(UUID projectId);
}
