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
