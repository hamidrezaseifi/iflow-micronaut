CREATE TABLE iflow_roles (
  id uuid NOT NULL PRIMARY KEY,
  title varchar(200) NOT NULL,
  status smallint NOT NULL DEFAULT 1,
  version integer NOT NULL DEFAULT 1,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc')
) ;
