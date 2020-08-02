package com.pth.iflow.common.rest;

import io.micronaut.http.uri.UriBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Map;


public class IflowRestPaths {

  public static class IflowUriBuilder {


    private final String template;
    private final UriBuilder builder;
    public IflowUriBuilder(final String template) {

      this.template = template;
      builder = UriBuilder.of(this.template);
    }

    public URI build(final Map<String, Object> map) {

      return this.builder.expand(map);
    }

  }

  public static class CoreModule {

    public static final int PORT = 1010;

    public static final String USER_SAVE = "/users/save";

    public static final String USER_DELETE = "/users/delete";

    public static final String USER_READ_BY_IDENTITY = "/users/readbyidentity/{identity}";
    public static final String USER_USERGROUPS_LIST_BY_IDENTITY = "/users/user/groups/{identity}";
    public static final String USER_DEPARTMENTS_LIST_BY_IDENTITY = "/users/user/departments/{identity}";
    public static final String USER_DEPUTIES_LIST_BY_IDENTITY = "/users/user/deputies/{identity}";
    public static final String USER_USER_LIST_BY_COMPANYIDENTITY = "/users/company/users/{companyidentity}";
    public static final String USER_USER_LIST_BY_DEPARTMENTIDENTITY = "/users/department/users/{identity}";
    public static final String USERPROFILE_READ_BY_EMAIL = "/users/readprofile/email/{appIdentity}/{email}";
    public static final String USERPROFILE_READ_BY_USERIDENTITY = "/users/readprofile/identity/{appIdentity}/{identity}";
    public static final String USERDASHBOARDMENU_READ_BY_USERIDENTITY = "/users/dashboardmenu/read/{appIdentity}/{userIdentity}";
    public static final String USERDASHBOARDMENU_SAVE_BY_USERIDENTITY = "/users/dashboardmenu/svae/{appIdentity}/{userIdentity}";

    public static final String COMPANY_READ_BY_IDENTITY = "/companies/readbyidentity/{companyidentity}";
    public static final String COMPANY_SAVE = "/companies/save";
    public static final String COMPANY_READ_WORKFLOWTYPE_ITEMS_OCR_SETTINGS_BY_IDENTITY = "/companies/readwtoctsettings/{companyidentity}";
    public static final String COMPANY_SAVE_WORKFLOWTYPE_ITEMS_OCR_SETTINGS = "/companies/savewtoctsettings";
    public static final String COMPANY_DELETE_WORKFLOWTYPE_ITEMS_OCR_SETTINGS = "/companies/deletewtoctsettings";

    public static final String DEPARTMENT_READ_BY_IDENTITY = "/department/readbyid/{identity}";
    public static final String DEPARTMENT_SAVE = "/department/save";
    public static final String DEPARTMENT_DELETE = "/department/delete";
    public static final String DEPARTMENT_READ_LIST = "/department/list";
    public static final String DEPARTMENT_READ_LIST_BY_COMPANYIDENTITY = "/department/company/list/{companyidentity}";
    public static final String DEPARTMENT_READ_ALLUSERLIST_BY_DEPARTMENTIDENTITY = "/department/alluser/list/{identity}";
    public static final String DEPARTMENT_GET_MANAGER = "/department/manager/read/{identity}";
    public static final String DEPARTMENT_GET_DEPUTY = "/department/deputy/read/{identity}";

    public static final String WORKFLOWTYPE_READ_BY_IDENTITY = "/workflowtype/readbyid/{identity}";
    public static final String WORKFLOWTYPE_READ_LIST = "/workflowtype/list";
    public static final String WORKFLOWTYPE_READ_LIST_BY_COMPANYIDENTITY = "/workflowtype/company/list/{companyidentity}";

    public static final String WORKFLOWTYPESTEP_READ_BY_IDENTITY = "/workflowtypestep/readbyid/{identity}";
    public static final String WORKFLOWTYPESTEP_READ_LIST_BY_WORKFLOWIDENTITY = "/workflowtypestep/workflowtype/list/{identity}";
    public static final String WORKFLOWTYPESTEP_READ_LIST = "/workflowtypestep/list";

