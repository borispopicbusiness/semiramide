package com.semiramide.operationsportal.core.repository;

import com.semiramide.operationsportal.core.entity.ProjectEmployee;
import java.util.List;
import java.util.UUID;

public interface ProjectEmployeeRepository {
  List<ProjectEmployee> findAll();

  ProjectEmployee save(ProjectEmployee projectEmployee);

  void deleteById(UUID id);

  void deleteByEmployeeId(UUID employeeId);

  void deleteByProjectIdAndEmployeeId(UUID projectID, UUID employeeId);

  List<ProjectEmployee> findByEmployeesId(UUID id);

  List<ProjectEmployee> findByProjectId(UUID id);
}
