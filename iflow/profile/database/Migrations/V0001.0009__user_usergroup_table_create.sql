CREATE TABLE user_usergroup (
  user_id uuid NOT NULL,
  user_group uuid NOT NULL,
  created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
  PRIMARY KEY (user_id,user_group),
  CONSTRAINT FK_USERGROUPUSER_GROUP FOREIGN KEY (user_group) REFERENCES user_group (id),
  CONSTRAINT FK_USERGROUPUSER_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;