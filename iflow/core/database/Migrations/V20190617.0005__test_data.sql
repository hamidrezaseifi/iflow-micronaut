

INSERT INTO companies VALUES (1,'test-company-1','Test Firma 1' , 'GMBH' , '',1,1,'2019-06-07 05:23:30.474205','2019-06-07 05:23:30.474205');

INSERT INTO users VALUES (1,'test-company-1-0000000001',1,'admin@iflow.de','1977-01-01','admin','Admin','Admin',1,1,1,'2019-05-24 07:59:38.703057','2019-05-24 07:59:38.703057'),(2,'test-company-1-0000000002',1,'user@iflow.de','1980-02-03','user','User','User',5,1,1,'2019-05-24 08:00:15.854422','2019-05-24 08:00:15.854422'),(3,'test-company-1-0000000003',1,'user2@iflow.de','2000-05-12','user','User 2','User 2',5,1,1,'2019-06-14 05:45:49.062034','2019-06-14 05:45:49.062034'),(4,'test-company-1-0000000004',1,'user3@iflow.de','1991-11-23','user','User 3','User 3',5,1,1,'2019-06-14 05:46:12.789077','2019-06-14 05:46:12.789077');

INSERT INTO departments(id, identity, company_id, title) VALUES (1, 'dep1', 1,'Dep 1'),(2,'dep2', 1,'Dep 2'),(3,'dep3', 1,'Dep 3');

INSERT INTO user_departments VALUES (1, 1,1,20,'2019-06-14 08:03:17.213536'),(2, 1,2,20,'2019-06-14 08:03:17.222388');

INSERT INTO user_deputy(user_id, deputy_id) VALUES (1,2),(1,3),(1,4);

INSERT INTO user_group(id, identity, title, company_id) VALUES (1,'Group-1','Group 1',1),(2,'Group-2','Group 2',1),(3,'Group-3','Group 3',1);

INSERT INTO user_usergroup VALUES (1,1,'2019-06-14 08:01:51.738670'),(1,2,'2019-06-14 08:01:51.740144');

INSERT INTO iflow_roles (id, title) VALUES ('1', 'Role 1');
INSERT INTO iflow_roles (id, title) VALUES ('2', 'Role 2');
INSERT INTO iflow_roles (id, title) VALUES ('3', 'Role 3');



