package com.deavensoft.operationsportal.core.event.event;

import com.deavensoft.operationsportal.core.entity.EmployeeHierarchyEntry;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubordinateUnassignedEvent {

  private EmployeeHierarchyEntry entry;
}
