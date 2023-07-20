package com.deavensoft.operationsportal.application.config;

import com.deavensoft.operationsportal.core.event.AppEventPublisher;
import com.deavensoft.operationsportal.core.hierarchy.EmployeeHierarchyGraph;
import com.deavensoft.operationsportal.core.repository.*;
import com.deavensoft.operationsportal.core.security.SecurityProvider;
import com.deavensoft.operationsportal.core.service.*;
import com.deavensoft.operationsportal.core.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class ServiceConfig {

  @Bean
  NotificationService notificationService(
      JavaMailSender javaMailSender, SpringTemplateEngine thymeleafTemplateEngine) {
    return NotificationServiceImpl.builder()
        .javaMailSender(javaMailSender)
        .thymeleafTemplateEngine(thymeleafTemplateEngine)
        .build();
  }

  @Bean
  EmployeeService employeeService(
      EmployeeRepository employeeRepository,
      EmployeeHierarchyGraph employeeHierarchyGraph,
      SecurityProvider securityProvider,
      AppEventPublisher appEventPublisher) {
    return EmployeeServiceImpl.builder()
        .employeeRepository(employeeRepository)
        .employeeHierarchyGraph(employeeHierarchyGraph)
        .securityProvider(securityProvider)
        .eventPublisher(appEventPublisher)
        .build();
  }

  @Bean
  LeaveService leaveService(LeaveRepository leaveRepository) {
    return LeaveServiceImpl.builder().leaveRepository(leaveRepository).build();
  }

  @Bean
  WorklogService worklogService(WorklogRepository worklogRepository) {
    return WorklogServiceImpl.builder().worklogRepository(worklogRepository).build();
  }

  @Bean
  EmployeeHierarchyService employeeHierarchyService(
      EmployeeService employeeService,
      EmployeeHierarchyRepository employeeHierarchyRepository,
      EmployeeHierarchyGraph employeeHierarchyGraph,
      AppEventPublisher appEventPublisher) {
    return EmployeeHierarchyServiceImpl.builder()
        .employeeHierarchyGraph(employeeHierarchyGraph)
        .employeeHierarchyRepository(employeeHierarchyRepository)
        .employeeService(employeeService)
        .eventPublisher(appEventPublisher)
        .build();
  }

  @Bean
  ProjectService projectService(ProjectRepository projectRepository) {
    return ProjectServiceImpl.builder().projectRepository(projectRepository).build();
  }

  @Bean
  ProjectEmployeeService projectEmployeeService(
      ProjectEmployeeRepository projectEmployeeRepository) {
    return ProjectEmployeeServiceImpl.builder()
        .projectEmployeeRepository(projectEmployeeRepository)
        .build();
  }
}
