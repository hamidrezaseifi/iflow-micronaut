package com.pth.workflow.services.bl.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.clients.profile.IProfile001Client;
import com.pth.common.edo.UserListEdo;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.mapper.IUserMapper;
import com.pth.workflow.models.User;
import com.pth.workflow.services.bl.IDepartmentDataService;
import io.micronaut.security.authentication.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class DepartmentDataService implements IDepartmentDataService {

  private static final Logger logger = LoggerFactory.getLogger(DepartmentDataService.class);

  private final IProfile001Client profileClient;
  private final IUserMapper userMapper;

  public DepartmentDataService(IProfile001Client profileClient,
                               IUserMapper userMapper) {

    this.profileClient = profileClient;
    this.userMapper = userMapper;
  }

  @Override
  public List<User> getUserListByDepartmentId(UUID departmentId, String authorization)
          throws WorkflowCustomizedException {

    logger.debug("Request department user list");

    Optional<UserListEdo> userListEdoOptional = profileClient.getAllDocumentMetaData(authorization, departmentId);
    if(userListEdoOptional.isPresent()){
      return userMapper.fromEdoList(userListEdoOptional.get().getUsers());
    }

    return new ArrayList<>();
  }

}
