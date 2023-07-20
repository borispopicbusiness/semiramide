package com.deavensoft.operationsportal.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.deavensoft.operationsportal.core.entity.Project;
import com.deavensoft.operationsportal.core.exception.NoProjectFoundException;
import com.deavensoft.operationsportal.core.repository.ProjectRepository;
import com.deavensoft.operationsportal.core.service.ProjectService;
import com.deavensoft.operationsportal.core.service.impl.ProjectServiceImpl;
import com.deavensoft.operationsportal.core.usecase.ProjectUseCase;
import com.deavensoft.operationsportal.core.usecase.impl.ProjectUseCaseImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FindProjectByNameTest {

  private ProjectUseCase projectUseCase;
  private ProjectRepository projectRepository;
  private ProjectService projectService;

  private String name;
  private Project project;

  @BeforeEach
  void setUp() {
    projectRepository = mock(ProjectRepository.class);
    projectService = ProjectServiceImpl.builder().projectRepository(projectRepository).build();
    projectUseCase = ProjectUseCaseImpl.builder().projectService(projectService).build();

    name = "project test";
    project =
        Project.builder()
            .id(UUID.randomUUID())
            .name("Project test")
            .description("this is description test.")
            .build();
  }

  @Test
  @DisplayName("Should find project by given name")
  void should_FindProjectByGivenName() throws NoProjectFoundException {
    when(projectRepository.findByName(name)).thenReturn(Optional.of(project));

    assertEquals(Optional.of(project), projectUseCase.findByName(name));
  }
}
