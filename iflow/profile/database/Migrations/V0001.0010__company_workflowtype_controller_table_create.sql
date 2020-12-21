CREATE TABLE company_workflowtype_controller (
  id serial PRIMARY KEY,
  company_id uuid NOT NULL,
  workflow_type_id uuid NOT NULL,
  user_id uuid NOT NULL,
  priority smallint NOT NULL,
  created_at timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  UNIQUE (company_id,workflow_type_id,user_id,priority),
  CONSTRAINT FK_COMPANY_WORKFLOWTYPE_CONTROLLER_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_COMPANY_WORKFLOWTYPE_CONTROLLER_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;