    public static final String USERGROUP_READ_BY_IDENTITY = "/usergroup/readbyid/{identity}";
    public static final String USERGROUP_READ_LIST = "/usergroup/list";
    public static final String USERGROUP_READ_LIST_BY_COMPANYIDENTITY = "/usergroup/company/list/{companyidentity}";

    public static final String WORKFLOW_SEARCH = "/workflow/search";
    public static final String WORKFLOW_READ_BY_IDENTITY = "/workflow/read/{identity}";
    public static final String WORKFLOW_READLIST = "/workflow/readlist";

    public static final String INVOICEWORKFLOW_SAVE = "/invworkflow/save";
    public static final String INVOICEWORKFLOW_READ_BY_IDENTITY = "/invworkflow/readbyid/{identity}";
    public static final String INVOICEWORKFLOW_READ_LIST = "/invworkflow/list";
    public static final String INVOICEWORKFLOW_READ_LIST_BY_USERIDENTITY = "/invworkflow/user/list/{identity}/{status}";

    public static final String SINGLETASKWORKFLOW_SAVE = "/singletaskworkflow/save";
    public static final String SINGLETASKWORKFLOW_READ_BY_IDENTITY = "/singletaskworkflow/readbyid/{identity}";
    public static final String SINGLETASKWORKFLOW_READ_LIST = "/singletaskworkflow/list";
    public static final String SINGLETASKWORKFLOW_READ_LIST_BY_USERIDENTITY = "/singletaskworkflow/user/list/{identity}/{status}";

    public static final String TESTTHREETASKWORKFLOW_SAVE = "/threetaskworkflow/save";
    public static final String TESTTHREETASKWORKFLOW_READ_BY_IDENTITY = "/threetaskworkflow/readbyid/{identity}";
    public static final String TESTTHREETASKWORKFLOW_READ_LIST = "/threetaskworkflow/list";
    public static final String TESTTHREETASKWORKFLOW_READ_LIST_BY_USERIDENTITY = "/threetaskworkflow/user/list/{identity}/{status}";

    public static final String WORKFLOWMESSAGE_READ_BY_USERIDENTITY = "/workflowmessage/user/{identity}/{status}";
    public static final String WORKFLOWMESSAGE_READ_BY_WORKFLOWIDENTITY = "/workflowmessage/workflow/{workflowid}";
    public static final String WORKFLOWMESSAGE_SAVE = "/workflowmessage/save";
    public static final String WORKFLOWMESSAGE_CHANGE_WORKFLOWMESSAGE_STAUS_BY_WORKFLOWIDENTITY = "/workflowmessage/changestatus/{workflowidentity}/{stepidentity}/{useridentity}/{status}";

