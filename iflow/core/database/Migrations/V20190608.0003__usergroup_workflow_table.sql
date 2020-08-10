



CREATE TABLE companies (
  id uuid NOT NULL PRIMARY KEY,
  identity varchar(45) DEFAULT NULL,
  company_name varchar(45) NOT NULL,
  company_type varchar(45) NOT NULL,
  company_type_custome varchar(45) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc')
);

CREATE TABLE departments (
  id uuid NOT NULL PRIMARY KEY,
  identity varchar(45) DEFAULT NULL,
  company_id uuid NOT NULL DEFAULT 1,
  title varchar(200) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  
  CONSTRAINT FK_DEPARTMENTS_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE iflow_roles (
  id uuid NOT NULL PRIMARY KEY,
  title varchar(200) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc')
) ;

CREATE TABLE users (
  id uuid NOT NULL PRIMARY KEY,
  identity varchar(45) DEFAULT NULL UNIQUE,
  company_id uuid NOT NULL DEFAULT 1,
  email varchar(255)  NOT NULL UNIQUE,
  birthdate date DEFAULT NULL,
  password varchar(255)  NOT NULL DEFAULT '-',
  firstname varchar(45)  DEFAULT NULL,
  lastname varchar(45)  DEFAULT NULL,
  permission smallint NOT NULL DEFAULT 1,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  
  CONSTRAINT FK_USERS_COMPANIES FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;

 

CREATE TABLE user_group (
  id uuid NOT NULL PRIMARY KEY,
  identity varchar(45) DEFAULT NULL,
  title varchar(200) NOT NULL,
  company_id uuid NOT NULL DEFAULT 1,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  
  CONSTRAINT FK_USERGROUP_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;


CREATE TABLE user_deputy (
  user_id uuid NOT NULL,
  deputy_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  PRIMARY KEY (user_id,deputy_id),
  CONSTRAINT FK_USERDEPUTY_DEPUTY FOREIGN KEY (deputy_id) REFERENCES users (id),
  CONSTRAINT FK_USERDEPUTY_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;


CREATE TABLE user_departments (
  id uuid NOT NULL PRIMARY KEY,
  user_id uuid NOT NULL,
  department_id uuid NOT NULL,
  member_type int NOT NULL default 5,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  CONSTRAINT FK_USERDEPARTMENTS_DEPARTMENTS FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_USERDEPARTMENTS_USERS FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;


CREATE TABLE user_usergroup (
  user_id uuid NOT NULL,
  user_group uuid NOT NULL,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  PRIMARY KEY (user_id,user_group),
  CONSTRAINT FK_USERGROUPUSER_GROUP FOREIGN KEY (user_group) REFERENCES user_group (id),
  CONSTRAINT FK_USERGROUPUSER_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;


CREATE TABLE user_roles (
  user_id uuid NOT NULL,
  role uuid NOT NULL,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  PRIMARY KEY (user_id,role),
  CONSTRAINT FK_USERROLES_ROLE FOREIGN KEY (role) REFERENCES iflow_roles (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_USERROLES_USERS FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE workflow_type (
  id uuid NOT NULL PRIMARY KEY,
  identity varchar(45) DEFAULT NULL,
  title varchar(200) NOT NULL,
  assign_type SMALLINT NOT NULL DEFAULT 1,
  send_to_controller SMALLINT NOT NULL DEFAULT 1,
  increase_step_automatic SMALLINT DEFAULT '0',
  allow_assign SMALLINT DEFAULT '0',
  commecnts text,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc')
);

CREATE TABLE workflow_type_step (
  id uuid NOT NULL PRIMARY KEY,
  identity varchar(45) DEFAULT NULL,
  workflow_type_id uuid NOT NULL,
  title varchar(200) NOT NULL,
  step_index smallint NOT NULL DEFAULT 0,
  view_name varchar(150) NOT NULL DEFAULT '-',
  expire_days smallint NOT NULL,
  commecnts text,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  
  CONSTRAINT FK_WORKFLOWTYPESTEP_WORKFLOWTYPE FOREIGN KEY (workflow_type_id) REFERENCES workflow_type (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE workflow (
  id uuid NOT NULL PRIMARY KEY,
  company_id uuid NOT NULL,
  identity varchar(45) DEFAULT NULL,
  workflow_type_id uuid NOT NULL,
  current_step uuid NOT NULL,
  status uuid NOT NULL,
  comments text,
  controller uuid NOT NULL,
  created_by uuid DEFAULT NULL,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  
  CONSTRAINT FK_WORKFLOW_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_WORKFLOW_USERS FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT FK_WORKFLOW_WORKFLOWTYPE FOREIGN KEY (workflow_type_id) REFERENCES workflow_type (id) ON UPDATE CASCADE,
  CONSTRAINT FK_WORKFLOW_WORKFLOWTYPESTEP FOREIGN KEY (current_step) REFERENCES workflow_type_step (id) ON UPDATE CASCADE
);



CREATE TABLE workflow_actions (
  id uuid NOT NULL PRIMARY KEY,
  workflow_id uuid NOT NULL,
  assign_to uuid NOT NULL DEFAULT '0',
  current_step_id uuid NOT NULL DEFAULT '0',
  comments varchar(45) DEFAULT NULL,
  status uuid DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  
  CONSTRAINT FK_WORKFLOWACTION_WORKFLOW FOREIGN KEY (workflow_id) REFERENCES workflow (id) ON UPDATE CASCADE
);

CREATE TABLE workflow_files (
  id uuid NOT NULL PRIMARY KEY,
  identity varchar(45) DEFAULT NULL,
  workflow_id uuid NOT NULL,
  title varchar(200) NOT NULL,
  extention varchar(10) NOT NULL,
  active_filepath varchar(500) NOT NULL,
  comments text,
  active_version integer NOT NULL DEFAULT 1,
  status smallint NOT NULL DEFAULT 1,
  created_by uuid DEFAULT NULL,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
   
  CONSTRAINT FK_WORKFLOWFILE_USERS FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT FK_WORKFLOWFILE_WORKFLOW FOREIGN KEY (workflow_id) REFERENCES workflow (id) ON UPDATE CASCADE
);

CREATE TABLE workflow_files_versions (
  id uuid NOT NULL PRIMARY KEY,
  workflow_file_id uuid NOT NULL,
  filepath varchar(500) NOT NULL,
  comments text,
  file_version integer NOT NULL DEFAULT 1,
  status smallint NOT NULL DEFAULT 1,
  created_by uuid DEFAULT NULL,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  CONSTRAINT FK_WORKFLOWFILEVERSION_WORKFLOWFILE FOREIGN KEY (workflow_file_id) REFERENCES workflow_files (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE company_workflow_type (
  company_id uuid NOT NULL,
  workflow_type_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  PRIMARY KEY (company_id,workflow_type_id),
  CONSTRAINT FK_COMPANYWORKFLOWTYPE_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_COMPANYWORKFLOWTYPE_WORKFLOWTYPE FOREIGN KEY (workflow_type_id) REFERENCES workflow_type (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;

