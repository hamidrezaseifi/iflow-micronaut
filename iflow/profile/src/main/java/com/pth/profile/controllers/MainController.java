package com.pth.profile.controllers;

import com.pth.common.contants.TestDataConstants;
import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.common.edo.UserAuthenticationRequestEdo;
import com.pth.common.edo.UserPasswordGenerationResponseEdo;
import com.pth.common.enums.EUserRoles;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import io.micronaut.security.annotation.Secured;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Validated
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/")
public class MainController {

    private final IPasswordHashGenerator passwordHashGenerator;
    private final IUserRepository userRepository;


    public MainController(IPasswordHashGenerator passwordHashGenerator,
                          IUserRepository userRepository){
        this.passwordHashGenerator = passwordHashGenerator;
        this.userRepository = userRepository;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(value = "/test")
    public HttpResponse<UserEntity> test() {

        String salt = passwordHashGenerator.produceSalt();

        UserEntity response = new UserEntity();
        response.setCompanyId(TestDataConstants.TEST_COMPANY_ID);
        response.setIdentity("test-iflow-1");
        response.setUsername("test@iflow.de");
        response.setPasswordHash(passwordHashGenerator.produceHash("test", salt));
        response.setPasswordSalt(salt);
        Set<String> roles = new HashSet<>();
        roles.add(EUserRoles.ADMIN.getId());
        roles.add(EUserRoles.DATAENTRY.getId());
        roles.add(EUserRoles.VIEW.getId());
        response.setRoles(roles);
        response.setStatus(1);

        this.userRepository.save(response);

        return HttpResponse.ok(response);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(value = "/createpassword")
    public HttpResponse<UserPasswordGenerationResponseEdo> createPassword(@Body @Valid UserAuthenticationRequestEdo request) {

        String salt = passwordHashGenerator.produceSalt();

        UserPasswordGenerationResponseEdo response = new UserPasswordGenerationResponseEdo();
        response.setCompanyIdentity(request.getCompanyIdentity());
        response.setUserIdentity(request.getUserIdentity());
        response.setPasswordHash(passwordHashGenerator.produceHash(request.getPassword(), salt));
        response.setPasswordSalt(salt);


        return HttpResponse.ok(response);
    }

}
