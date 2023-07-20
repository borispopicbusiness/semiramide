package com.deavensoft.operationsportal.adapters.api.controller;

import com.deavensoft.operationsportal.core.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
@RequiredArgsConstructor
public class TestController {

  private final NotificationService notificationService;

  @GetMapping("/admin/test")
  public String bla() {
    return "bla";
  }

  @GetMapping("/send-mail")
  public void sendMail(@RequestParam("to") String to) {
    notificationService.sendEmail(
        "deavensoft.op@guidle.com", new String[] {to}, "Test email", "Test body", false);
  }
}
