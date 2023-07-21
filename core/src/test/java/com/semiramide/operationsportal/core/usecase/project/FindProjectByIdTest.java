package com.semiramide.operationsportal.core.usecase.project;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.semiramide.operationsportal.core.entity.Project;
import com.semiramide.operationsportal.core.exception.NoProjectFoundException;
import com.semiramide.operationsportal.core.repository.ProjectRepository;
import com.semiramide.operationsportal.core.service.ProjectService;
import com.semiramide.operationsportal.core.service.impl.ProjectServiceImpl;
import com.semiramide.operationsportal.core.usecase.ProjectUseCase;
import com.semiramide.operationsportal.core.usecase.impl.ProjectUseCaseImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FindProjectByIdTest {

  private ProjectUseCase projectUseCase;
  private ProjectRepository projectRepository;
  private ProjectService projectService;

  private UUID id;
  private Project project;

  @BeforeEach
  void setUp() {
    projectRepository = mock(ProjectRepository.class);
    projectService = ProjectServiceImpl.builder().projectRepository(projectRepository).build();
    projectUseCase = ProjectUseCaseImpl.builder().projectService(projectService).build();

    id = UUID.randomUUID();
    project =
        Project.builder()
            .id(id)
            .name("Project test")
            .description("this is description test.")
            .build();
  }

  @Test
  @DisplayName("Should find project by given id.")
  void should_FindProjectWithGivenId() throws NoProjectFoundException {

    when(projectRepository.findProjectById(id)).thenReturn(Optional.of(project));

    Assertions.assertEquals(project, projectUseCase.findProjectById(id).get());
  }
}
