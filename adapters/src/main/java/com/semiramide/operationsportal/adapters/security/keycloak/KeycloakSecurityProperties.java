package com.semiramide.operationsportal.adapters.security.keycloak;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Builder
@ConfigurationProperties(prefix = "op.security.provider.keycloak")
public class KeycloakSecurityProperties {

  private String realm;
  private String username;
  private String password;
  private String adminClientName;
  private String frontendClientName;
  private String grantType;
  private String getClientIdUrl;
  private String getAvailableRolesUrl;
  private String createPrincipalUrl;
  private String updatePrincipalUrl;
  private String deletePrincipalUrl;
  private String assignClientRoleUrl;
  private String authorizeUrl;
  private String defaultAdminUsername;
  private String defaultAdminPassword;
  private String getPrincipalCountUrl;
}
