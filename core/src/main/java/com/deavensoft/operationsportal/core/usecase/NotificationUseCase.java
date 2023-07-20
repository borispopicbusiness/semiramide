package com.deavensoft.operationsportal.core.usecase;

import com.deavensoft.operationsportal.core.entity.Employee;
import java.util.List;

public interface NotificationUseCase {

  void sendLeaveRequestMail(Employee employee, List<Employee> recipients);

  void sendLeaveDetailsUpdatedMail(Employee employee, List<Employee> recipients);

  void sendLeaveResponseMail(Employee superior, Employee recipient);
}
