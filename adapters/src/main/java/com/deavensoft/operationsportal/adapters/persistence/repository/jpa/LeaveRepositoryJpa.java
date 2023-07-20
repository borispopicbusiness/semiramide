package com.deavensoft.operationsportal.adapters.persistence.repository.jpa;

import com.deavensoft.operationsportal.adapters.persistence.dto.LeaveDtoDB;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepositoryJpa extends JpaRepository<LeaveDtoDB, UUID> {

  List<LeaveDtoDB> findByEmployeeId(UUID id);

  void deleteByEmployeeId(UUID employeeId);

  List<LeaveDtoDB> findByStartTimeBefore(LocalDate endTime);

  List<LeaveDtoDB> findByEndTimeAfter(LocalDate startTime);
}