    /*public static URI READ_WORKFLOWMESSAGE_READ_BY_USER(final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWMESSAGE_READ_BY_USERIDENTITY);
      return builder.build(identity, status);
    }

    public static URI READ_WORKFLOWMESSAGE_READ_BY_WORKFLOW(final String workflowidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWMESSAGE_READ_BY_WORKFLOWIDENTITY);
      return builder.build(workflowidentity);
    }

    public static URI SAVE_WORKFLOWMESSAGE() {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWMESSAGE_SAVE);
      return builder.build();
    }

    public static URI CHANGE_WORKFLOWMESSAGE_WORKFLOWMESSAGE_STAUS(final String workflowidentity, final String stepidentity,
        final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWMESSAGE_CHANGE_WORKFLOWMESSAGE_STAUS_BY_WORKFLOWIDENTITY);
      return builder.build(workflowidentity, stepidentity, identity, status);
    }

    public static URI READ_USER_BY_IDENTITY(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI SAVE_USER_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_SAVE);
      return builder.build();
    }

    public static URI DELETE_USER_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_DELETE);
      return builder.build();
    }

    public static URI READ_USER_USER_LIST_BY_COMPANY(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_USER_LIST_BY_COMPANYIDENTITY);
      return builder.build(companyidentity);
    }

    public static URI READ_USERPROFILE_BY_EMAIL(final String appIdentity, final String email) {

      final IflowUriBuilder builder = new IflowUriBuilder(USERPROFILE_READ_BY_EMAIL);
      return builder.build(appIdentity, email);
    }

    public static URI READ_USERPROFILE_BY_IDENTITY(final String appIdentity, final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USERPROFILE_READ_BY_USERIDENTITY);
      return builder.build(appIdentity, identity);
    }

    public static URI READ_USERDASHBOARDMENU_BY_IDENTITY(final String appIdentity, final String userIdentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USERDASHBOARDMENU_READ_BY_USERIDENTITY);
      return builder.build(appIdentity, userIdentity);
    }

    public static URI SAVE_USERDASHBOARDMENU_BY_IDENTITY(final String appIdentity, final String userIdentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USERDASHBOARDMENU_SAVE_BY_USERIDENTITY);
      return builder.build(appIdentity, userIdentity);
    }

    public static URI READ_USERGROUP_BY_ID(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USERGROUP_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_USERGROUP_LIST_BY_COMPANY(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USERGROUP_READ_LIST_BY_COMPANYIDENTITY);
      return builder.build(companyidentity);
    }

    public static URI READ_COMPANY_BY_ID(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_READ_BY_IDENTITY);
      return builder.build(companyidentity);
    }

    public static URI SAVE_COMPANY() {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_SAVE);
      return builder.build();
    }

    public static URI READ_COMPANY_WORKFLOWTYPE_ITEMS_OCR_SETTINGS_BY_IDENTITY(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_READ_WORKFLOWTYPE_ITEMS_OCR_SETTINGS_BY_IDENTITY);
      return builder.build(companyidentity);
    }

    public static URI SAVE_COMPANY_WORKFLOWTYPE_ITEMS_OCR_SETTINGS() {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_SAVE_WORKFLOWTYPE_ITEMS_OCR_SETTINGS);
      return builder.build();
    }

    public static URI DELETE_COMPANY_WORKFLOWTYPE_ITEMS_OCR_SETTINGS() {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_DELETE_WORKFLOWTYPE_ITEMS_OCR_SETTINGS);
      return builder.build();
    }

    public static URI READ_DEPARTMENT_BY_ID(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI SAVE_DEPARTMENT_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_SAVE);
      return builder.build();
    }

    public static URI DELETE_DEPARTMENT_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_DELETE);
      return builder.build();
    }

    public static URI READ_DEPARTMENT_LIST_BY_COMPANY(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_READ_LIST_BY_COMPANYIDENTITY);
      return builder.build(companyidentity);
    }

    public static URI READ_DEPARTMENT_ALLUSERLIST_BY_DEPARTMENT(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_READ_ALLUSERLIST_BY_DEPARTMENTIDENTITY);
      return builder.build(identity);
    }

    public static URI GET_DEPARTMENT_MANAGER_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_GET_MANAGER);
      return builder.build(identity);
    }

    public static URI GET_DEPARTMENT_DEPUTY_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_GET_DEPUTY);
      return builder.build(identity);
    }

    public static URI READ_WORKFLOWTYPESTEP_BY_IDENTITY(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPESTEP_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_WORKFLOWTYPESTEP_LIST_BY_WORKFLOW(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPESTEP_READ_LIST_BY_WORKFLOWIDENTITY);
      return builder.build(identity);
    }

    public static URI READ_WORKFLOWTYPESTEP_LIST() {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPESTEP_READ_LIST);
      return builder.build();
    }

    public static URI READ_WORKFLOWTYPE_BY_IDENTITY(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPE_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_WORKFLOWTYPE_LIST_BY_COMPANY(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPE_READ_LIST_BY_COMPANYIDENTITY);
      return builder.build(companyidentity);
    }

    public static URI READ_WORKFLOWTYPE_LIST() {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPE_READ_LIST);
      return builder.build();
    }

    public static URI SEARCH_WORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOW_SEARCH);
      return builder.build();
    }

    public static URI READ_WORKFLOWLIST() {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOW_READLIST);
      return builder.build();
    }

    public static URI READ_WORKFLOW_BY_IDENTITY(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOW_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_INVOICEWORKFLOW_BY_IDENTITY(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI SAVE_INVOICEWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_SAVE);
      return builder.build();
    }

    public static URI READ_INVOICEWORKFLOWLIST_BY_IDENTITYLIST() {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_READ_LIST);
      return builder.build();
    }

    public static URI READ_INVOICEWORKFLOW_LIST_BY_USERIDENTITY(final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_READ_LIST_BY_USERIDENTITY);
      return builder.build(identity, status);
    }

    public static URI READ_SINGLETASKWORKFLOW_BY_IDENTITY(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI SAVE_SINGLETASKWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_SAVE);
      return builder.build();
    }

    public static URI READ_SINGLETASKWORKFLOWLIST_BY_IDENTITYLIST() {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_READ_LIST);
      return builder.build();
    }

    public static URI READ_SINGLETASKWORKFLOW_LIST_BY_USERIDENTITY(final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_READ_LIST_BY_USERIDENTITY);
      return builder.build(identity, status);
    }

    public static URI READ_TESTTHREETASKWORKFLOW_BY_IDENTITY(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI SAVE_TESTTHREETASKWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_SAVE);
      return builder.build();
    }

    public static URI READ_TESTTHREETASKWORKFLOWLIST_BY_IDENTITYLIST() {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_READ_LIST);
      return builder.build();
    }

    public static URI READ_TESTTHREETASKWORKFLOW_LIST_BY_USERIDENTITY(final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_READ_LIST_BY_USERIDENTITY);
      return builder.build(identity, status);
    }*/
  }

