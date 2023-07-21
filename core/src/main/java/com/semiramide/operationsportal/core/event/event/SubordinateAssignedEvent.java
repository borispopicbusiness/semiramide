package com.semiramide.operationsportal.core.event.event;

import com.semiramide.operationsportal.core.entity.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubordinateAssignedEvent {

  private Employee parent;
  private Employee child;
}
