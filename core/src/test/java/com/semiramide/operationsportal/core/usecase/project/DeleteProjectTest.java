package com.semiramide.operationsportal.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.semiramide.operationsportal.core.entity.Project;
import com.semiramide.operationsportal.core.exception.NoProjectFoundException;
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

class DeleteProjectTest {
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
  @DisplayName("Should delete project with given id.")
  void should_DeleteProjectWithGivenId() throws NoProjectFoundException {

    when(projectRepository.findProjectById(project.getId())).thenReturn(Optional.of(project));

    projectUseCase.deleteProjectById(project.getId());

    verify(projectRepository).deleteProjectById(project.getId());
  }

  @Test
  @DisplayName("should throw exception when project, with given id, does not exist,")
  void should_ThrowException_When_ProjectDoesNotExist() throws NoProjectFoundException {

    doThrow(NoProjectFoundException.class)
        .when(projectRepository)
        .deleteProjectById(project.getId());

    assertThrows(
        NoProjectFoundException.class, () -> projectUseCase.deleteProjectById(project.getId()));

    verify(projectRepository, never()).deleteProjectById(project.getId());
  }
}
