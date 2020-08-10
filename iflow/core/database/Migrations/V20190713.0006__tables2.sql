
CREATE TABLE invoice_workflow (
  workflow_id uuid NOT NULL PRIMARY KEY,
  sender varchar(80) NOT NULL,
  ext_reg_number varchar(45) DEFAULT NULL,
  invoce_date date DEFAULT NULL,
  partner_code varchar(45) DEFAULT NULL,
  vendor_number varchar(45) DEFAULT NULL,
  vendor_name varchar(45) DEFAULT NULL,
  direct_debit_permission smallint NOT NULL DEFAULT 0,
  invoice_type smallint NOT NULL DEFAULT 1,
  discount_enter date NOT NULL,
  discount_rate float NOT NULL DEFAULT 0,
  discount_deadline int NOT NULL DEFAULT 0,
  discount_date date NOT NULL,
  payment_amount float NOT NULL DEFAULT 0
) ;


INSERT INTO workflow_type(id, identity, title, assign_type, send_to_controller, increase_step_automatic, allow_assign, commecnts, status, version) VALUES (1, 'singletaskworkflowtype', 'Einzel Aufgabe',1,1,1,0,NULL,1,1);
INSERT INTO workflow_type(id, identity, title, assign_type, send_to_controller, increase_step_automatic, allow_assign, commecnts, status, version) VALUES (2,'threetaskworkflowtype', 'Drei Schritt Aufgabe',2,0,1,1,NULL,1,1);
INSERT INTO workflow_type(id, identity, title, assign_type, send_to_controller, increase_step_automatic, allow_assign, commecnts, status, version) VALUES (3,'invoiceworkflowtype', 'Rechnung Wrokflow',2,0,1,1,NULL,1,1);

INSERT INTO workflow_type_step(id, identity, workflow_type_id, title, step_index, view_name, expire_days, commecnts, status, version) VALUES (1,'singletasktypestep', 1,'Augabe',1,'workflow/singletask/edit',15,NULL,1,1);

INSERT INTO workflow_type_step(id, identity, workflow_type_id, title, step_index, view_name, expire_days, commecnts, status, version) VALUES (2,'threetasktypestep1', 2,'Schritt 1',1,'workflow/testthreetask/edit',15,NULL,1,1);
INSERT INTO workflow_type_step(id, identity, workflow_type_id, title, step_index, view_name, expire_days, commecnts, status, version) VALUES (3,'threetasktypestep2', 2,'Schritt 2',2,'workflow/testthreetask/edit',15,NULL,1,1);
INSERT INTO workflow_type_step(id, identity, workflow_type_id, title, step_index, view_name, expire_days, commecnts, status, version) VALUES (4,'threetasktypestep3', 2,'Schritt 3',3,'wworkflow/testthreetask/edit',15,NULL,1,1);

INSERT INTO workflow_type_step(id, identity, workflow_type_id, title, step_index, view_name, expire_days, commecnts, status, version) VALUES (5,'invocetasktypestep1', 3,'Rechungsverteilung',1,'workflow/invoice/invoice_assign',15,NULL,1,1);
INSERT INTO workflow_type_step(id, identity, workflow_type_id, title, step_index, view_name, expire_days, commecnts, status, version) VALUES (6,'invocetasktypestep2', 3,'Rechungsprüfung',2,'workflow/invoice/invoice_testing',15,NULL,1,1);
INSERT INTO workflow_type_step(id, identity, workflow_type_id, title, step_index, view_name, expire_days, commecnts, status, version) VALUES (7,'invocetasktypestep3', 3,'Rechungsfreigabe',3,'workflow/invoice/invoice_release',15,NULL,1,1);

CREATE TABLE workflow_message (
  id uuid NOT NULL PRIMARY KEY,
  workflow_id uuid NOT NULL,
  step_id uuid NOT NULL DEFAULT 0,
  user_id uuid NOT NULL,
  message varchar(500) NOT NULL DEFAULT 'no message',
  created_by uuid NOT NULL,
  message_type smallint NOT NULL DEFAULT 1,
  version int NOT NULL DEFAULT 1,
  status smallint DEFAULT NULL,
  expire_days smallint NOT NULL,
  created_at timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  CONSTRAINT FK_WORKFLOWOFFER_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT FK_WORKFLOWOFFER_WORKFLOW FOREIGN KEY (workflow_id) REFERENCES workflow (id) ON DELETE CASCADE ON UPDATE RESTRICT
) ;
ALTER SEQUENCE workflow_message_id_seq OWNED BY workflow_message.id;


INSERT INTO company_workflow_type(company_id,workflow_type_id) VALUES(1, 1);
INSERT INTO company_workflow_type(company_id,workflow_type_id) VALUES(1, 2);
INSERT INTO company_workflow_type(company_id,workflow_type_id) VALUES(1, 3);



