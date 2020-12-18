package com.pth.gui.controllers.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.gui.controllers.helper.AuthenticatedController;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowSearchFilter;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.services.IWorkflowSearchHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;
import io.micronaut.validation.Validated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/workflow/data/general")
public class WorkflowSearchController extends AuthenticatedController {
  
  private final IWorkflowSearchHandler workflowSearchHandler;

  public WorkflowSearchController(IWorkflowSearchHandler workflowSearchHandler) {
    this.workflowSearchHandler = workflowSearchHandler;
  }

  @Post("/initsearch")
  public HttpResponse<Map<String, Object>> loadWorkflowListInitialData(Session session) {

    final Map<String, Object> map = new HashMap<>();
    SessionData sessionData = getSessionData(session);

    final Collection<WorkflowType> workflowTypeList = sessionData.getWorkflow().getWorkflowTypes();
    final WorkflowSearchFilter workflowSearchFilter = WorkflowSearchFilter.generateNew(workflowTypeList);
    EWorkflowStatus.values();

    map.put("workflowStatusList", EWorkflowStatus.values());
    map.put("searchFilter", workflowSearchFilter);

    return HttpResponse.ok(map);
  }

  @Post("/search")
  public HttpResponse<Map<String, Object>> searchWorkflow(@Body final WorkflowSearchFilter workflowSearchFilter, Session session) {

    SessionData sessionData = getSessionData(session);

    final List<Workflow> workflowList = this.workflowSearchHandler.searchWorkflow(workflowSearchFilter, sessionData);

    final Map<String, Object> mapped = new HashMap<>();
    mapped.put("res", "ok");
    mapped.put("list", workflowList);

    return HttpResponse.ok(mapped);
  }

}