  public static class WorkflowModule {

    public static final int PORT = 1030;

    public static final String WORKFLOWTYPE_READ_BY_IDENTITY = "/workflowtype/readbyid/{identity}";
    public static final String WORKFLOWTYPE_READ_LIST = "/workflowtype/list";
    public static final String WORKFLOWTYPE_READ_LIST_BY_COMPANYIDENTITY = "/workflowtype/company/list/{identity}";

    public static final String WORKFLOWTYPESTEP_READ_BY_IDENTITY = "/workflowtypestep/readbyid/{identity}";
    public static final String WORKFLOWTYPESTEP_READ_LIST_BY_WORKFLOWIDENTITY = "/workflowtypestep/workflowtype/list/{identity}";
    public static final String WORKFLOWTYPESTEP_READ_LIST = "/workflowtypestep/list";

    public static final String WORKFLOWMESSAGE_READ_BY_USERIDENTITY = "/workflowmessage/user/{identity}/{status}";
    public static final String WORKFLOWMESSAGE_READ_BY_WORKFLOWIDENTITY = "/workflowmessage/workflow/{identity}";

    public static final String INVOICEWORKFLOW_CREATE = "/workflow/invoice/create";
    public static final String INVOICEWORKFLOW_SAVE = "/workflow/invoice/save";
    public static final String INVOICEWORKFLOW_READ_BY_IDENTITY = "/workflow/invoice/readbyid/{identity}";
    public static final String INVOICEWORKFLOW_READ_LIST = "/workflow/invoice/list";
    public static final String INVOICEWORKFLOW_READ_LIST_BY_USERIDENTITY = "/workflow/invoice/user/list/{identity}/{status}";
    public static final String INVOICEWORKFLOW_VALIDATE = "/workflow/invoice/validate";

