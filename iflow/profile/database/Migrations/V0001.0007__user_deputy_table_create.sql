CREATE TABLE user_deputy (
  user_id uuid NOT NULL,
  deputy_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  PRIMARY KEY (user_id,deputy_id),
  CONSTRAINT FK_USERDEPUTY_DEPUTY FOREIGN KEY (deputy_id) REFERENCES users (id),
  CONSTRAINT FK_USERDEPUTY_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;
