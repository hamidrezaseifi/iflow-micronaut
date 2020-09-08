package com.pth.workflow.services.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.clients.clients.profile.IUserClient;
import com.pth.common.edo.UserListEdo;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.mapper.IUserMapper;
import com.pth.workflow.models.User;
import com.pth.workflow.services.bl.IDepartmentDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class DepartmentDataService implements IDepartmentDataService {

  private static final Logger logger = LoggerFactory.getLogger(DepartmentDataService.class);

  private final IUserClient userClient;
  private final IUserMapper userMapper;

  public DepartmentDataService(IUserClient userClient,
                               IUserMapper userMapper) {

    this.userClient = userClient;
    this.userMapper = userMapper;
  }

  @Override
  public List<User> getUserListByDepartmentId(UUID departmentId, String authorization)
          throws WorkflowCustomizedException {

    logger.debug("Request department user list");

    Optional<UserListEdo> userListEdoOptional = userClient.readDepartmentUsers(authorization, departmentId);
    if(userListEdoOptional.isPresent()){
      return userMapper.fromEdoList(userListEdoOptional.get().getUsers());
    }

    return new ArrayList<>();
  }

}
