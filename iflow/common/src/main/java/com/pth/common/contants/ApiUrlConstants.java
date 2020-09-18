package com.pth.common.contants;

public class ApiUrlConstants {

    public static class CoreUrlConstants {
        public static final String API001_CORE001_ROOT = "/api001/core001";


        public static final String WORKFLOWTYPE_READ_BY_IDENTITY = "/workflowtype/readbyid/{identity}";
        public static final String WORKFLOWTYPE_READ_LIST = "/workflowtype/list";
        public static final String WORKFLOWTYPE_READ_LIST_BY_COMPANYIDENTITY =
                "/workflowtype/company/list/{companyidentity}";

        public static final String WORKFLOWTYPESTEP_READ_BY_IDENTITY = "/workflowtypestep/readbyid/{identity}";
        public static final String WORKFLOWTYPESTEP_READ_LIST_BY_WORKFLOWIDENTITY =
                "/workflowtypestep/workflowtype/list/{identity}";
        public static final String WORKFLOWTYPESTEP_READ_LIST = "/workflowtypestep/list";
        public static final String WORKFLOW_SEARCH = "/workflow/search";
        public static final String WORKFLOW_READ_BY_IDENTITY = "/workflow/read/{identity}";
        public static final String WORKFLOW_READLIST = "/workflow/readlist";

        public static final String INVOICEWORKFLOW_SAVE = "/invworkflow/save";
        public static final String INVOICEWORKFLOW_READ_BY_IDENTITY = "/invworkflow/readbyid/{identity}";
        public static final String INVOICEWORKFLOW_READ_LIST = "/invworkflow/list";
        public static final String INVOICEWORKFLOW_READ_LIST_BY_USERIDENTITY =
                "/invworkflow/user/list/{identity}/{status}";

        public static final String SINGLETASKWORKFLOW_SAVE = "/singletaskworkflow/save";
        public static final String SINGLETASKWORKFLOW_READ_BY_IDENTITY = "/singletaskworkflow/readbyid/{identity}";
        public static final String SINGLETASKWORKFLOW_READ_LIST = "/singletaskworkflow/list";
        public static final String SINGLETASKWORKFLOW_READ_LIST_BY_USERIDENTITY =
                "/singletaskworkflow/user/list/{identity}/{status}";

        public static final String TESTTHREETASKWORKFLOW_SAVE = "/threetaskworkflow/save";
        public static final String TESTTHREETASKWORKFLOW_READ_BY_IDENTITY = "/threetaskworkflow/readbyid/{identity}";
        public static final String TESTTHREETASKWORKFLOW_READ_LIST = "/threetaskworkflow/list";
        public static final String TESTTHREETASKWORKFLOW_READ_LIST_BY_USERIDENTITY =
                "/threetaskworkflow/user/list/{identity}/{status}";

        public static final String WORKFLOWMESSAGE_READ_BY_USERIDENTITY = "/workflowmessage/user/{identity}/{status}";
        public static final String WORKFLOWMESSAGE_READ_BY_WORKFLOWIDENTITY = "/workflowmessage/workflow/{workflowid}";
        public static final String WORKFLOWMESSAGE_SAVE = "/workflowmessage/save";
        public static final String WORKFLOWMESSAGE_CHANGE_WORKFLOWMESSAGE_STAUS_BY_WORKFLOWIDENTITY =
                "/workflowmessage/changestatus/{workflowidentity}/{stepidentity}/{useridentity}/{status}";


    }

    public static class ProfileUrlConstants {
        public static final String API001_PROFILE001_ROOT = "/api001/profile001";
        public static final String API001_PROFILE001_AUTHENTICATION = API001_PROFILE001_ROOT + "/authentication";
        public static final String API001_PROFILE001_AUTHENTICATION_SIGNIN =
                API001_PROFILE001_AUTHENTICATION + "/login";


        public static final String API001_CORE001_COMPANY = API001_PROFILE001_ROOT + "/companies";
        public static final String API001_CORE001_DEPARTMENT = API001_PROFILE001_ROOT + "/department";
        public static final String API001_CORE001_USERS = API001_PROFILE001_ROOT + "/users";
        public static final String API001_CORE001_USERGROUP = API001_PROFILE001_ROOT + "/usergroup";


