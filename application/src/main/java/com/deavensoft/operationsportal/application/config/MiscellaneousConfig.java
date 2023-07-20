package com.deavensoft.operationsportal.application.config;

import com.deavensoft.operationsportal.adapters.event.AppEventListenerImpl;
import com.deavensoft.operationsportal.adapters.event.AppEventPublisherImpl;
import com.deavensoft.operationsportal.core.event.AppEventListener;
import com.deavensoft.operationsportal.core.event.AppEventPublisher;
import com.deavensoft.operationsportal.core.hierarchy.EmployeeHierarchyGraph;
import com.deavensoft.operationsportal.core.hierarchy.impl.GuavaMutableGraph;
import com.deavensoft.operationsportal.core.init.ApplicationInitializer;
import com.deavensoft.operationsportal.core.security.SecurityProvider;
import com.deavensoft.operationsportal.core.service.EmployeeHierarchyService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiscellaneousConfig {

  @Bean
  EmployeeHierarchyGraph employeeHierarchyGraph() {
    return new GuavaMutableGraph();
  }

  @Bean
  ApplicationInitializer initializingBean(
      EmployeeHierarchyService employeeHierarchyService, SecurityProvider securityProvider) {
    return ApplicationInitializer.builder()
        .employeeHierarchyService(employeeHierarchyService)
        .securityProvider(securityProvider)
        .build();
  }

  @Bean
  AppEventPublisher eventPublisher(ApplicationEventPublisher publisher) {
    return AppEventPublisherImpl.builder().publisher(publisher).build();
  }

  @Bean
  AppEventListener eventListener(EmployeeHierarchyService employeeHierarchyService) {
    return AppEventListenerImpl.builder()
        .employeeHierarchyService(employeeHierarchyService)
        .build();
  }
}
