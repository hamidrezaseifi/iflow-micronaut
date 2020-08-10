CREATE TABLE public.users (
	id uuid NOT NULL,
	company_id uuid NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(500) NOT NULL,
	password_salt varchar(255) NOT NULL,
	status int2 NOT NULL DEFAULT 1,
	"version" int4 NOT NULL DEFAULT 1,
	"identity" varchar(50) NOT NULL,
	created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT users_pk PRIMARY KEY (id)
);