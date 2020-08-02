package com.pth.iflow.common.moduls.security;

public class RestAccessRoles {

  public static class General {

    public final static String USER_ROLE = "ROLE_USER";
    public final static String HAS_ROLE_USER = "hasRole('ROLE_USER')";

  }

  public static class Users {

    public final static String USERS_READ = "USERS_READ";
    public final static String HAS_ROLE_USERS_READ = "hasRole('" + USERS_READ + "')";

    public final static String USERS_SAVE = "USERS_SAVE";
    public final static String HAS_ROLE_USERS_SAVE = "hasRole('" + USERS_SAVE + "')";

    public final static String USERS_DELETE = "USERS_DELETE";
    public final static String HAS_ROLE_USERS_DELETE = "hasRole('" + USERS_DELETE + "')";

    public final static String USERS_UPDATE = "USERS_UPDATE";
    public final static String HAS_ROLE_USERS_UPDATE = "hasRole('" + USERS_UPDATE + "')";

  }

  public static class Companies {

    public final static String COMPANIES_READ = "COMPANIES_READ";
    public final static String HAS_ROLE_COMPANIES_READ = "hasRole('" + COMPANIES_READ + "')";

    public final static String COMPANIES_SAVE = "COMPANIES_SAVE";
    public final static String HAS_ROLE_COMPANIES_SAVE = "hasRole('" + COMPANIES_SAVE + "')";

    public final static String COMPANIES_DELETE = "COMPANIES_DELETE";
    public final static String HAS_ROLE_COMPANIES_DELETE = "hasRole('" + COMPANIES_DELETE + "')";

    public final static String COMPANIES_UPDATE = "COMPANIES_UPDATE";
    public final static String HAS_ROLE_COMPANIES_UPDATE = "hasRole('" + COMPANIES_UPDATE + "')";

  }

  public static class Departments {

    public final static String DEPARTMENTS_READ = "DEPARTMENTS_READ";
    public final static String HAS_ROLE_DEPARTMENTS_READ = "hasRole('" + DEPARTMENTS_READ + "')";

    public final static String DEPARTMENTS_SAVE = "DEPARTMENTS_SAVE";
    public final static String HAS_ROLE_DEPARTMENTS_SAVE = "hasRole('" + DEPARTMENTS_SAVE + "')";

    public final static String DEPARTMENTS_DELETE = "DEPARTMENTS_DELETE";
    public final static String HAS_ROLE_DEPARTMENTS_DELETE = "hasRole('" + DEPARTMENTS_DELETE + "')";

    public final static String DEPARTMENTS_UPDATE = "DEPARTMENTS_UPDATE";
    public final static String HAS_ROLE_DEPARTMENTS_UPDATE = "hasRole('" + DEPARTMENTS_UPDATE + "')";

  }

  public static class InvoiceWorkflow {

    public final static String INVOICE_READ = "INVOICE_READ";
    public final static String HAS_ROLE_INVOICE_READ = "hasRole('" + INVOICE_READ + "')";

    public final static String INVOICE_CREATE = "INVOICE_CREATE";
    public final static String HAS_ROLE_INVOICE_CREATE = "hasRole('" + INVOICE_CREATE + "')";

    public final static String INVOICE_SAVE = "INVOICE_SAVE";
    public final static String HAS_ROLE_INVOICE_SAVE = "hasRole('" + INVOICE_SAVE + "')";

    public final static String INVOICE_DELETE = "INVOICE_DELETE";
    public final static String HAS_ROLE_INVOICE_DELETE = "hasRole('" + INVOICE_DELETE + "')";

    public final static String INVOICE_UPDATE = "INVOICE_UPDATE";
    public final static String HAS_ROLE_INVOICE_UPDATE = "hasRole('" + INVOICE_UPDATE + "')";

  }

  public static class SingleTaskWorkflow {

    public final static String SINGLETASK_READ = "SINGLETASK_READ";
    public final static String HAS_ROLE_SINGLETASK_READ = "hasRole('" + SINGLETASK_READ + "')";

    public final static String SINGLETASK_CREATE = "SINGLETASK_CREATE";
    public final static String HAS_ROLE_SINGLETASK_CREATE = "hasRole('" + SINGLETASK_CREATE + "')";

    public final static String SINGLETASK_SAVE = "SINGLETASK_SAVE";
    public final static String HAS_ROLE_SINGLETASK_SAVE = "hasRole('" + SINGLETASK_SAVE + "')";

    public final static String SINGLETASK_DELETE = "SINGLETASK_DELETE";
    public final static String HAS_ROLE_SINGLETASK_DELETE = "hasRole('" + SINGLETASK_DELETE + "')";

    public final static String SINGLETASK_UPDATE = "SINGLETASK_UPDATE";
    public final static String HAS_ROLE_SINGLETASK_UPDATE = "hasRole('" + SINGLETASK_UPDATE + "')";

  }

  public static class TestThreeTaskWorkflow {

    public final static String TESTTHREETASK_READ = "TESTTHREETASK_READ";
    public final static String HAS_ROLE_TESTTHREETASK_READ = "hasRole('" + TESTTHREETASK_READ + "')";

    public final static String TESTTHREETASK_CREATE = "TESTTHREETASK_CREATE";
    public final static String HAS_ROLE_TESTTHREETASK_CREATE = "hasRole('" + TESTTHREETASK_CREATE + "')";

    public final static String TESTTHREETASK_SAVE = "TESTTHREETASK_SAVE";
    public final static String HAS_ROLE_TESTTHREETASK_SAVE = "hasRole('" + TESTTHREETASK_SAVE + "')";

    public final static String TESTTHREETASK_DELETE = "TESTTHREETASK_DELETE";
    public final static String HAS_ROLE_TESTTHREETASK_DELETE = "hasRole('" + TESTTHREETASK_DELETE + "')";

    public final static String TESTTHREETASK_UPDATE = "TESTTHREETASK_UPDATE";
    public final static String HAS_ROLE_TESTTHREETASK_UPDATE = "hasRole('" + TESTTHREETASK_UPDATE + "')";

  }

  public static class Workflow {

    public final static String WORKFLOW_READ = "WORKFLOW_READ";
    public final static String HAS_ROLE_WORKFLOW_READ = "hasRole('" + WORKFLOW_READ + "')";

  }

}
