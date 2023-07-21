package com.semiramide.operationsportal.core.security;

import com.semiramide.operationsportal.core.entity.Employee;

public interface SecurityProvider {

  /**
   * Creates a principal on authorization server from the given employee.
   *
   * @param employee
   * @return Principal ID, as set on the authorization server
   */
  String createPrincipal(Employee employee);

  default void authorize() {}

  default void assignEmployeeRoles(String principalId, Role... roles) {}

  default void initialize() {}

  default void deletePrincipal(String principalId) {}

  default void updatePrincipal(Employee employee) {}

  default void changePassword(String principalId, String newPassword) {}
}
