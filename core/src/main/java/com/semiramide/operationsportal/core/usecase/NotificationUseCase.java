package com.semiramide.operationsportal.core.usecase;

import com.semiramide.operationsportal.core.entity.Employee;
import java.util.List;

public interface NotificationUseCase {

  void sendLeaveRequestMail(Employee employee, List<Employee> recipients);

  void sendLeaveDetailsUpdatedMail(Employee employee, List<Employee> recipients);

  void sendLeaveResponseMail(Employee superior, Employee recipient);
}
