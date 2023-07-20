package com.deavensoft.operationsportal.adapters.persistence.mapper;

import com.deavensoft.operationsportal.adapters.persistence.dto.EmployeeHierarchyEntryDtoDB;
import com.deavensoft.operationsportal.core.entity.EmployeeHierarchyEntry;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeHierarchyEntryMapperDB {

  EmployeeHierarchyEntryMapperDB INSTANCE = Mappers.getMapper(EmployeeHierarchyEntryMapperDB.class);

  EmployeeHierarchyEntry employeeHierarchyEntryDtoDBToEmployeeHierarchyEntry(
      EmployeeHierarchyEntryDtoDB employeeHierarchyEntryDtoDB);

  EmployeeHierarchyEntryDtoDB employeeHierarchyEntryToEmployeeHierarchyEntryDtoDB(
      EmployeeHierarchyEntry employeeHierarchyEntry);

  List<EmployeeHierarchyEntry> employeeHierarchyEntryDtoDBListToEmployeeHierarchyEntryList(
      List<EmployeeHierarchyEntryDtoDB> employeeHierarchyEntryDtoDB);

  List<EmployeeHierarchyEntryDtoDB> employeeHierarchyEntryListToEmployeeHierarchyEntryDtoDBList(
      List<EmployeeHierarchyEntry> employeeHierarchyEntry);
}
