package com.deavensoft.operationsportal.core.repository;

import com.deavensoft.operationsportal.core.entity.Employee;
import com.deavensoft.operationsportal.core.entity.Leave;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaveRepository {

  List<Leave> findAllLeaves();

  Leave saveLeave(Leave leave);

  Optional<Leave> findLeaveById(UUID id);

  void deleteLeave(Leave leave);

  void deleteAllLeaves();

  void deleteByEmployeeId(UUID employeeId);

  List<Leave> findByEmployeeId(UUID id);

  List<Leave> findAllSubordinatesLeaves(List<Employee> subordinates);

  List<Leave> findByStartTimeBefore(LocalDate endTime);

  List<Leave> findByEndTimeAfter(LocalDate startTime);
}
