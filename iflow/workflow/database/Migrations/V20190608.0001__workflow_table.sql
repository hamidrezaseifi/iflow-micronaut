
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
  status int NOT NULL,
  comments text,
  controller uuid NOT NULL,
  created_by uuid DEFAULT NULL,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  
  CONSTRAINT FK_WORKFLOW_WORKFLOWTYPE FOREIGN KEY (workflow_type_id) REFERENCES workflow_type (id) ON UPDATE CASCADE,
  CONSTRAINT FK_WORKFLOW_WORKFLOWTYPESTEP FOREIGN KEY (current_step) REFERENCES workflow_type_step (id) ON UPDATE CASCADE
);



CREATE TABLE workflow_actions (
  id uuid NOT NULL PRIMARY KEY,
  workflow_id uuid NOT NULL,
  assign_to uuid NULL,
  current_step_id uuid NULL,
  comments varchar(45) DEFAULT NULL,
  status smallint DEFAULT 1,
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

CREATE TABLE public.company_workflow_type (
	id serial NOT NULL PRIMARY KEY,
	company_id uuid NOT NULL,
	workflow_type_id uuid NOT NULL,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now())
);

