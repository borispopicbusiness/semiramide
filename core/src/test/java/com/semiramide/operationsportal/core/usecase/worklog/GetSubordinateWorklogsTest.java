package com.semiramide.operationsportal.core.usecase.worklog;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.semiramide.operationsportal.core.entity.Employee;
import com.semiramide.operationsportal.core.exception.EmployeeNotFoundException;
import com.semiramide.operationsportal.core.exception.NotReachableNodeException;
import com.semiramide.operationsportal.core.hierarchy.EmployeeHierarchyGraph;
import com.semiramide.operationsportal.core.hierarchy.impl.GuavaMutableGraph;
import com.semiramide.operationsportal.core.repository.EmployeeHierarchyRepository;
import com.semiramide.operationsportal.core.repository.EmployeeRepository;
import com.semiramide.operationsportal.core.repository.ProjectRepository;
import com.semiramide.operationsportal.core.repository.WorklogRepository;
import com.semiramide.operationsportal.core.service.EmployeeHierarchyService;
import com.semiramide.operationsportal.core.service.EmployeeService;
import com.semiramide.operationsportal.core.service.ProjectService;
import com.semiramide.operationsportal.core.service.WorklogService;
import com.semiramide.operationsportal.core.service.impl.EmployeeHierarchyServiceImpl;
import com.semiramide.operationsportal.core.service.impl.EmployeeServiceImpl;
import com.semiramide.operationsportal.core.service.impl.ProjectServiceImpl;
import com.semiramide.operationsportal.core.service.impl.WorklogServiceImpl;
import com.semiramide.operationsportal.core.usecase.WorklogUseCase;
import com.semiramide.operationsportal.core.usecase.impl.WorklogUseCaseImpl;
import com.semiramide.operationsportal.core.usecase.util.Helper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GetSubordinateWorklogsTest {

  private WorklogUseCase worklogUseCase;

  private WorklogService worklogService;

  private EmployeeHierarchyService employeeHierarchyService;

  private WorklogRepository worklogRepository;

  private EmployeeRepository employeeRepository;

  private ProjectRepository projectRepository;

  private EmployeeService employeeService;

  private ProjectService projectService;

  private EmployeeHierarchyGraph employeeHierarchyGraph;

  private EmployeeHierarchyRepository employeeHierarchyRepository;
  private Helper helper;

  @BeforeEach
  void setUp() {
    worklogRepository = mock(WorklogRepository.class);
    projectRepository = mock(ProjectRepository.class);
    employeeRepository = mock(EmployeeRepository.class);
    employeeHierarchyRepository = mock(EmployeeHierarchyRepository.class);
    employeeHierarchyGraph = new GuavaMutableGraph();

    helper = new Helper();

    employeeHierarchyService =
        EmployeeHierarchyServiceImpl.builder()
            .employeeHierarchyGraph(employeeHierarchyGraph)
            .employeeHierarchyRepository(employeeHierarchyRepository)
            .build();

    worklogService = WorklogServiceImpl.builder().worklogRepository(worklogRepository).build();

    projectService = ProjectServiceImpl.builder().projectRepository(projectRepository).build();

    employeeService = EmployeeServiceImpl.builder().employeeRepository(employeeRepository).build();
    worklogUseCase =
        WorklogUseCaseImpl.builder()
            .worklogService(worklogService)
            .projectService(projectService)
            .employeeService(employeeService)
            .employeeHierarchyService(employeeHierarchyService)
            .build();
  }

  @Test
  @DisplayName("Should return worklogs of subordinate.")
  void should_ReturnWorklogsOfSubordinate()
      throws NotReachableNodeException, EmployeeNotFoundException {

    List<Employee> employees = helper.generateEmployeesList();
    helper.initializeStartingGraph(employeeHierarchyGraph, employees);

    UUID loggedUser = employees.get(0).getId();
    UUID subordinateId = employees.get(1).getId();

    when(employeeRepository.findEmployeeById(subordinateId))
        .thenReturn(Optional.ofNullable(employees.get(1)));
    when(employeeRepository.findEmployeeById(loggedUser))
        .thenReturn(Optional.ofNullable(employees.get(0)));

    worklogUseCase.findSubordinateWorklogs(loggedUser, subordinateId);

    verify(worklogRepository).findWorklogsByEmployeeId(subordinateId);
  }

  @Test
  @DisplayName("Should throw exception when subordinate id is invalid")
  void should_ThrowException_When_SubordinateIdIsInvalid() {
    List<Employee> employees = helper.generateEmployeesList();
    helper.initializeStartingGraph(employeeHierarchyGraph, employees);

    UUID loggedUser = employees.get(0).getId();
    UUID subordinateId = employees.get(1).getId();

    when(employeeRepository.findEmployeeById(UUID.randomUUID()))
        .thenReturn(Optional.ofNullable(employees.get(1)));
    when(employeeRepository.findEmployeeById(loggedUser))
        .thenReturn(Optional.ofNullable(employees.get(0)));

    assertThrows(
        EmployeeNotFoundException.class,
        () -> worklogUseCase.findSubordinateWorklogs(loggedUser, subordinateId));

    verify(worklogRepository, never()).findWorklogsByEmployeeId(subordinateId);
  }

  @Test
  @DisplayName("Should throw exception when logged employee id is invalid")
  void should_ThrowException_When_ParentIdIsInvalid() {
    List<Employee> employees = helper.generateEmployeesList();
    helper.initializeStartingGraph(employeeHierarchyGraph, employees);

    UUID loggedEmployee = employees.get(0).getId();
    UUID subordinateId = employees.get(1).getId();

    when(employeeRepository.findEmployeeById(subordinateId))
        .thenReturn(Optional.ofNullable(employees.get(1)));
    when(employeeRepository.findEmployeeById(UUID.randomUUID()))
        .thenReturn(Optional.ofNullable(employees.get(0)));

    assertThrows(
        EmployeeNotFoundException.class,
        () -> worklogUseCase.findSubordinateWorklogs(loggedEmployee, subordinateId));

    verify(worklogRepository, never()).findWorklogsByEmployeeId(subordinateId);
  }

  @Test
  @DisplayName("Should throw exception when subordinate id is not child of logged employee")
  void should_ThrowException_When_SubordinateIsNotChild() {
    List<Employee> employees = helper.generateEmployeesList();
    helper.initializeStartingGraph(employeeHierarchyGraph, employees);

    UUID loggedEmployee = employees.get(3).getId();
    UUID subordinateId = employees.get(6).getId();

    when(employeeRepository.findEmployeeById(subordinateId))
        .thenReturn(Optional.ofNullable(employees.get(3)));
    when(employeeRepository.findEmployeeById(loggedEmployee))
        .thenReturn(Optional.ofNullable(employees.get(6)));

    assertThrows(
        NotReachableNodeException.class,
        () -> worklogUseCase.findSubordinateWorklogs(loggedEmployee, subordinateId));

    verify(worklogRepository, never()).findWorklogsByEmployeeId(subordinateId);
  }
}
