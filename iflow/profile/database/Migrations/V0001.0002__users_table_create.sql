CREATE TABLE public.users (
    id uuid NOT NULL PRIMARY KEY,
    identity varchar(45) DEFAULT NULL UNIQUE,
    company_id uuid NOT NULL,
    username varchar(50) NOT NULL,
    password_hash varchar(500) NOT NULL,
    password_salt varchar(255) NOT NULL,
    email varchar(255)  NOT NULL UNIQUE,
    birthdate date NULL,
    firstname varchar(45)  NOT NULL,
    lastname varchar(45)  NOT NULL,
    permission smallint NOT NULL DEFAULT 1,
    status smallint NOT NULL DEFAULT 1,
    version integer NOT NULL DEFAULT 1,
    created_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),
    updated_at timestamp without time zone NOT NULL default (now() at time zone 'utc'),

    CONSTRAINT FK_USERS_COMPANIES FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE ON UPDATE CASCADE
);
