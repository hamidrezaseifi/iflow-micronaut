package com.pth.gui.controllers;

import com.pth.gui.helpers.SessionDataHelper;
import com.pth.gui.models.Company;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.services.ICompanyHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import javax.validation.Valid;
import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/company/data")
public class CompanyController {

    private final ICompanyHandler companyHandler;

    public CompanyController(ICompanyHandler companyHandler) {
        this.companyHandler = companyHandler;
    }

    @Get(value ="/info")
    public HttpResponse<Company> readCompanyInfo(Session session) {

        Optional<SessionData> sessionDataOptional = SessionDataHelper.getSessionData(session);
        if(sessionDataOptional.isPresent()){
            Optional<Company> companyOptional = this.companyHandler.read(
                    sessionDataOptional.get().getCompany().getCompany().getId(),
                    sessionDataOptional.get().getRefreshToken());
            if(companyOptional.isPresent()){
                return HttpResponse.ok(companyOptional.get());
            }

        }

        return HttpResponse.notFound();
    }

    @Post("/update")
    public HttpResponse<Company> saveCompany(@Body @Valid Company requestCompany, Session session) {

        Optional<SessionData> sessionDataOptional = SessionDataHelper.getSessionData(session);
        if(sessionDataOptional.isPresent()) {
            final Optional<Company> savedCompanyOptional = this.companyHandler.save(requestCompany,
                                                                  sessionDataOptional.get().getRefreshToken());
            if(savedCompanyOptional.isPresent()){
                return HttpResponse.created(savedCompanyOptional.get());
            }
        }
        return HttpResponse.notFound();
    }

}
