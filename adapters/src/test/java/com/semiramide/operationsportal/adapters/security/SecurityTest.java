package com.semiramide.operationsportal.adapters.security;

import com.semiramide.operationsportal.adapters.security.keycloak.KeycloakSecurityProperties;
import com.semiramide.operationsportal.adapters.security.keycloak.KeycloakSecurityProvider;
import com.semiramide.operationsportal.core.entity.Employee;
import com.semiramide.operationsportal.core.security.SecurityProvider;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class SecurityTest {

  private KeycloakSecurityProperties props;

  @BeforeEach
  void setup() {
    props =
        KeycloakSecurityProperties.builder()
            .realm("Operations_portal_realm")
            .username("kc_admin")
            .password("kc_password")
            .adminClientName("admin-cli")
            .grantType("password")
            .authorizeUrl("http://localhost:8080/auth/realms/master/protocol/openid-connect/token")
            .createPrincipalUrl(
                "http://localhost:8080/auth/admin/realms/Operations_portal_realm/users")
            .getPrincipalCountUrl(
                "http://localhost:8080/auth/admin/realms/operations-portal-realm/users/count")
            .build();
  }

  @Test
  @Disabled
  void testAuthorizeRequest() {
    SecurityProvider sp = KeycloakSecurityProvider.builder().properties(props).build();
    sp.authorize();
    copyToClipboard(((KeycloakSecurityProvider) sp).getAuthToken());
  }

  @Test
  @Disabled
  void testObtainClientId() {}

  @Test
  @Disabled
  void testAuthorizeAndCreatePrincipal() {
    SecurityProvider sp = KeycloakSecurityProvider.builder().properties(props).build();
    sp.authorize();

    sp.createPrincipal(
        Employee.builder()
            .firstName("Mika")
            .lastName("Mikic")
            .email("mm@gmail.com")
            .password("verystrongpassword")
            .build());
  }

  @Test
  @Disabled
  void testGetPrincipalCount() {
    SecurityProvider sp = KeycloakSecurityProvider.builder().properties(props).build();
    sp.authorize();
    int count = ((KeycloakSecurityProvider) sp).getPrincipalCount();
    System.out.println(count);
  }

  private void copyToClipboard(String text) {
    StringSelection stringSelectionObj = new StringSelection(text);
    Clipboard clipboardObj = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboardObj.setContents(stringSelectionObj, null);
  }
}
