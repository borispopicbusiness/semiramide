package com.semiramide.operationsportal.core.init;

import com.semiramide.operationsportal.core.security.SecurityProvider;
import com.semiramide.operationsportal.core.service.EmployeeHierarchyService;
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
