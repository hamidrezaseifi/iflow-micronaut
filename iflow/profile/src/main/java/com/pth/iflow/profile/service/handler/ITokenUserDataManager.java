package com.pth.iflow.profile.service.handler;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.Department;
import com.pth.iflow.profile.model.ProfileResponse;
import com.pth.iflow.profile.model.User;
import com.pth.iflow.profile.model.UserAuthenticationSession;
import com.pth.iflow.profile.model.UserGroup;
import io.micronaut.security.authentication.Authentication;

public interface ITokenUserDataManager {

  ProfileResponse getProfileByToken(String appIdentity, Authentication authentication)
      throws ProfileCustomizedException, MalformedURLException, URISyntaxException, IFlowMessageConversionFailureException;

  UserAuthenticationSession validateToken(Authentication authentication)
      throws ProfileCustomizedException, IFlowMessageConversionFailureException;

  void validateTokenAndCompany(Authentication authentication, String companyIdentity)
      throws ProfileCustomizedException, IFlowMessageConversionFailureException;

  ProfileResponse getProfileByTokenAndCheckCompany(String appIdentity, Authentication authentication, String companyIdentity)
      throws ProfileCustomizedException, MalformedURLException, URISyntaxException, IFlowMessageConversionFailureException;

  ProfileResponse getProfileByTokenUserIdentity(String appIdentity, String userIdentity, Authentication authentication)
      throws ProfileCustomizedException, MalformedURLException, URISyntaxException, IFlowMessageConversionFailureException;

  List<User> getUserListByToken(Authentication authentication, String companyIdentity)
      throws ProfileCustomizedException, MalformedURLException, URISyntaxException, IFlowMessageConversionFailureException;

  List<UserGroup> getUserGroupListByToken(Authentication authentication, String companyIdentity)
      throws ProfileCustomizedException, MalformedURLException, URISyntaxException, IFlowMessageConversionFailureException;

  List<Department> getDepartmentListByToken(Authentication authentication, String companyIdentity)
      throws ProfileCustomizedException, MalformedURLException, URISyntaxException, IFlowMessageConversionFailureException;

  List<User> getAllUserListByDepartmentId(Authentication authentication, String identity)
      throws ProfileCustomizedException, MalformedURLException, URISyntaxException, IFlowMessageConversionFailureException;

}
