package com.pth.iflow.profile.controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import com.pth.iflow.common.enums.EModule;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.common.models.edo.UserAuthenticationRequestEdo;
import com.pth.iflow.common.models.edo.UserAuthenticationResponseEdo;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.ProfileResponse;
import com.pth.iflow.profile.model.UserAuthenticationRequest;
import com.pth.iflow.profile.model.UserAuthenticationSession;
import com.pth.iflow.profile.model.mapper.ProfileModelEdoMapper;
import com.pth.iflow.profile.service.access.IUsersAccessService;
import com.pth.iflow.profile.service.handler.IAuthenticationService;
import com.pth.iflow.profile.service.handler.ISessionManager;

@Validated
@Controller(IflowRestPaths.ProfileModule.API001_PROFILE001_AUTHENTICATION)
@Secured(SecurityRule.IS_ANONYMOUS)
public class AuthenticationController {

  private final IAuthenticationService authService;
  private final ISessionManager sessionManager;
  private final IUsersAccessService usersService;

  public AuthenticationController(final IAuthenticationService authService, final ISessionManager sessionManager,
      final IUsersAccessService usersService) {

    this.authService = authService;
    this.sessionManager = sessionManager;
    this.usersService = usersService;
  }

  @Produces(MediaType.APPLICATION_XML)
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post(value = "/authenticate")
  public HttpResponse<UserAuthenticationResponseEdo> authenticate(@Body @Valid final UserAuthenticationRequestEdo userEdo)
      throws ProfileCustomizedException, URISyntaxException, MalformedURLException, IFlowMessageConversionFailureException {

    return HttpResponse.ok(this.authenticateUser(userEdo));
  }

  private UserAuthenticationResponseEdo authenticateUser(final UserAuthenticationRequestEdo userAuthenticationRequest)
      throws URISyntaxException, ProfileCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final UserAuthenticationRequest authUser = this.authService.authenticate(ProfileModelEdoMapper.fromEdo(userAuthenticationRequest));
    if (authUser == null) {
      throw new ProfileCustomizedException("Invalid Username or Password", "", EModule.PROFILE.getModuleName(),
          EIFlowErrorType.INVALID_USERNAMEPASSWORD);
    }

    UserAuthenticationSession session = this.sessionManager
        .findValidateByUserIdentity(authUser.getUserIdentity(),
            authUser.getCompanyIdentity(), true);
    if (session == null) {
      final ProfileResponse profile = this.usersService
          .getUserProfileByEmail(userAuthenticationRequest.getAppId(), authUser.getUserIdentity());
      if (profile.getCompanyProfile().getCompany().hasSameIdentity(authUser.getCompanyIdentity()) == false) {
        throw new ProfileCustomizedException("Invalid company-identity!", "", EModule.PROFILE.getModuleName(),
            EIFlowErrorType.COMPANY_NOTFOUND);
      }

      session = this.sessionManager
          .addSession(profile.getUser().getIdentity(), authUser.getCompanyIdentity(), profile.getUser().getRoles());
    }

    this.sessionManager.updateUser(session.getUserIdentity(), session.getSessionid());

    final UserAuthenticationResponseEdo authRespEdo = ProfileModelEdoMapper.toEdo(session);

    return authRespEdo;
  }
}
