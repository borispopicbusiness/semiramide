package com.semiramide.operationsportal.core.event.event;

import com.semiramide.operationsportal.core.entity.EmployeeHierarchyEntry;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubordinateUnassignedEvent {

  private EmployeeHierarchyEntry entry;
}
