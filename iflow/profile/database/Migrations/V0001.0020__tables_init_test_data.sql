
INSERT INTO public.companies (id,identity,company_name,company_type,company_type_custome,status,"version") VALUES
('4420cc31-339d-49f2-8c5d-f06b58244305','test-company-1','Test Company 1','EINZELUNTERNEHMEN','',1,0);


INSERT INTO public.users (id,company_id,username,password_hash,password_salt,email, birthdate, firstname, lastname, status,"version","identity",created_at,updated_at) VALUES
('d94c7d98-0240-4633-b300-0777d5b30c12','4420cc31-339d-49f2-8c5d-f06b58244305','user@iflow.de','VbiADLcB6b08OyEqGrerGhDd+q3JzwXZVfuSfiKjotcqvTC1/m5tEVUB9ANhUvGZuc+68wOH5YX0q5V/hq6RWg==','f9639ce2-430d-4910-9096-d471f365e1e5#851e206d-5327-4c0b-91e5-345043deee61','user@iflow.de', '1980-1-1', 'user', 'user', 1,0,'user-iflow-1','2020-08-06 19:57:41.770','2020-08-06 19:57:41.770')
,('2cde5028-f047-4dbb-a593-157e0b89dc6d','4420cc31-339d-49f2-8c5d-f06b58244305','user2@iflow.de','7/6DjCedoPJgUpfPhCVtv9ivRVtpsY9LYGkSakkX00tf+64mmILihbmPKyVrejTbT+Wx1fCJCvUBVgttEjMwFA==','5bbe3930-c11d-4f8d-b91e-0eaa4239299a#bf5bdd65-99fe-45b5-9701-5b7ebde76362','user2@iflow.de', '1980-1-1', 'user2', 'user2', 1,0,'user-iflow-2','2020-08-06 19:58:01.567','2020-08-06 19:58:01.567')
,('2653d5a5-200b-4fd6-a820-89de59e918bb','4420cc31-339d-49f2-8c5d-f06b58244305','test@iflow.de','uBZ2nMyHq8UFSyQhswrltJU4UKk55Zhy6B3AiOKlDxVjjys7XlwhMJ8uQhRyKHhZ0bl9AqUT9/o3QhDHA/vKNg==','2e60fc1f-6070-4640-8e47-2f0f307f50b2#b9353e07-db86-403b-8bbd-35ac3f8f96d9','test@iflow.de', '1980-1-1', 'test', 'test', 1,0,'test-iflow-1','2020-08-06 19:58:29.139','2020-08-06 19:58:29.139')
,('12ae03f5-81b8-44bc-ac85-4b985d0af79f','4420cc31-339d-49f2-8c5d-f06b58244305','admin@iflow.de','UpSN7EEBL2Pv0YLFl+7ofQs9X1H+CL8eGt8LG5v7An2Po5Plm0O1D3CQLfR4TikuwQMbkxePIMXx21C27NmQiw==','666dc230-d8d8-41b8-b757-eeafac4be840#9d45426a-9f29-4231-a5cd-aa847ae992fe','admin@iflow.de', '1980-1-1', 'admin', 'admin', 1,0,'admin-iflow-1','2020-08-06 19:57:06.000','2020-08-06 19:57:06.951')
;

INSERT INTO public.users_roles (user_id,"role",created_at) VALUES
('12ae03f5-81b8-44bc-ac85-4b985d0af79f','DATAENTRY','2020-08-06 17:57:06.951')
,('12ae03f5-81b8-44bc-ac85-4b985d0af79f','ADMIN','2020-08-06 17:57:06.951')
,('12ae03f5-81b8-44bc-ac85-4b985d0af79f','VIEW','2020-08-06 17:57:06.951')
,('d94c7d98-0240-4633-b300-0777d5b30c12','DATAENTRY','2020-08-06 17:57:41.770')
,('d94c7d98-0240-4633-b300-0777d5b30c12','ADMIN','2020-08-06 17:57:41.770')
,('d94c7d98-0240-4633-b300-0777d5b30c12','VIEW','2020-08-06 17:57:41.770')
,('2cde5028-f047-4dbb-a593-157e0b89dc6d','DATAENTRY','2020-08-06 17:58:01.567')
,('2cde5028-f047-4dbb-a593-157e0b89dc6d','ADMIN','2020-08-06 17:58:01.567')
,('2cde5028-f047-4dbb-a593-157e0b89dc6d','VIEW','2020-08-06 17:58:01.567')
,('2653d5a5-200b-4fd6-a820-89de59e918bb','DATAENTRY','2020-08-06 17:58:29.139')
;
INSERT INTO public.users_roles (user_id,"role",created_at) VALUES
('2653d5a5-200b-4fd6-a820-89de59e918bb','ADMIN','2020-08-06 17:58:29.139')
,('2653d5a5-200b-4fd6-a820-89de59e918bb','VIEW','2020-08-06 17:58:29.139')
;


