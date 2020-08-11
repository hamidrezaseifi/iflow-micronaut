CREATE TABLE user_dashboard_menus
(
    id uuid NOT NULL primary key,
    user_id uuid NOT NULL,
    app_id varchar(45) DEFAULT NULL,
    menu_id varchar(45) DEFAULT NULL,
    row_index int NOT NULL,
    column_index int NOT NULL,
    status smallint NOT NULL DEFAULT 1,
    version integer NOT NULL DEFAULT 1,
    created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
    CONSTRAINT "FK_USER_DASHBOARD_MENUS_USERS" FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);