package com.deavensoft.operationsportal.core.usecase.employee;

import static org.mockito.Mockito.*;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.event.AppEventPublisher;
import com.deavensoft.operationsportal.core.exception.EmailAlreadyExistsException;
import com.deavensoft.operationsportal.core.exception.EmployeeNotFoundException;
import com.deavensoft.operationsportal.core.repository.EmployeeRepository;
import com.deavensoft.operationsportal.core.security.SecurityProvider;
import com.deavensoft.operationsportal.core.service.EmployeeService;
import com.deavensoft.operationsportal.core.service.impl.EmployeeServiceImpl;
import com.deavensoft.operationsportal.core.usecase.EmployeeUseCase;
import com.deavensoft.operationsportal.core.usecase.impl.EmployeeUseCaseImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EditEmployeeTest {

  private EmployeeUseCase employeeUseCase;
  private EmployeeService employeeService;

  private EmployeeRepository employeeRepository;
  private SecurityProvider securityProvider;
  private AppEventPublisher eventPublisher;

  @BeforeEach
  void setup() {
    employeeRepository = mock(EmployeeRepository.class);
    securityProvider = mock(SecurityProvider.class);
    eventPublisher = mock(AppEventPublisher.class);

    employeeService =
        EmployeeServiceImpl.builder()
            .employeeRepository(employeeRepository)
            .securityProvider(securityProvider)
            .eventPublisher(eventPublisher)
            .build();
    employeeUseCase = EmployeeUseCaseImpl.builder().employeeService(employeeService).build();
  }

  @Test
  void should_EditEmployee() throws EmailAlreadyExistsException, EmployeeNotFoundException {
    // given
    UUID employeeId = UUID.randomUUID();

    Employee expectedEmployee =
        Employee.builder()
            .id(employeeId)
            .firstName("fName1")
            .lastName("lName1")
            .email("email1")
            .build();

    // when
    when(employeeRepository.findEmployeeById(employeeId)).thenReturn(Optional.of(expectedEmployee));
    employeeUseCase.updateEmployee(expectedEmployee);

    // then
    verify(employeeRepository).saveEmployee(expectedEmployee);
  }
}
