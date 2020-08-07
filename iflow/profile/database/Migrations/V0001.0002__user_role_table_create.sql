CREATE TABLE public.users_roles (
	user_id uuid NOT NULL,
	"role" varchar(200) NOT NULL,
	created_at timestamp NOT NULL DEFAULT timezone('utc'::text, now()),
	CONSTRAINT users_roles_pk PRIMARY KEY (user_id, role)
);


-- public.users_roles foreign keys

ALTER TABLE public.users_roles ADD CONSTRAINT users_roles_fk FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;
