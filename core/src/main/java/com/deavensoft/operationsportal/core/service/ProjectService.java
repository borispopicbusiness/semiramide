package com.deavensoft.operationsportal.core.service;

import com.deavensoft.operationsportal.core.entity.Project;
import com.deavensoft.operationsportal.core.exception.NoProjectFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectService {

  List<Project> findAllProjects();

  Project saveProject(Project project);

  Optional<Project> findProjectById(UUID id);

  void deleteProjectById(UUID id) throws NoProjectFoundException;

  Project updateProject(Project project) throws NoProjectFoundException;

  Optional<Project> findByName(String name);

  List<Project> findAllProjectsByIds(List<UUID> projectIds);
}