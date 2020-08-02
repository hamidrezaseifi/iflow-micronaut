package com.pth.iflow.profile.controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.common.models.edo.AuthenticatedProfileRequestEdo;
import com.pth.iflow.common.models.edo.ProfileResponseEdo;
import com.pth.iflow.common.models.edo.TokenProfileRequestEdo;
import com.pth.iflow.common.models.edo.UserAuthenticationRequestEdo;
import com.pth.iflow.common.models.edo.UserAuthenticationResponseEdo;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.profile.exceptions.ProfileCustomizedException;
import com.pth.iflow.profile.model.ProfileResponse;
import com.pth.iflow.profile.model.UserAuthenticationRequest;
import com.pth.iflow.profile.model.UserAuthenticationSession;
import com.pth.iflow.profile.model.mapper.ProfileModelEdoMapper;
import com.pth.iflow.profile.service.handler.IAuthenticationService;
import com.pth.iflow.profile.service.handler.ITokenUserDataManager;

import javax.validation.Valid;

@Validated
@Controller(IflowRestPaths.ProfileModule.API001_PROFILE001_PROFILE)
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ProfileController {

  private final ITokenUserDataManager tokenUserDataManager;
  private final IAuthenticationService authenticationService;

  public ProfileController(final ITokenUserDataManager tokenUserDataManager,
      final IAuthenticationService authenticationService) {

    this.tokenUserDataManager = tokenUserDataManager;
    this.authenticationService = authenticationService;
  }

  @Produces(MediaType.APPLICATION_XML)
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post(value = "/authinfo")
  public HttpResponse<ProfileResponseEdo> readAuthenticatedInfo(@Body @Valid final AuthenticatedProfileRequestEdo requestEdo,
                                                                final Authentication authentication)
      throws ProfileCustomizedException, URISyntaxException, MalformedURLException, IFlowMessageConversionFailureException {

    final ProfileResponse profile = this.tokenUserDataManager
        .getProfileByTokenUserIdentity(requestEdo.getAppId(), requestEdo.getUserIdentity(),
            authentication);

    return HttpResponse.ok(ProfileModelEdoMapper.toEdo(profile));
  }

  @Produces(MediaType.APPLICATION_XML)
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post(value = "/tokeninfo")
  public HttpResponse<ProfileResponseEdo> readTokenInfo(@Body @Valid final TokenProfileRequestEdo requestEdo,
      final Authentication authentication)
      throws ProfileCustomizedException, URISyntaxException, MalformedURLException, IFlowMessageConversionFailureException {

    final ProfileResponse profile = this.tokenUserDataManager.getProfileByToken(requestEdo.getAppId(), authentication);

    return HttpResponse.ok(ProfileModelEdoMapper.toEdo(profile));
  }

  @Produces(MediaType.APPLICATION_XML)
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post(value = "/validate/token")
  public HttpResponse<UserAuthenticationResponseEdo> validateToken(final Authentication authentication)
      throws ProfileCustomizedException, URISyntaxException, MalformedURLException, IFlowMessageConversionFailureException {

    final UserAuthenticationSession userAuthenticationSession = this.tokenUserDataManager.validateToken(authentication);

    final UserAuthenticationResponseEdo edo = new UserAuthenticationResponseEdo();
    edo.setCompanyIdentity(userAuthenticationSession.getCompanyIdentity());
    edo.setCreated(userAuthenticationSession.getCreatedLong());
    edo.setLastAccess(userAuthenticationSession.getLastAccessLong());
    edo.setSessionid(userAuthenticationSession.getSessionid());
    edo.setToken(userAuthenticationSession.getToken());
    edo.setUserIdentity(userAuthenticationSession.getUserIdentity());

    return HttpResponse.ok(edo);
  }

  @Produces(MediaType.APPLICATION_XML)
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post(value = "/save/profile")
  public HttpResponse<UserAuthenticationRequestEdo> saveAuthentication(@Body @Valid final UserAuthenticationRequestEdo userEdo,
      final Authentication authentication)
      throws ProfileCustomizedException, URISyntaxException, MalformedURLException, IFlowMessageConversionFailureException {

    final UserAuthenticationRequest auth = this.authenticationService.setAuthentication(ProfileModelEdoMapper.fromEdo(userEdo));

    return HttpResponse.ok(ProfileModelEdoMapper.toEdo(auth));
  }

}
