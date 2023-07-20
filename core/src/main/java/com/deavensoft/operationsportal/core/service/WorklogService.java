package com.deavensoft.operationsportal.core.service;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.entity.Project;
import com.deavensoft.operationsportal.core.entity.Worklog;
import com.deavensoft.operationsportal.core.entity.enums.WorklogType;
import com.deavensoft.operationsportal.core.exception.InvalidArgumentException;
import com.deavensoft.operationsportal.core.util.WorklogExportDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface WorklogService {

  List<WorklogExportDTO> prepareWorklogsForExport(
      List<Worklog> worklogs, List<Employee> employees, List<Project> projects);

  List<Worklog> findAllWorklogs();

  Worklog addWorklog(Worklog worklog) throws InvalidArgumentException;

  void updateWorklog(Worklog worklog) throws InvalidArgumentException;

  Optional<Worklog> findWorklogById(UUID id);

  List<Worklog> findWorklogByEmployeeId(UUID employeeId);

  List<Worklog> findWorklogByEmployeeIdAndCreationDate(
      UUID employeeId, LocalDate creationDate, int page);

  List<Worklog> listWorklogByAnyCriteria(Map<String, String[]> criteria);

  void deleteWorklogById(UUID id);

  void deleteByEmployeeId(UUID employeeId);

  void deleteAllWorklogs();

  List<WorklogType> listWorklogTypes();

  int findNumberOfWorklogs(UUID employeeId, LocalDate creationDate);

  List<Worklog> listWorklogByAllCriteria(Map<String, String[]> criteria);

  boolean isWorklogsDateLocked(Worklog worklog);

  boolean isWorklogsDateInTheFuture(Worklog worklog);
}
