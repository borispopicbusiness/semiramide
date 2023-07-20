package com.deavensoft.operationsportal.adapters.api.mapper;

import com.deavensoft.operationsportal.adapters.api.dto.ProjectEmployeeDtoAPI;
import com.deavensoft.operationsportal.core.entity.ProjectEmployee;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectEmployeeMapperAPI {
  ProjectEmployeeMapperAPI INSTANCE = Mappers.getMapper(ProjectEmployeeMapperAPI.class);

  List<ProjectEmployeeDtoAPI> projectEmployeeListToProjectEmployeeDtoAPIList(
      List<ProjectEmployee> projectEmployeeList);

  ProjectEmployeeDtoAPI projectEmployeeToProjectEmployeeDtoAPI(ProjectEmployee projectEmployee);

  List<ProjectEmployee> projectEmployeeDtoAPIListToProjectEmployeeList(
      List<ProjectEmployeeDtoAPI> projectEmployeeDtoAPIList);

  ProjectEmployee projectEmployeeDtoAPIToProjectEmployee(
      ProjectEmployeeDtoAPI projectEmployeeDtoAPI);
}
