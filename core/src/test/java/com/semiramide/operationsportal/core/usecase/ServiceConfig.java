package com.semiramide.operationsportal.core.usecase;

import com.semiramide.operationsportal.core.service.NotificationService;
import com.semiramide.operationsportal.core.service.impl.NotificationServiceImpl;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class ServiceConfig {

  @Bean
  JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("127.0.0.1");
    mailSender.setPort(3025);
    mailSender.setUsername("user123");
    mailSender.setPassword("password123");

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    return mailSender;
  }

  @Bean
  NotificationService emailService(JavaMailSender javaMailSender) {
    return NotificationServiceImpl.builder().javaMailSender(javaMailSender).build();
  }
}