        public static final String USER_SAVE = "/users/save";
        public static final String USER_DELETE = "/users/delete";
        public static final String USER_READ_BY_ID = "/readbyidentity/{id}";
        public static final String USER_USERGROUPS_LIST_BY_ID = "/user/groups/{id}";
        public static final String USER_DEPARTMENTS_LIST_BY_ID = "/user/departments/{id}";
        public static final String USER_DEPUTIES_LIST_BY_ID = "/user/deputies/{id}";
        public static final String USER_USER_LIST_BY_COMPANYID = "/company/users/{companyid}";
        public static final String USER_USER_LIST_BY_DEPARTMENTID = "/department/users/{id}";
        public static final String USERPROFILE_READ_BY_USERNAME = "/readprofile/email/{appIdentity}/{username}";
        public static final String USERPROFILE_READ_BY_USERID = "/readprofile/identity/{appIdentity}/{id}";
        public static final String USERDASHBOARDMENU_READ_BY_USERID = "/dashboardmenu/read/{appIdentity}/{userId}";
        public static final String USERDASHBOARDMENU_SAVE_BY_USERID = "/dashboardmenu/svae/{appIdentity}/{userId}";
        public static final String USER_PASSWORD_RESET = "/users/resetpassword/{id}";


        public static final String DEPARTMENT_READ_BY_ID = "/readbyid/{id}";
        public static final String DEPARTMENT_SAVE = "/save";
        public static final String DEPARTMENT_DELETE = "/delete";
        public static final String DEPARTMENT_READ_LIST = "/list";
        public static final String DEPARTMENT_READ_LIST_BY_COMPANYID = "/company/list/{companyid}";
        public static final String DEPARTMENT_READ_ALLUSERLIST_BY_DEPARTMENTID = "/alluser/list/{id}";
        public static final String DEPARTMENT_GET_MANAGER = "/manager/read/{id}";
        public static final String DEPARTMENT_GET_DEPUTY = "/deputy/read/{id}";

        public static final String USERGROUP_READ_BY_ID = "/readbyid/{id}";
        public static final String USERGROUP_READ_LIST = "/list";
        public static final String USERGROUP_READ_LIST_BY_COMPANYID = "/company/list/{companyid}";


    }


    public static class WorkflowUrlConstants {
        public static final String API001_WORKFLOW001_ROOT = "/api001/workflow001";
        public static final String API001_WORKFLOW001_WORKFLOW_ROOT = API001_WORKFLOW001_ROOT + "/workflow";
        public static final String API001_WORKFLOW001_INVOICEWORKFLOW_ROOT = API001_WORKFLOW001_ROOT + "/invoice";
        public static final String API001_WORKFLOW001_SINGLETASKWORKFLOW_ROOT = API001_WORKFLOW001_ROOT + "/singletask";
        public static final String API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT =
                API001_WORKFLOW001_ROOT + "/testthreetask";

        public static final String API001_WORKFLOW001_WORKFLOWMESSAGE_ROOT =
                API001_WORKFLOW001_ROOT + "/workflowmessage";
        public static final String API001_WORKFLOW001_WORKFLOWTYPE_ROOT = API001_WORKFLOW001_ROOT + "/workflowtype";
        public static final String API001_WORKFLOW001_WORKFLOWTYPESTEP_ROOT =
                API001_WORKFLOW001_ROOT + "/workflowtypestep";

    }


    public static class GuiUrlConstants {
        public static final String API001_GUI001_ROOT = "/api001/gui001";
        public static final String API001_GUI001_CALCACHDATA_ROOT = API001_GUI001_ROOT + "/cachdata";

        public static final String CACHDATA_READ_USER_WORKFLOWMESSAGELIST = "/user/readworkflowmessagelist/{companyid}/{userid}";
        public static final String CACHDATA_ADD_USER_WORKFLOWMESSAGELIST = "/user/addworkflowmessagelist/{companyid}/{useri}";
        public static final String CACHDATA_CAL_USER_DATARESET_BY_COMPANYID = "/user/datareset/{companyid}/{userid}";
        public static final String CACHDATA_CAL_USERLIST_DATARESET_BY_COMPANYID = "/userlist/datareset/{companyid}";
        public static final String CACHDATA_CAL_WORKFLOW_DATARESET_BY_WORKFLOWID = "/workflow/datareset/{companyid}/{id}";

    }
}