package com.semiramide.operationsportal.adapters.persistence.repository.pagingAndSorting;

import com.semiramide.operationsportal.adapters.persistence.dto.WorklogDtoDB;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;

public interface PagingAndSortingRepository
    extends org.springframework.data.repository.PagingAndSortingRepository<WorklogDtoDB, UUID> {

  List<WorklogDtoDB> findByEmployeeIdAndCreationDate(UUID id, LocalDate localDate, PageRequest of);
}
