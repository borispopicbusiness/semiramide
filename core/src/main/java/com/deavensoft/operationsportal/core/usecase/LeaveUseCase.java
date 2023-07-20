package com.deavensoft.operationsportal.core.usecase;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.entity.Leave;
import com.deavensoft.operationsportal.core.entity.enums.LeaveStatus;
import com.deavensoft.operationsportal.core.entity.enums.LeaveType;
import com.deavensoft.operationsportal.core.exception.EmployeeNotFoundException;
import com.deavensoft.operationsportal.core.exception.FreeDaysLeftException;
import java.util.List;
import java.util.UUID;

public interface LeaveUseCase {

  Leave requestLeave(Leave leave) throws EmployeeNotFoundException, FreeDaysLeftException;

  void approveLeave(Leave leave) throws EmployeeNotFoundException, FreeDaysLeftException;

  void denyLeave(Leave leave);

  List<Leave> findAll();

  void save(Leave leave);

  Leave update(Leave leave);

  void deleteById(UUID id);

  List<LeaveStatus> listLeaveStatus();

  List<LeaveType> listLeaveTypes();

  List<Leave> findLeavesOfSubordinates(UUID id);

  List<Leave> findByEmployeeId(UUID id);

  List<Leave> findAllSubordinatesLeaves(Employee employee);
}