    public static final String SINGLETASKWORKFLOW_CREATE = "/workflow/singletask/create";
    public static final String SINGLETASKWORKFLOW_SAVE = "/workflow/singletask/save";
    public static final String SINGLETASKWORKFLOW_READ_BY_IDENTITY = "/workflow/singletask/readbyid/{identity}";
    public static final String SINGLETASKWORKFLOW_READ_LIST = "/workflow/singletask/list";
    public static final String SINGLETASKWORKFLOW_READ_LIST_BY_TYPEIDENTITY = "/workflow/singletask/type/list/{identity}";
    public static final String SINGLETASKWORKFLOW_READ_LIST_BY_USERIDENTITY = "/workflow/singletask/user/list/{identity}/{status}";
    public static final String SINGLETASKWORKFLOW_VALIDATE = "/workflow/singletask/validate";

    public static final String TESTTHREETASKWORKFLOW_CREATE = "/workflow/testthreetask/create";
    public static final String TESTTHREETASKWORKFLOW_SAVE = "/workflow/testthreetask/save";
    public static final String TESTTHREETASKWORKFLOW_READ_BY_IDENTITY = "/workflow/testthreetask/readbyid/{identity}";
    public static final String TESTTHREETASKWORKFLOW_READ_LIST = "/workflow/testthreetask/list";
    public static final String TESTTHREETASKWORKFLOW_READ_LIST_BY_TYPEIDENTITY = "/workflow/testthreetask/type/list/{identity}";
    public static final String TESTTHREETASKWORKFLOW_READ_LIST_BY_USERIDENTITY = "/workflow/testthreetask/user/list/{identity}/{status}";
    public static final String TESTTHREETASKWORKFLOW_VALIDATE = "/workflow/testthreetask/validate";

    public static final String WORKFLOW_SEARCH = "/workflow/search";
    public static final String WORKFLOW_READLISTBY_IDENTITYLIST = "/workflow/readlist";
    public static final String WORKFLOW_READ_BY_IDENTITY = "/workflow/readbyid/{identity}";

    /*public static URI WORKFLOWTYPE_BY_ID_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPE_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_WORKFLOWTYPELIST_BY_COMPANYID_URIBUILDER(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPE_READ_LIST_BY_COMPANYIDENTITY);
      return builder.build(companyidentity);
    }

    public static URI READ_WORKFLOWTYPESTEP_BY_ID_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPESTEP_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_WORKFLOWTYPESTELIST_BY_WORKFLOWID_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWTYPESTEP_READ_LIST_BY_WORKFLOWIDENTITY);
      return builder.build(identity);
    }

    public static URI READ_WORKFLOWMESSAGE_READ_BY_USER(final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWMESSAGE_READ_BY_USERIDENTITY);
      return builder.build(identity, status);
    }

    public static URI READ_WORKFLOWMESSAGE_READ_BY_WORKFLOW(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOWMESSAGE_READ_BY_WORKFLOWIDENTITY);
      return builder.build(identity);
    }

    public static URI READ_INVOICEWORKFLOW_BY_IDENTITY_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_INVOICEWORKFLOWLIST_BY_IDENTITYLIST_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_READ_LIST);
      return builder.build();
    }

    public static URI READ_INVOICEWORKFLOWLIST_BY_USERID_URIBUILDER(final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_READ_LIST_BY_USERIDENTITY);
      return builder.build(identity, status);
    }

    public static URI CREATE_INVOICEWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_CREATE);
      return builder.build();
    }

    public static URI SAVE_INVOICEWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_SAVE);
      return builder.build();
    }

    public static URI VALIDATE_INVOICEWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(INVOICEWORKFLOW_VALIDATE);
      return builder.build();
    }

    public static URI READ_SINGLETASKWORKFLOW_BY_IDENTITY_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_SINGLETASKWORKFLOWLIST_BY_USERID_URIBUILDER(final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_READ_LIST_BY_USERIDENTITY);
      return builder.build(identity, status);
    }

    public static URI READ_SINGLETASKWORKFLOWLIST_BY_IDENTITYLIST_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_READ_LIST);
      return builder.build();
    }

    public static URI CREATE_SINGLETASKWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_CREATE);
      return builder.build();
    }

    public static URI SAVE_SINGLETASKWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_SAVE);
      return builder.build();
    }

    public static URI VALIDATE_SINGLETASKWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(SINGLETASKWORKFLOW_VALIDATE);
      return builder.build();
    }

    public static URI READ_TESTTHREETASKWORKFLOW_BY_IDENTITY_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_TESTTHREETASKWORKFLOWLIST_BY_IDENTITYLIST_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_READ_LIST);
      return builder.build();
    }

    public static URI READ_TESTTHREETASKWORKFLOWLIST_BY_USERID_URIBUILDER(final String identity, final int status) {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_READ_LIST_BY_USERIDENTITY);
      return builder.build(identity, status);
    }

    public static URI CREATE_TESTTHREETASKWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_CREATE);
      return builder.build();
    }

    public static URI SAVE_TESTTHREETASKWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_SAVE);
      return builder.build();
    }

    public static URI VALIDATE_TESTTHREETASKWORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(TESTTHREETASKWORKFLOW_VALIDATE);
      return builder.build();
    }

    public static URI SEARCH_WORKFLOW() {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOW_SEARCH);
      return builder.build();
    }

    public static URI READ_WORKFLOWLIST_BY_IDENTITYLIST() {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOW_READLISTBY_IDENTITYLIST);
      return builder.build();
    }

    public static URI READ_WORKFLOW_BY_IDENTITY_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(WORKFLOW_READ_BY_IDENTITY);
      return builder.build(identity);
    }*/

  }

