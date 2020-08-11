CREATE TABLE departments (
  id uuid NOT NULL PRIMARY KEY,
  identity varchar(45) DEFAULT NULL,
  company_id uuid NOT NULL,
  title varchar(200) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),

  CONSTRAINT FK_DEPARTMENTS_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE
);
