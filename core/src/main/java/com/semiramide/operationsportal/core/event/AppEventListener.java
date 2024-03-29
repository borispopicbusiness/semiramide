package com.semiramide.operationsportal.core.event;

import com.semiramide.operationsportal.core.event.event.*;

public interface AppEventListener {

  void employeeCreatedEvent(EmployeeCreatedEvent event);

  void employeeUpdatedEvent(EmployeeUpdatedEvent event);

  void employeeDeletedEvent(EmployeeDeletedEvent event);

  void subordinateAssignedEvent(SubordinateAssignedEvent event);

  void subordinateUnassignedEvent(SubordinateUnassignedEvent event);

  void allEmployeeHierarchyEntriesDeletedForEmployeeEvent(
      AllEmployeeHierarchyEntriesDeletedForEmployeeEvent event);
}