INSERT INTO company_workflowtype_controller (company_id,workflow_type_id,user_id,priority,created_at) VALUES
	 ('4420cc31-339d-49f2-8c5d-f06b58244305','2cde5028-f047-4dbb-a593-157e0b89dc61','12ae03f5-81b8-44bc-ac85-4b985d0af79f',1,'2020-12-21 11:28:13.952985'),
	 ('4420cc31-339d-49f2-8c5d-f06b58244305','2cde5028-f047-4dbb-a593-157e0b89dc62','12ae03f5-81b8-44bc-ac85-4b985d0af79f',1,'2020-12-21 11:28:13.952985'),
	 ('4420cc31-339d-49f2-8c5d-f06b58244305','2cde5028-f047-4dbb-a593-157e0b89dc63','12ae03f5-81b8-44bc-ac85-4b985d0af79f',1,'2020-12-21 11:28:13.952985'),
	 ('4420cc31-339d-49f2-8c5d-f06b58244305','2cde5028-f047-4dbb-a593-157e0b89dc61','d94c7d98-0240-4633-b300-0777d5b30c12',2,'2020-12-21 11:28:13.952985'),
	 ('4420cc31-339d-49f2-8c5d-f06b58244305','2cde5028-f047-4dbb-a593-157e0b89dc63','d94c7d98-0240-4633-b300-0777d5b30c12',2,'2020-12-21 11:28:13.952985'),
	 ('4420cc31-339d-49f2-8c5d-f06b58244305','d94c7d98-0240-4633-b300-0777d5b30ca1','12ae03f5-81b8-44bc-ac85-4b985d0af79f',1,'2020-12-21 11:44:48.092879'),
	 ('4420cc31-339d-49f2-8c5d-f06b58244305','d94c7d98-0240-4633-b300-0777d5b30ca2','12ae03f5-81b8-44bc-ac85-4b985d0af79f',1,'2020-12-22 12:28:17.894954'),
	 ('4420cc31-339d-49f2-8c5d-f06b58244305','d94c7d98-0240-4633-b300-0777d5b30ca3','12ae03f5-81b8-44bc-ac85-4b985d0af79f',1,'2020-12-22 12:35:51.971939');

INSERT INTO departments(id, identity, company_id, title) VALUES
('12ae03f5-81b8-44bc-ac85-4b985d0af7d1','dep1', '4420cc31-339d-49f2-8c5d-f06b58244305','Dep 1'),
('12ae03f5-81b8-44bc-ac85-4b985d0af7d2','dep2', '4420cc31-339d-49f2-8c5d-f06b58244305','Dep 2'),
('12ae03f5-81b8-44bc-ac85-4b985d0af7d3','dep3', '4420cc31-339d-49f2-8c5d-f06b58244305','Dep 3');


INSERT INTO public.user_departments (user_id, department_id, member_type)
VALUES
('12ae03f5-81b8-44bc-ac85-4b985d0af79f', '12ae03f5-81b8-44bc-ac85-4b985d0af7d1', 20),
('12ae03f5-81b8-44bc-ac85-4b985d0af79f', '12ae03f5-81b8-44bc-ac85-4b985d0af7d2', 20),
('12ae03f5-81b8-44bc-ac85-4b985d0af79f', '12ae03f5-81b8-44bc-ac85-4b985d0af7d3', 15),
('2cde5028-f047-4dbb-a593-157e0b89dc6d', '12ae03f5-81b8-44bc-ac85-4b985d0af7d1', 5),
('2cde5028-f047-4dbb-a593-157e0b89dc6d', '12ae03f5-81b8-44bc-ac85-4b985d0af7d2', 5),
('2cde5028-f047-4dbb-a593-157e0b89dc6d', '12ae03f5-81b8-44bc-ac85-4b985d0af7d3', 5);

INSERT INTO user_deputy(user_id, deputy_id) VALUES ('12ae03f5-81b8-44bc-ac85-4b985d0af79f','2cde5028-f047-4dbb-a593-157e0b89dc6d');

INSERT INTO user_group(id, identity, title, company_id)
VALUES
('12ae03f5-81b8-44bc-ac85-4b985d0af7c1','Group-1','Group 1','4420cc31-339d-49f2-8c5d-f06b58244305'),
('12ae03f5-81b8-44bc-ac85-4b985d0af7c2','Group-2','Group 2','4420cc31-339d-49f2-8c5d-f06b58244305'),
('12ae03f5-81b8-44bc-ac85-4b985d0af7c3','Group-3','Group 3','4420cc31-339d-49f2-8c5d-f06b58244305');

INSERT INTO user_usergroup
VALUES
('12ae03f5-81b8-44bc-ac85-4b985d0af79f','12ae03f5-81b8-44bc-ac85-4b985d0af7c1'),
('12ae03f5-81b8-44bc-ac85-4b985d0af79f','12ae03f5-81b8-44bc-ac85-4b985d0af7c2'),
('12ae03f5-81b8-44bc-ac85-4b985d0af79f','12ae03f5-81b8-44bc-ac85-4b985d0af7c3');

INSERT INTO iflow_roles (id, title) VALUES ('22ae03f5-81b8-44bc-ac85-4b985d0af711', 'Role 1');
INSERT INTO iflow_roles (id, title) VALUES ('22ae03f5-81b8-44bc-ac85-4b985d0af712', 'Role 2');
INSERT INTO iflow_roles (id, title) VALUES ('22ae03f5-81b8-44bc-ac85-4b985d0af713', 'Role 3');
