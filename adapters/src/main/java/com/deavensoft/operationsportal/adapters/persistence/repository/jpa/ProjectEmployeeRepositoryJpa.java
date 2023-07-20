package com.deavensoft.operationsportal.adapters.persistence.repository.jpa;

import com.deavensoft.operationsportal.adapters.persistence.dto.ProjectEmployeeDtoDB;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEmployeeRepositoryJpa extends JpaRepository<ProjectEmployeeDtoDB, UUID> {

  List<ProjectEmployeeDtoDB> findByEmployeesId(UUID id);

  void deleteByEmployeesId(UUID employeeId);

  void deleteByProjectIdAndEmployeesId(UUID projectId, UUID employeeId);

  List<ProjectEmployeeDtoDB> findByProjectId(UUID id);
}
