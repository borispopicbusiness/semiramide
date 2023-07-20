package com.deavensoft.operationsportal.core.usecase.leave;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.entity.Leave;
import com.deavensoft.operationsportal.core.event.AppEventPublisher;
import com.deavensoft.operationsportal.core.exception.EmployeeNotFoundException;
import com.deavensoft.operationsportal.core.exception.FreeDaysLeftException;
import com.deavensoft.operationsportal.core.hierarchy.EmployeeHierarchyGraph;
import com.deavensoft.operationsportal.core.hierarchy.impl.GuavaMutableGraph;
import com.deavensoft.operationsportal.core.repository.EmployeeHierarchyRepository;
import com.deavensoft.operationsportal.core.repository.EmployeeRepository;
import com.deavensoft.operationsportal.core.repository.LeaveRepository;
import com.deavensoft.operationsportal.core.service.EmployeeHierarchyService;
import com.deavensoft.operationsportal.core.service.EmployeeService;
import com.deavensoft.operationsportal.core.service.LeaveService;
import com.deavensoft.operationsportal.core.service.impl.EmployeeHierarchyServiceImpl;
import com.deavensoft.operationsportal.core.service.impl.EmployeeServiceImpl;
import com.deavensoft.operationsportal.core.service.impl.LeaveServiceImpl;
import com.deavensoft.operationsportal.core.usecase.LeaveUseCase;
import com.deavensoft.operationsportal.core.usecase.impl.LeaveUseCaseImpl;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApproveLeaveTest {
  private LeaveUseCase leaveUseCase;
  private LeaveRepository leaveRepository;
  private LeaveService leaveService;

  private EmployeeHierarchyService employeeHierarchyService;

  private EmployeeService employeeService;
  private EmployeeHierarchyGraph employeeHierarchyGraph;
  private AppEventPublisher eventPublisher;
  private EmployeeRepository employeeRepository;
  private EmployeeHierarchyRepository employeeHierarchyRepository;

  @BeforeEach
  void setup() {
    eventPublisher = mock(AppEventPublisher.class);
    employeeRepository = mock(EmployeeRepository.class);
    leaveRepository = mock(LeaveRepository.class);
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

    leaveService = LeaveServiceImpl.builder().leaveRepository(leaveRepository).build();

    leaveUseCase =
        LeaveUseCaseImpl.builder()
            .leaveService(leaveService)
            .employeeService(employeeService)
            .build();
  }

  @Test
  void should_Approve_LeaveRequest() throws EmployeeNotFoundException, FreeDaysLeftException {
    UUID id = UUID.randomUUID();
    Employee employee =
        Employee.builder().id(id).firstName("Pera").lastName("Peric").freeDaysLeft(20).build();
    Leave leave =
        Leave.builder()
            .startTime(LocalDate.of(2023, 04, 15))
            .endTime(LocalDate.of(2023, 04, 25))
            .employeeId(id)
            .build();
    when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.of(employee));
    leaveUseCase.approveLeave(leave);

    verify(leaveRepository).saveLeave(leave);
    verify(employeeRepository).findEmployeeById(id);
    verify(employeeRepository).saveEmployee(employee);
  }

  @Test
  void should_Return_FreeDaysLeftException() {
    UUID id = UUID.randomUUID();
    Employee employee =
        Employee.builder().id(id).firstName("Pera").lastName("Peric").freeDaysLeft(5).build();
    Leave leave =
        Leave.builder()
            .startTime(LocalDate.of(2023, 04, 15))
            .endTime(LocalDate.of(2023, 04, 25))
            .employeeId(id)
            .build();
    when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.of(employee));

    assertThrows(FreeDaysLeftException.class, () -> leaveUseCase.approveLeave(leave));
  }

  @Test
  void should_Return_EmployeeNotFoundException() {
    UUID id = UUID.randomUUID();
    Employee employee =
        Employee.builder().id(id).firstName("Pera").lastName("Peric").freeDaysLeft(5).build();
    Leave leave =
        Leave.builder()
            .startTime(LocalDate.of(2023, 04, 15))
            .endTime(LocalDate.of(2023, 04, 25))
            .employeeId(UUID.randomUUID())
            .build();
    when(employeeRepository.findEmployeeById(id)).thenReturn(Optional.of(employee));

    assertThrows(EmployeeNotFoundException.class, () -> leaveUseCase.approveLeave(leave));
  }
}
