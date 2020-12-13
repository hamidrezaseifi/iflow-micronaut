package com.pth.gui.controllers;

import com.pth.common.edo.enums.EModule;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.gui.controllers.helper.AuthenticatedController;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.helpers.SessionDataHelper;
import com.pth.gui.models.Department;
import com.pth.gui.models.User;
import com.pth.gui.models.UserDashboardMenu;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.services.IDepartmentHandler;
import com.pth.gui.services.IUserHandler;
import com.pth.gui.services.IWorkflowMessageHandler;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import java.util.*;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/departments/data")
public class DepartmentController extends AuthenticatedController {

    private final IDepartmentHandler departmentHandler;

    public DepartmentController(IDepartmentHandler departmentHandler) {
        this.departmentHandler = departmentHandler;

    }

    @Get( "/list" )
    public HttpResponse<List<Department>> listDepartments(Session session)  {
        SessionData sessionData = getSessionData(session);

        List<Department> departmentList =
                this.departmentHandler.getCompanyDepartmentList(sessionData.getRefreshToken(), sessionData.getCompanyId());

        sessionData.getCompany().setDepartments(departmentList);
        //this.setSessionData(session, sessionData);

        return HttpResponse.ok(departmentList);
    }

    @Post( "/create" )
    public HttpResponse<Department> createDepartment(@Body final Department requestDepartment, Session session){
        SessionData sessionData = getSessionData(session);

        final Optional<Department> departmentOptional =
                this.departmentHandler.createDepartment(sessionData.getRefreshToken(),
                                                        sessionData.getCompanyId(),
                                                        requestDepartment);
        if(departmentOptional.isPresent()) {
            return HttpResponse.created(departmentOptional.get());
        }
        return HttpResponse.badRequest();
    }

    @Post( "/update" )
    public HttpResponse<Department> saveDepartment(@Body final Department requestUser, Session session){
        SessionData sessionData = getSessionData(session);

        final Optional<Department> departmentOptional =
                this.departmentHandler.updateDepartment(sessionData.getRefreshToken(),
                                                        sessionData.getCompanyId(),
                                                        requestUser);
        if(departmentOptional.isPresent()) {
            return HttpResponse.created(departmentOptional.get());
        }
        return HttpResponse.badRequest();
    }

    @Post( "/delete" )
    public HttpResponse<?> deleteDepartment(@Body final Department department, Session session){
        SessionData sessionData = getSessionData(session);

        this.departmentHandler.deleteDepartment(sessionData.getRefreshToken(), department);
        return HttpResponse.ok();
    }

}
