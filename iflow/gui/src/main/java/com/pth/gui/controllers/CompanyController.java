package com.pth.gui.controllers;

import com.pth.gui.controllers.helper.AuthenticatedController;
import com.pth.gui.helpers.SessionDataHelper;
import com.pth.gui.models.Company;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.services.ICompanyHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import javax.validation.Valid;
import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/company/data")
public class CompanyController extends AuthenticatedController {

    private final ICompanyHandler companyHandler;

    public CompanyController(ICompanyHandler companyHandler) {
        this.companyHandler = companyHandler;
    }

    @Get(value ="/info")
    public HttpResponse<Company> readCompanyInfo(Session session) {

        SessionData sessionData = getSessionData(session);

        Optional<Company> companyOptional = this.companyHandler.read(
                sessionData.getCompany().getCompany().getId(),
                sessionData.getRefreshToken());
        if(companyOptional.isPresent()){
            return HttpResponse.ok(companyOptional.get());
        }

        return HttpResponse.notFound();
    }

    @Post("/update")
    public HttpResponse<Company> saveCompany(@Body @Valid Company requestCompany, Session session) {

        SessionData sessionData = getSessionData(session);

        final Optional<Company> savedCompanyOptional =
                this.companyHandler.save(requestCompany, sessionData.getRefreshToken());
        if(savedCompanyOptional.isPresent()){
            return HttpResponse.created(savedCompanyOptional.get());
        }

        return HttpResponse.notFound();
    }

}
