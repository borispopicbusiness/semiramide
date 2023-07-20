package com.deavensoft.operationsportal.application.config;

import com.deavensoft.operationsportal.adapters.security.keycloak.KeycloakJwtRolesConverter;
import com.deavensoft.operationsportal.adapters.security.keycloak.KeycloakSecurityProperties;
import com.deavensoft.operationsportal.adapters.security.keycloak.KeycloakSecurityProvider;
import com.deavensoft.operationsportal.core.security.SecurityProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http, KeycloakJwtRolesConverter jwtRolesConverter) throws Exception {
    http.authorizeHttpRequests(
        auth ->
            auth.requestMatchers(
                    HttpMethod.POST,
                    "/employee",
                    "/project",
                    "/assign-subordinate",
                    "/project-employees")
                .access(
                    AuthorizationManagers.allOf(
                        AuthorityAuthorizationManager.hasAuthority("SCOPE_all"),
                        AuthorityAuthorizationManager.hasAnyRole("ROLE_ADMIN")))
                .requestMatchers(
                    HttpMethod.DELETE, "/employee/**", "/project/**", "/project-employees/**")
                .access(
                    AuthorizationManagers.allOf(
                        AuthorityAuthorizationManager.hasAuthority("SCOPE_all"),
                        AuthorityAuthorizationManager.hasAnyRole("ROLE_ADMIN")))
                .requestMatchers(HttpMethod.PUT, "/employee/me/**")
                .access(
                    AuthorizationManagers.allOf(
                        AuthorityAuthorizationManager.hasAuthority("SCOPE_all"),
                        AuthorityAuthorizationManager.hasAnyRole("ROLE_EMPLOYEE", "ROLE_ADMIN")))
                .requestMatchers(HttpMethod.PUT, "/project/**", "/employee/**")
                .access(
                    AuthorizationManagers.allOf(
                        AuthorityAuthorizationManager.hasAuthority("SCOPE_all"),
                        AuthorityAuthorizationManager.hasAnyRole("ROLE_ADMIN")))
                .requestMatchers("/admin/**")
                .access(
                    AuthorizationManagers.allOf(
                        AuthorityAuthorizationManager.hasAuthority("SCOPE_all"),
                        AuthorityAuthorizationManager.hasAnyRole("ROLE_ADMIN")))
                .requestMatchers("/test/**")
                .permitAll()
                .requestMatchers("/**")
                .access(
                    AuthorizationManagers.allOf(
                        AuthorityAuthorizationManager.hasAnyAuthority("SCOPE_all"),
                        AuthorityAuthorizationManager.hasAnyRole("ROLE_EMPLOYEE", "ROLE_ADMIN"))));
    http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtRolesConverter);
    return http.build();
  }

  @Bean
  SecurityProvider securityProvider(KeycloakSecurityProperties props) {
    return KeycloakSecurityProvider.builder().properties(props).build();
  }

  @Bean
  KeycloakJwtRolesConverter jwtRolesConverter() {
    return new KeycloakJwtRolesConverter();
  }
}