  public static class ProfileModule {

    public static final int PORT = 1020;

    public static final String API001_PROFILE001 = "/profile001";
    public static final String API001_PROFILE001_AUTHENTICATION = "/profile001/authenticate";
    public static final String API001_PROFILE001_PROFILE = "/profile001/profile";

    //public static final String AUTHENTICATION_AUTHENTICATE = "/auth/authenticate";
    //public static final String PROFILE_READ_AUTHENTOCATEDINFO = "/profile/read/authinfo";
    //public static final String PROFILE_READ_TOKENINFO = "/profile/read/tokeninfo";
    //public static final String PROFILE_VALIDATE_TOKEN = "/profile/validate/token";
    //public static final String PROFILE_SAVE_AUTHENTOCATION = "/profile/save";

    public static final String COMPANY_READ_BY_IDENTITY = "/company/readbyid/{companyidentity}";
    public static final String COMPANY_READ_USER_LIST = "/company/read/user/{companyidentity}";
    public static final String COMPANY_READ_USERGROUP_LIST = "/company/read/usergroup/{companyidentity}";
    public static final String COMPANY_READ_DEPARTMENT_LIST = "/company/read/department/{companyidentity}";
    public static final String COMPANY_READ_PROFILE = "/company/read/profile/{appIdentity}/{companyidentity}";
    public static final String COMPANY_SAVE = "/company/save";
    public static final String COMPANY_READ_WORKFLOWTYPE_ITEMS_OCR_SETTINGS_BY_IDENTITY = "/company/readwtoctsettings/{companyidentity}";
    public static final String COMPANY_SAVE_WORKFLOWTYPE_ITEMS_OCR_SETTINGS = "/company/savewtoctsettings";
    public static final String COMPANY_DELETE_WORKFLOWTYPE_ITEMS_OCR_SETTINGS = "/company/deletewtoctsettings";

    public static final String DEPARTMENT_READ_BY_IDENTITY = "/department/readbyid/{identity}";
    public static final String DEPARTMENT_READ_ALLUSERS_LIST = "/department/read/allusers/{identity}";
    public static final String DEPARTMENT_SAVE = "/department/save";
    public static final String DEPARTMENT_DELETE = "/department/delete";

