package com.semiramide.operationsportal.adapters.event;

import com.semiramide.operationsportal.core.event.AppEventPublisher;
import lombok.Builder;
import org.springframework.context.ApplicationEventPublisher;

@Builder
public class AppEventPublisherImpl implements AppEventPublisher {

  private final ApplicationEventPublisher publisher;

  @Override
  public void publishEvent(Object event) {
    publisher.publishEvent(event);
  }
}
