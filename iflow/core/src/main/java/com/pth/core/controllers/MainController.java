package com.pth.core.controllers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pth.iflow.common.enums.EIdentity;
import com.pth.iflow.common.enums.EWorkflowMessageStatus;
import com.pth.iflow.common.enums.EWorkflowMessageType;
import com.pth.iflow.common.enums.EWorkflowType;
import com.pth.iflow.common.models.edo.WorkflowActionEdo;
import com.pth.iflow.common.models.edo.WorkflowFileEdo;
import com.pth.iflow.common.models.edo.WorkflowFileVersionEdo;
import com.pth.iflow.common.models.edo.WorkflowMessageEdo;
import com.pth.iflow.common.models.edo.WorkflowSearchFilterEdo;
import com.pth.iflow.common.models.edo.workflow.WorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.iflow.core.helper.BuildInfoProperties;

@RestController(value = "/")
public class MainController {

  @Autowired
  BuildInfoProperties buildInfoProperties;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/about", produces = MediaType.APPLICATION_XML_VALUE)
  public BuildInfoProperties about() {
    return buildInfoProperties;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/test", produces = MediaType.APPLICATION_XML_VALUE)
  public SingleTaskWorkflowEdo test() {
    final SingleTaskWorkflowEdo edo = new SingleTaskWorkflowEdo();

    final WorkflowEdo workflow = createWorkflow();

    edo.setWorkflow(workflow);
    return edo;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/testmsg", produces = MediaType.APPLICATION_XML_VALUE)
  public WorkflowMessageEdo testSHowMessage() {
    final WorkflowMessageEdo edo = new WorkflowMessageEdo();

    edo.setCreatedAt(LocalDateTime.now());
    edo.setCreatedByIdentity("admin@iflow.de");
    edo.setExpireDays(15);
    edo.setMessage("message");
    edo.setMessageType(EWorkflowMessageType.OFFERING_WORKFLOW.getValue());
    edo.setStatus(EWorkflowMessageStatus.OFFERING.getValue());
    edo.setStepIdentity("invocetasktypestep1");
    edo.setUserIdentity("user@iflow.de");
    edo.setVersion(1);
    edo.setWorkflowIdentity("w1575062673592-557642");

    return edo;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/testsearch", produces = MediaType.APPLICATION_XML_VALUE)
  public WorkflowSearchFilterEdo testSHowSearch() {
    final WorkflowSearchFilterEdo edo = new WorkflowSearchFilterEdo();

    final Set<String> set = new HashSet<>();
    set.add("admin@iflow.de");
    edo.setAssignedUserIdentitySet(set);

    return edo;
  }

  private WorkflowEdo createWorkflow() {
    final WorkflowEdo workflow = new WorkflowEdo();
    workflow.setComments("comments");
    workflow.setControllerIdentity("admin@iflow.de");
    workflow.setCreatedByIdentity("admin@iflow.de");
    workflow.setCurrentStepIdentity("singletasktypestep");
    workflow.setStatus(1);
    workflow.setVersion(1);
    workflow.setWorkflowTypeIdentity(EWorkflowType.SINGLE_TASK_WORKFLOW_TYPE.getIdentity());
    workflow.setIdentity(EIdentity.NOT_SET.getIdentity());

    final WorkflowFileVersionEdo fver = new WorkflowFileVersionEdo();
    fver.setComments("comments");
    fver.setCreatedByIdentity("admin@iflow.de");
    fver.setFilePath("filePath");
    fver.setFileVersion(1);
    fver.setStatus(1);

    final WorkflowFileEdo f = new WorkflowFileEdo();
    f.setActiveFilePath("filePath");
    f.setActiveFileVersion(1);
    f.setComments("comments");
    f.setCreatedByIdentity("admin@iflow.de");
    f.setExtention("extention");
    f.setStatus(1);
    f.setTitle("title");
    f.getFileVersions().add(fver);

    workflow.getFiles().add(f);

    final WorkflowActionEdo ac = new WorkflowActionEdo();
    ac.setAssignToIdentity("");
    ac.setComments("comments");
    ac.setCurrentStepIdentity("singletasktypestep");
    ac.setStatus(1);

    workflow.getActions().add(ac);

    return workflow;
  }
}
