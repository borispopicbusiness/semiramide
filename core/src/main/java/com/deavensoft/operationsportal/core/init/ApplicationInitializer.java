package com.deavensoft.operationsportal.core.init;

import com.deavensoft.operationsportal.core.security.SecurityProvider;
import com.deavensoft.operationsportal.core.service.EmployeeHierarchyService;
import jakarta.annotation.PostConstruct;
import lombok.Builder;

@Builder
public class ApplicationInitializer {

  private EmployeeHierarchyService employeeHierarchyService;
  private SecurityProvider securityProvider;

  @PostConstruct
  public void initialize() {
    employeeHierarchyService.loadGraph();
    securityProvider.initialize();
  }
}
