package com.semiramide.operationsportal.adapters.api.mapper;

import com.semiramide.operationsportal.adapters.api.dto.LeaveDtoAPI;
import com.semiramide.operationsportal.core.entity.Leave;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveMapperAPI {

  LeaveMapperAPI INSTANCE = Mappers.getMapper(LeaveMapperAPI.class);

  Leave leaveDtoAPIToLeave(LeaveDtoAPI leaveDtoAPI);

  LeaveDtoAPI leaveToLeaveDtoAPI(Leave leave);

  List<LeaveDtoAPI> leaveListToLeaveDtoAPIList(List<Leave> leaveList);

  List<Leave> leaveDtoAPIListToLeaveList(List<LeaveDtoAPI> leaveDtoAPIList);
}
