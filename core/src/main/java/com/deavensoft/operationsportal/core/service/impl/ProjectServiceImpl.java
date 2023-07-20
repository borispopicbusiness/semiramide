package com.deavensoft.operationsportal.core.service.impl;

import com.deavensoft.operationsportal.core.entity.Project;
import com.deavensoft.operationsportal.core.exception.NoProjectFoundException;
import com.deavensoft.operationsportal.core.repository.ProjectRepository;
import com.deavensoft.operationsportal.core.service.ProjectService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;

  @Override
  public List<Project> findAllProjects() {
    return projectRepository.findAllProjects();
  }

  @Override
  public Project saveProject(Project project) {
    return projectRepository.saveProject(project);
  }

  @Override
  public Optional<Project> findProjectById(UUID id) {
    return projectRepository.findProjectById(id);
  }

  @Override
  public void deleteProjectById(UUID id) throws NoProjectFoundException {
    projectRepository.deleteProjectById(id);
  }

  @Override
  public Project updateProject(Project project) {
    return projectRepository.updateProject(project);
  }

  @Override
  public Optional<Project> findByName(String name) {
    return projectRepository.findByName(name);
  }

  @Override
  public List<Project> findAllProjectsByIds(List<UUID> projectIds) {
    return projectRepository.findAllProjectsByIds(projectIds);
  }
}
