CREATE TABLE user_departments (
  id serial NOT NULL PRIMARY KEY,
  user_id uuid NOT NULL,
  department_id uuid NOT NULL,
  member_type int NOT NULL default 5,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  CONSTRAINT FK_USERDEPARTMENTS_DEPARTMENTS FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_USERDEPARTMENTS_USERS FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;