    public static final String USER_READ_BY_IDENTITY = "/users/read/identity/{identity}";
    public static final String USER_SAVE = "/users/save";
    public static final String USER_DELETE = "/users/delete";
    public static final String USER_RESETPASSWORD = "/users/resetpassword";
    public static final String USER_DELETE_AUTHENTICATION = "/users/delete/auth";
    public static final String USERDASHBOARDMENU_READ_BY_USERIDENTITY = "/users/dashboardmenu/read/{appIdentity}/{userIdentity}";
    public static final String USERDASHBOARDMENU_SAVE_BY_USERIDENTITY = "/users/dashboardmenu/save/{sppIdentity}/{userIdentity}";

    /*public static URI READ_COMPANY_BY_ID_URIBUILDER(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_READ_BY_IDENTITY);
      return builder.build(companyidentity);
    }

    public static URI SAVE_COMPANY() {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_SAVE);
      return builder.build();
    }

    public static URI READ_COMPANY_WORKFLOWTYPE_ITEMS_OCR_SETTINGS_BY_IDENTITY(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_READ_WORKFLOWTYPE_ITEMS_OCR_SETTINGS_BY_IDENTITY);
      return builder.build(companyidentity);
    }

    public static URI SAVE_COMPANY_WORKFLOWTYPE_ITEMS_OCR_SETTINGS() {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_SAVE_WORKFLOWTYPE_ITEMS_OCR_SETTINGS);
      return builder.build();
    }

    public static URI DELETE_COMPANY_WORKFLOWTYPE_ITEMS_OCR_SETTINGS() {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_DELETE_WORKFLOWTYPE_ITEMS_OCR_SETTINGS);
      return builder.build();
    }

    public static URI READ_USERLIST_BY_COMPANYID_URIBUILDER(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_READ_USER_LIST);
      return builder.build(companyidentity);
    }

    public static URI READ_USERGROUPLIST_BY_COMPANYID_URIBUILDER(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_READ_USERGROUP_LIST);
      return builder.build(companyidentity);
    }

    public static URI READ_DEPARTMENTLIST_BY_COMPANYID_URIBUILDER(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_READ_DEPARTMENT_LIST);
      return builder.build(companyidentity);
    }

    public static URI READ_PROFILE_BY_COMPANYID_URIBUILDER(final String appIdentity, final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(COMPANY_READ_PROFILE);
      return builder.build(appIdentity, companyidentity);
    }

    public static URI READ_DEPARTMENT_BY_ID_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI SAVE_DEPARTMENT_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_SAVE);
      return builder.build();
    }

    public static URI DELETE_DEPARTMENT_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_DELETE);
      return builder.build();
    }

    public static URI READ_ALLUSERS_BY_DEPARTMENTID_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(DEPARTMENT_READ_ALLUSERS_LIST);
      return builder.build(identity);
    }

    public static URI READ_PROFILE_TOKENINFO_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(PROFILE_READ_TOKENINFO);
      return builder.build();
    }

    public static URI VALIDATE_TOKENINFO_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(PROFILE_VALIDATE_TOKEN);
      return builder.build();
    }

    public static URI AUTHENTICATE_AUTHENTICATION_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(AUTHENTICATION_AUTHENTICATE);
      return builder.build();
    }

    public static URI READ_PROFILE_AUTHENTOCATEDINFO_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(PROFILE_READ_AUTHENTOCATEDINFO);
      return builder.build();
    }

    public static URI SAVE_USER_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_SAVE);
      return builder.build();
    }

    public static URI DELETE_USER_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_DELETE);
      return builder.build();
    }

    public static URI RESETPASSWORD_USER_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_RESETPASSWORD);
      return builder.build();
    }

    public static URI DELETE_USER_AUTHENTICATION_URIBUILDER() {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_DELETE_AUTHENTICATION);
      return builder.build();
    }

    public static URI READ_USER_BY_IDENTITY_URIBUILDER(final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USER_READ_BY_IDENTITY);
      return builder.build(identity);
    }

    public static URI READ_USERDASHBOARDMENU_BY_IDENTITY(final String appIdentity, final String userIdentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USERDASHBOARDMENU_READ_BY_USERIDENTITY);
      return builder.build(appIdentity, userIdentity);
    }

    public static URI SAVE_USERDASHBOARDMENU_BY_IDENTITY(final String appIdentity, final String userIdentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(USERDASHBOARDMENU_SAVE_BY_USERIDENTITY);
      return builder.build(appIdentity, userIdentity);
    }*/

  }

