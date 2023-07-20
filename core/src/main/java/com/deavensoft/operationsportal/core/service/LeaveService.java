package com.deavensoft.operationsportal.core.service;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.entity.Leave;
import com.deavensoft.operationsportal.core.entity.enums.LeaveStatus;
import com.deavensoft.operationsportal.core.entity.enums.LeaveType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaveService {

  List<Leave> findAllLeaves();

  Leave addLeave(Leave leave);

  Leave updateLeave(Leave leave);

  Optional<Leave> findLeaveById(UUID id);

  void deleteLeaveById(UUID id);

  void deleteAllLeaves();

  void deleteByEmployeeId(UUID employeeId);

  List<LeaveStatus> listLeaveStatus();

  List<LeaveType> listLeaveTypes();

  List<Leave> findLeaveByEmployeeId(UUID id);

  List<Leave> findAllSubordinatesLeaves(List<Employee> subordinates);

  boolean hasOverlaps(Leave leave);

  boolean isStartBeforeOrSameAsEnd(Leave leave);

  boolean isStartInThePast(Leave leave);
}
