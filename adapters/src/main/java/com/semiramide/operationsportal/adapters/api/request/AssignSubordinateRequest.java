package com.semiramide.operationsportal.adapters.api.request;

import java.util.UUID;
import lombok.Data;

@Data
public class AssignSubordinateRequest {

  private UUID parentId;
  private UUID childId;
}
