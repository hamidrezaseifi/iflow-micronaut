package com.pth.iflow.profile.service.access;

import java.net.MalformedURLException;
import java.util.List;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.ProfileResponse;
import com.pth.iflow.profile.model.User;
import com.pth.iflow.profile.model.UserDashboardMenu;

public interface IUsersAccessService {

  User getUserByIdentity(String identity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  List<User> getUserListByCompanyIdentity(String companyId)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  ProfileResponse getUserProfileByEmail(String appIdentity, String email)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  ProfileResponse getUserProfileByIdentity(String appIdentity, String userIdentity)
      throws ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException;

  User saveUser(User user) throws MalformedURLException, IFlowMessageConversionFailureException;

  void deleteUser(User user) throws MalformedURLException, IFlowMessageConversionFailureException;

  List<UserDashboardMenu> getUserDashboardMenuListByUserIdentity(String appIdentity, String userIdentity)
      throws MalformedURLException, IFlowMessageConversionFailureException;

  List<UserDashboardMenu> saveUserDashboardMenuListByUserIdentity(String appIdentity, String userIdentity,
      List<UserDashboardMenu> requestedModelList)
      throws MalformedURLException, IFlowMessageConversionFailureException;

}
