package com.deavensoft.operationsportal.adapters.api.mapper;

import com.deavensoft.operationsportal.adapters.api.dto.ProjectDtoAPI;
import com.deavensoft.operationsportal.core.entity.Project;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapperAPI {

  ProjectMapperAPI INSTANCE = Mappers.getMapper(ProjectMapperAPI.class);

  List<ProjectDtoAPI> projectListToProjectDtoAPIList(List<Project> projectList);

  ProjectDtoAPI projectToProjectDtoAPI(Project project);

  List<Project> projectDtoAPIListToProjectList(List<ProjectDtoAPI> projectDtoAPIList);

  Project projectDtoAPIToProject(ProjectDtoAPI projectDtoAPI);
}
