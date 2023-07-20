package com.deavensoft.operationsportal.core.usecase;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.entity.Project;
import com.deavensoft.operationsportal.core.entity.Worklog;
import com.deavensoft.operationsportal.core.entity.enums.WorklogType;
import com.deavensoft.operationsportal.core.exception.*;
import com.deavensoft.operationsportal.core.util.WorklogExportDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface WorklogUseCase {

    List<WorklogExportDTO> prepareWorklogsForExport(List<Worklog> worklogs, List<Employee> employees, List<Project> projects);

    Worklog addWorklog(Worklog worklog) throws EmployeeNotFoundException, NoProjectFoundException, InvalidArgumentException;

    Worklog addWorklog(UUID accessingEmployeeId, Worklog worklog) throws EmployeeNotFoundException, NoProjectFoundException, InvalidArgumentException;

    void updateWorklog(Worklog worklog) throws EmployeeNotFoundException, NoProjectFoundException, WorklogNotFoundException, InvalidArgumentException;

    void updateWorklog(UUID accessingEmployeeId, Worklog worklog) throws EmployeeNotFoundException, NoProjectFoundException, WorklogNotFoundException, InvalidArgumentException;

    List<Worklog> listEmployeeWorklogs(UUID employeeId) throws EmployeeNotFoundException;

    List<Worklog> listEmployeeWorklogsByCreationDate(UUID employeeId, LocalDate creationDate, int page) throws EmployeeNotFoundException;

    List<Worklog> getOwnWorklogs(UUID id) throws EmployeeNotFoundException;

    List<Worklog> findSubordinateWorklogs(UUID loggedUser, UUID subordinateId) throws EmployeeNotFoundException, NotReachableNodeException;

    List<Worklog> listWorklogByAnyCriteria(Map<String, String[]> criteria);

    List<Worklog> listWorklogByAnyCriteria(UUID accessingEmployeeId, Map<String, String[]> criteria);

    List<Worklog> listWorklogByAllCriteria(Map<String, String[]> criteria);

    List<Worklog> listWorklogByAllCriteria(UUID accessingEmployeeId, Map<String, String[]> criteria);

    void deleteWorklogById(UUID id);

    List<WorklogType> listWorklogTypes();

    int findNumberOfWorklogs(UUID employeeId, LocalDate creationDate);

}
