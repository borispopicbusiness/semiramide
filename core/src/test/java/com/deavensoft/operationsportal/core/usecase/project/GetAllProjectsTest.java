package com.deavensoft.operationsportal.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.deavensoft.operationsportal.core.entity.Project;
import com.deavensoft.operationsportal.core.repository.ProjectRepository;
import com.deavensoft.operationsportal.core.service.ProjectService;
import com.deavensoft.operationsportal.core.service.impl.ProjectServiceImpl;
import com.deavensoft.operationsportal.core.usecase.ProjectUseCase;
import com.deavensoft.operationsportal.core.usecase.impl.ProjectUseCaseImpl;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GetAllProjectsTest {
  private ProjectUseCase projectUseCase;
  private ProjectRepository projectRepository;
  private ProjectService projectService;

  private List<Project> projects;

  @BeforeEach
  void setUp() {
    projectRepository = mock(ProjectRepository.class);
    projectService = ProjectServiceImpl.builder().projectRepository(projectRepository).build();
    projectUseCase = ProjectUseCaseImpl.builder().projectService(projectService).build();

    projects =
        List.of(
            new Project(UUID.randomUUID(), "project1", "this is project 1"),
            new Project(UUID.randomUUID(), "project2", "this is project 2"),
            new Project(UUID.randomUUID(), "project3", "this is project 3"));
  }

  @Test
  @DisplayName("Should return all projects.")
  void should_ReturnAllProjects() {

    when(projectRepository.findAllProjects()).thenReturn(projects);

    assertEquals(projects, projectUseCase.getAllProjects());
  }
}
