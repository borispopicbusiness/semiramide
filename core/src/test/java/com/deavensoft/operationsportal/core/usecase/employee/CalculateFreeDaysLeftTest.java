package com.deavensoft.operationsportal.core.usecase.employee;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.event.AppEventPublisher;
import com.deavensoft.operationsportal.core.hierarchy.EmployeeHierarchyGraph;
import com.deavensoft.operationsportal.core.hierarchy.impl.GuavaMutableGraph;
import com.deavensoft.operationsportal.core.repository.EmployeeHierarchyRepository;
import com.deavensoft.operationsportal.core.repository.EmployeeRepository;
import com.deavensoft.operationsportal.core.service.EmployeeHierarchyService;
import com.deavensoft.operationsportal.core.service.EmployeeService;
import com.deavensoft.operationsportal.core.service.impl.EmployeeHierarchyServiceImpl;
import com.deavensoft.operationsportal.core.service.impl.EmployeeServiceImpl;
import com.deavensoft.operationsportal.core.usecase.EmployeeUseCase;
import com.deavensoft.operationsportal.core.usecase.impl.EmployeeUseCaseImpl;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateFreeDaysLeftTest {
  private EmployeeUseCase employeeUseCase;
  private EmployeeService employeeService;
  private EmployeeHierarchyService employeeHierarchyService;
  private EmployeeHierarchyGraph employeeHierarchyGraph;

  private AppEventPublisher eventPublisher;
  private EmployeeRepository employeeRepository;
  private EmployeeHierarchyRepository employeeHierarchyRepository;

  @BeforeEach
  void setup() {
    eventPublisher = mock(AppEventPublisher.class);
    employeeRepository = mock(EmployeeRepository.class);
    employeeHierarchyRepository = mock(EmployeeHierarchyRepository.class);

    employeeHierarchyGraph = new GuavaMutableGraph();

    employeeHierarchyService =
        EmployeeHierarchyServiceImpl.builder()
            .employeeHierarchyRepository(employeeHierarchyRepository)
            .employeeHierarchyGraph(employeeHierarchyGraph)
            .build();

    employeeService =
        EmployeeServiceImpl.builder()
            .employeeRepository(employeeRepository)
            .eventPublisher(eventPublisher)
            .employeeHierarchyGraph(employeeHierarchyGraph)
            .build();

    employeeUseCase =
        EmployeeUseCaseImpl.builder()
            .employeeService(employeeService)
            .employeeHierarchyService(employeeHierarchyService)
            .build();
  }

  @Test
  void should_Update_Free_Days_Left() {
    // given
    LocalDate fromDate = LocalDate.of(2023, 04, 15);
    LocalDate toDate = LocalDate.of(2023, 04, 25);
    Integer daysTaken = (int) ChronoUnit.DAYS.between(fromDate, toDate);
    UUID id = UUID.randomUUID();
    Employee employee = Employee.builder().id(id).freeDaysLeft(25).build();
    Employee updatedFreeDaysEmployee = Employee.builder().id(id).freeDaysLeft(15).build();
    when(employeeRepository.saveEmployee(employee)).thenReturn(updatedFreeDaysEmployee);
    employeeService.calculateFreeDaysLeft(daysTaken, employee);

    assertEquals((int) daysTaken, 10);
    verify(employeeRepository).saveEmployee(employee);
  }
}
