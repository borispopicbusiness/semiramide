package com.deavensoft.operationsportal.core.event.event;

import com.deavensoft.operationsportal.core.entity.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDeletedEvent {

  private Employee employee;
}
