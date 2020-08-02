package com.pth.iflow.profile.service.handler.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pth.iflow.common.enums.EModule;
import com.pth.iflow.common.enums.EUserDepartmentMemberType;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.ProfileResponse;
import com.pth.iflow.profile.model.User;
import com.pth.iflow.profile.model.UserAuthenticationRequest;
import com.pth.iflow.profile.model.UserDashboardMenu;
import com.pth.iflow.profile.model.UserDepartment;
import com.pth.iflow.profile.model.UserPasswordChangeRequest;
import com.pth.iflow.profile.service.access.IDepartmentAccessService;
import com.pth.iflow.profile.service.access.IUsersAccessService;
import com.pth.iflow.profile.service.handler.IAuthenticationService;
import com.pth.iflow.profile.service.handler.IUsersHandlerService;

@Service
public class UsersHandlerService implements IUsersHandlerService {

  private final IUsersAccessService usersService;
  private final IDepartmentAccessService departmentAccessService;
  private final IAuthenticationService authenticationService;

  public UsersHandlerService(@Autowired final IUsersAccessService usersService,
      @Autowired final IDepartmentAccessService departmentAccessService,
      @Autowired final IAuthenticationService authenticationService) {

    this.usersService = usersService;
    this.departmentAccessService = departmentAccessService;
    this.authenticationService = authenticationService;
  }

  @Override
  public User getUserByIdentity(final String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    return this.usersService.getUserByIdentity(identity);
  }

  @Override
  public List<User> getUserListByCompanyIdentity(final String companyId)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    return this.usersService.getUserListByCompanyIdentity(companyId);
  }

  @Override
  public ProfileResponse getUserProfileByIdentity(final String appIdentity, final String email)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    return this.usersService.getUserProfileByIdentity(appIdentity, email);
  }

  @Override
  public User saveUser(final User user) throws MalformedURLException, IFlowMessageConversionFailureException {

    this.verifayDepartmentManager(user);

    this.verifyDepartmentDeputy(user);

    return this.usersService.saveUser(user);
  }

  @Override
  public void deleteUser(final User user) throws MalformedURLException, IFlowMessageConversionFailureException {

    this.usersService.deleteUser(user);

  }

  @Override
  public void resetUserPassword(final UserPasswordChangeRequest userPasswordChangeRequest) {

    final UserAuthenticationRequest request = new UserAuthenticationRequest();
    request.setCompanyIdentity(userPasswordChangeRequest.getCompanyIdentity());
    request.setPassword(userPasswordChangeRequest.getPassword());
    request.setUserIdentity(userPasswordChangeRequest.getIdentity());

    this.authenticationService.setAuthentication(request);

  }

  @Override
  public void deleteUserAuthentication(final UserPasswordChangeRequest userPasswordChangeRequest) {

    final UserAuthenticationRequest request = new UserAuthenticationRequest();
    request.setCompanyIdentity(userPasswordChangeRequest.getCompanyIdentity());
    request.setPassword(userPasswordChangeRequest.getPassword());
    request.setUserIdentity(userPasswordChangeRequest.getIdentity());

    this.authenticationService.deleteAuthentication(request);

  }

  @Override
  public List<UserDashboardMenu> getUserDashboardMenuListByUserIdentity(final String appIdentity, final String userIdentity)
      throws MalformedURLException, IFlowMessageConversionFailureException {

    return this.usersService.getUserDashboardMenuListByUserIdentity(appIdentity, userIdentity);
  }

  @Override
  public List<UserDashboardMenu> saveUserDashboardMenuListByUserIdentity(final String appIdentity, final String userIdentity,
      final List<UserDashboardMenu> requestedModelList) throws MalformedURLException, IFlowMessageConversionFailureException {

    return this.usersService.saveUserDashboardMenuListByUserIdentity(appIdentity, userIdentity, requestedModelList);
  }

  private void verifyDepartmentDeputy(final User user) throws MalformedURLException, IFlowMessageConversionFailureException {

    final List<String> departmentIdentityWithDeputyMemberType = this.findDepartmentsWithMemberType(user, EUserDepartmentMemberType.DEPUTY);
    for (final String identity : departmentIdentityWithDeputyMemberType) {
      final User manager = this.departmentAccessService.getDepartmentDeputy(identity);
      if (manager != null && manager.hasNotSameIdentity(user.getIdentity())) {
        throw new ProfileCustomizedException("Deputy for department exists!", "", EModule.PROFILE.getModuleName(),
            EIFlowErrorType.PROFILE_DEPUTY_FOR_DEPARTMENT_EXISTS);
      }
    }
  }

  private void verifayDepartmentManager(final User user) throws MalformedURLException, IFlowMessageConversionFailureException {

    final List<
        String> departmentIdentityWithManagerMemberType = this.findDepartmentsWithMemberType(user, EUserDepartmentMemberType.MANAGER);
    for (final String identity : departmentIdentityWithManagerMemberType) {
      final User manager = this.departmentAccessService.getDepartmentManager(identity);
      if (manager != null && manager.hasNotSameIdentity(user.getIdentity())) {
        throw new ProfileCustomizedException("Manager for department exists!", "", EModule.PROFILE.getModuleName(),
            EIFlowErrorType.PROFILE_MANAGER_FOR_DEPARTMENT_EXISTS);
      }
    }
  }

  private List<String> findDepartmentsWithMemberType(final User user, final EUserDepartmentMemberType memberType) {

    final List<String> departmentIdentityWithManagerMemberType = new ArrayList<>();
    for (final UserDepartment userDepartment : user.getUserDepartments()) {
      if (userDepartment.getMemberType() == memberType) {
        departmentIdentityWithManagerMemberType.add(userDepartment.getDepartmentIdentity());
      }
    }
    return departmentIdentityWithManagerMemberType;
  }

}
