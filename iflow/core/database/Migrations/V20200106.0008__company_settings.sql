
CREATE TABLE company_workflowtype_controller (
  company_id uuid NOT NULL,
  workflow_type_id uuid NOT NULL,
  user_id uuid NOT NULL,
  priority smallint NOT NULL,
  created_at timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (company_id,workflow_type_id,user_id,priority),
  CONSTRAINT FK_COMPANY_WORKFLOWTYPE_CONTROLLER_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_COMPANY_WORKFLOWTYPE_CONTROLLER_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;

INSERT INTO company_workflowtype_controller (company_id, workflow_type_id, user_id, priority) VALUES (1, 1, 1, 1);
INSERT INTO company_workflowtype_controller (company_id, workflow_type_id, user_id, priority) VALUES (1, 2, 1, 1);
INSERT INTO company_workflowtype_controller (company_id, workflow_type_id, user_id, priority) VALUES (1, 3, 1, 1);
INSERT INTO company_workflowtype_controller (company_id, workflow_type_id, user_id, priority) VALUES (1, 1, 2, 2);
INSERT INTO company_workflowtype_controller (company_id, workflow_type_id, user_id, priority) VALUES (1, 3, 2, 2);


