package com.deavensoft.operationsportal.adapters.persistence.repository.jpa;

import com.deavensoft.operationsportal.adapters.persistence.dto.ProjectDtoDB;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepositoryJpa extends JpaRepository<ProjectDtoDB, UUID> {

  Optional<ProjectDtoDB> findByName(String name);

  List<ProjectDtoDB> findAllByIdIn(List<UUID> projectIds);
}