  public static class GuiModule {

    private GuiModule() {

    }

    public static final int PORT = 1200;

    public static final String INVOICEWORKFLOW_PAGE_BASE = "/workflow/invoice";
    public static final String SINGLETASKWORKFLOW_PAGE_BASE = "/workflow/singletask";
    public static final String TESTTHREETASKWORKFLOW_PAGE_BASE = "/workflow/testthreetask";

    public static final String INVOICEWORKFLOW_DATA_BASE = "/workflow/invoice/data";
    public static final String SINGLETASKWORKFLOW_DATA_BASE = "/workflow/singletask/data";
    public static final String TESTTHREETASKWORKFLOW_DATA_BASE = "/workflow/testthreetask/data";

    public static final String WORKFLOWGENERAL_DATA_BASE = "/workflow/general/data";
    public static final String WORKFLOWGENERAL_DATA_INITIALIZESEARCH = WORKFLOWGENERAL_DATA_BASE + "/initsearch";
    public static final String WORKFLOWGENERAL_DATA_SEARCH = WORKFLOWGENERAL_DATA_BASE + "/search";
    public static final String WORKFLOWGENERAL_DATA_ASSIGN = WORKFLOWGENERAL_DATA_BASE + "/assign/{workflowIdentity}";

    public static final String CACHDATA_READ_USER_WORKFLOWMESSAGELIST = "/cachdata/user/readworkflowmessagelist/{companyidentity}/{useridentity}";
    public static final String CACHDATA_ADD_USER_WORKFLOWMESSAGELIST = "/cachdata/user/addworkflowmessagelist/{companyidentity}/{useridentity}";
    public static final String CACHDATA_CAL_USER_DATARESET_BY_COMPANYIDENTITY = "/cachdata/user/datareset/{companyidentity}/{useridentity}";
    public static final String CACHDATA_CAL_USERLIST_DATARESET_BY_COMPANYIDENTITY = "/cachdata/userlist/datareset/{companyidentity}";
    public static final String CACHDATA_CAL_WORKFLOW_DATARESET_BY_WORKFLOWIDENTITY = "/cachdata/workflow/datareset/{companyidentity}/{identity}";

    /*public static URI READ_CACHDATA_USER_WORKFLOWMESSAGELIST(final String companyidentity, final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(CACHDATA_READ_USER_WORKFLOWMESSAGELIST);
      return builder.build(companyidentity, identity);
    }

    public static URI ADD_CACHDATA_USER_WORKFLOWMESSAGELIST(final String companyidentity, final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(CACHDATA_ADD_USER_WORKFLOWMESSAGELIST);
      return builder.build(companyidentity, identity);
    }

    public static URI CAL_CACHDATA_USER_DATARESET(final String companyidentity, final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(CACHDATA_CAL_USER_DATARESET_BY_COMPANYIDENTITY);
      return builder.build(companyidentity, identity);
    }

    public static URI CAL_CACHDATA_USERLIST_DATARESET(final String companyidentity) {

      final IflowUriBuilder builder = new IflowUriBuilder(CACHDATA_CAL_USERLIST_DATARESET_BY_COMPANYIDENTITY);
      return builder.build(companyidentity);
    }

    public static URI CAL_CACHDATA_WORKFLOW_DATARESET(final String companyidentity, final String identity) {

      final IflowUriBuilder builder = new IflowUriBuilder(CACHDATA_CAL_WORKFLOW_DATARESET_BY_WORKFLOWIDENTITY);
      return builder.build(companyidentity, identity);
    }*/

  }
}
