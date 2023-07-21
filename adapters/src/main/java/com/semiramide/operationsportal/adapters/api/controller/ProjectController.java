package com.semiramide.operationsportal.adapters.api.controller;

import com.semiramide.operationsportal.adapters.api.dto.ProjectDtoAPI;
import com.semiramide.operationsportal.adapters.api.mapper.ProjectMapperAPI;
import com.semiramide.operationsportal.core.entity.Project;
import com.semiramide.operationsportal.core.exception.NoProjectFoundException;
import com.semiramide.operationsportal.core.exception.ProjectNameAlreadyTakenException;
import com.semiramide.operationsportal.core.usecase.EmployeeUseCase;
import com.semiramide.operationsportal.core.usecase.ProjectUseCase;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

  private final ProjectUseCase projectUseCase;
  private final EmployeeUseCase employeeUseCase;

  @GetMapping()
  public List<ProjectDtoAPI> findAll() {
    return ProjectMapperAPI.INSTANCE.projectListToProjectDtoAPIList(
        projectUseCase.getAllProjects());
  }

  @GetMapping("/{id}")
  public ProjectDtoAPI getById(@PathVariable(name = "id") UUID id) throws NoProjectFoundException {
    Optional<Project> project = projectUseCase.findProjectById(id);
    if (project.isPresent()) {
      return ProjectMapperAPI.INSTANCE.projectToProjectDtoAPI(project.get());
    }
    return null;
  }

  @PostMapping
  public ProjectDtoAPI createProject(@RequestBody ProjectDtoAPI project)
      throws ProjectNameAlreadyTakenException, NoProjectFoundException {
    Project savedProject =
        projectUseCase.createProject(ProjectMapperAPI.INSTANCE.projectDtoAPIToProject(project));
    return ProjectMapperAPI.INSTANCE.projectToProjectDtoAPI(savedProject);
  }

  @PutMapping
  public ProjectDtoAPI editProject(@RequestBody ProjectDtoAPI project)
      throws NoProjectFoundException {
    Project editedProject =
        projectUseCase.editProject(ProjectMapperAPI.INSTANCE.projectDtoAPIToProject(project));
    return ProjectMapperAPI.INSTANCE.projectToProjectDtoAPI(editedProject);
  }

  @DeleteMapping("/{id}")
  public void deleteProject(@PathVariable(name = "id") UUID id) throws NoProjectFoundException {
    projectUseCase.deleteProjectById(id);
  }
}
