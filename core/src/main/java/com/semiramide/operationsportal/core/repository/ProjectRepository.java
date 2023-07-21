package com.semiramide.operationsportal.core.repository;

import com.semiramide.operationsportal.core.entity.Project;
import com.semiramide.operationsportal.core.exception.NoProjectFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {
  List<Project> findAllProjects();

  Project saveProject(Project project);

  Optional<Project> findProjectById(UUID id);

  void deleteProjectById(UUID id) throws NoProjectFoundException;

  Project updateProject(Project project);

  Optional<Project> findByName(String name);

  List<Project> findAllProjectsByIds(List<UUID> projectIds);
}
