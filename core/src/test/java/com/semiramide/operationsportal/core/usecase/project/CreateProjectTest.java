package com.semiramide.operationsportal.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.semiramide.operationsportal.core.entity.Project;
import com.semiramide.operationsportal.core.exception.NoProjectFoundException;
import com.semiramide.operationsportal.core.exception.ProjectNameAlreadyTakenException;
import com.semiramide.operationsportal.core.repository.ProjectRepository;
import com.semiramide.operationsportal.core.service.ProjectService;
import com.semiramide.operationsportal.core.service.impl.ProjectServiceImpl;
import com.semiramide.operationsportal.core.usecase.ProjectUseCase;
import com.semiramide.operationsportal.core.usecase.impl.ProjectUseCaseImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateProjectTest {

  private ProjectUseCase projectUseCase;
  private ProjectRepository projectRepository;
  private ProjectService projectService;

  private Project project;

  @BeforeEach
  void setUp() {
    projectRepository = mock(ProjectRepository.class);
    projectService = ProjectServiceImpl.builder().projectRepository(projectRepository).build();
    projectUseCase = ProjectUseCaseImpl.builder().projectService(projectService).build();
    project =
        Project.builder()
            .id(UUID.randomUUID())
            .name("Project test")
            .description("this is description test.")
            .build();
  }

  @Test
  @DisplayName("Should create project when data is valid.")
  void should_CreateProject_When_DataIsValid()
      throws ProjectNameAlreadyTakenException, NoProjectFoundException {

    when(projectRepository.findByName(project.getName())).thenReturn(Optional.empty());

    projectUseCase.createProject(project);

    verify(projectRepository).saveProject(project);
  }

  @Test
  @DisplayName("should throw exception when project with the same name already exist in database")
  void should_ThrowException_When_NameIsTaken() {

    when(projectRepository.findByName(project.getName())).thenReturn(Optional.ofNullable(project));

    assertThrows(
        ProjectNameAlreadyTakenException.class, () -> projectUseCase.createProject(project));

    verify(projectRepository, never()).saveProject(project);
  }
}
