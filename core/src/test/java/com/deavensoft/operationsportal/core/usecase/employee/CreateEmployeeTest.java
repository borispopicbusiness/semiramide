package com.deavensoft.operationsportal.core.usecase.employee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.event.AppEventPublisher;
import com.deavensoft.operationsportal.core.event.event.EmployeeCreatedEvent;
import com.deavensoft.operationsportal.core.exception.EmailAlreadyExistsException;
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
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateEmployeeTest {

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
  @Disabled
  @DisplayName(value = "Create employee - happy path")
  void should_CreateEmployee_When_AllConditionsAreMet() throws EmailAlreadyExistsException {
    // given
    UUID id = UUID.randomUUID();
    String email = "email@example.com";
    String firstName = "firstName";
    String lastName = "lastName";
    String password = "password";

    Employee newEmployeeData =
        Employee.builder()
            .email(email)
            .firstName(firstName)
            .lastName(lastName)
            .password(password)
            .build();
    Employee expectedCreatedEmployee =
        Employee.builder()
            .id(id)
            .email(email)
            .firstName(firstName)
            .lastName(lastName)
            .password(password)
            .build();
    Optional<Employee> expectedCreatedEmployeeOptional = Optional.of(expectedCreatedEmployee);
    when(employeeRepository.findEmployeeByEmail(newEmployeeData.getEmail()))
        .thenReturn(Optional.empty());
    when(employeeRepository.saveEmployee(newEmployeeData)).thenReturn(expectedCreatedEmployee);

    // when
    Employee actualCreatedEmployeeOptional = employeeUseCase.createEmployee(newEmployeeData);

    // then
    verify(employeeRepository).findEmployeeByEmail(newEmployeeData.getEmail());
    verify(employeeRepository).saveEmployee(newEmployeeData);
    assertEquals(expectedCreatedEmployeeOptional, actualCreatedEmployeeOptional);
    verify(eventPublisher)
        .publishEvent(
            EmployeeCreatedEvent.builder().employee(actualCreatedEmployeeOptional).build());
  }

  @Test
  @DisplayName(value = "Email is taken")
  void should_ThrowException_When_EmailIsTaken() {
    // given
    String email = "email@example.com";
    String firstName = "firstName";
    String lastName = "lastName";
    String password = "password";

    Employee newEmployeeData =
        Employee.builder()
            .email(email)
            .firstName(firstName)
            .lastName(lastName)
            .password(password)
            .build();

    Employee existingEmployee =
        Employee.builder()
            .id(UUID.randomUUID())
            .email(email)
            .firstName("existing" + firstName)
            .lastName("existing" + lastName)
            .password("existing" + password)
            .build();
    Optional<Employee> existingEmployeeOptional = Optional.of(existingEmployee);
    when(employeeRepository.findEmployeeByEmail(newEmployeeData.getEmail()))
        .thenReturn(existingEmployeeOptional);

    // when

    // then
    assertAll(
        () ->
            assertThrows(
                EmailAlreadyExistsException.class,
                () -> employeeUseCase.createEmployee(newEmployeeData)));
  }
}
