package com.pth.iflow.profile.controller;

import com.pth.iflow.common.models.edo.UserAuthenticationResponseEdo;
import com.pth.iflow.common.models.edo.UserPasswordGenerationResponseEdo;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.profile.credentials.IPasswordHashGenerator;
import com.pth.iflow.profile.model.UserAuthenticationRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import javax.validation.Valid;

@Validated
@Controller(IflowRestPaths.ProfileModule.API001_PROFILE001)
@Secured(SecurityRule.IS_ANONYMOUS)
public class MainController {

  private final IPasswordHashGenerator passwordHashGenerator;


  public MainController(IPasswordHashGenerator passwordHashGenerator){
    this.passwordHashGenerator = passwordHashGenerator;
  }

  @Produces(MediaType.APPLICATION_XML)
  @Secured(SecurityRule.IS_ANONYMOUS)
  @Post(value = "/createpassword")
  public HttpResponse<UserPasswordGenerationResponseEdo> createPassword(@Body @Valid UserAuthenticationRequest request) {

    String salt = passwordHashGenerator.produceSalt();

    UserPasswordGenerationResponseEdo response = new UserPasswordGenerationResponseEdo();
    response.setCompanyIdentity(request.getCompanyIdentity());
    response.setUserIdentity(request.getUserIdentity());
    response.setPasswordHash(passwordHashGenerator.produceHash(request.getPassword(), salt));
    response.setPasswordSalt(salt);


    return HttpResponse.ok();
  }
  
}
