CREATE SEQUENCE user_seq START 1;

CREATE TABLE users(
id bigint NOT NULL DEFAULT nextval('user_seq'),
username character varying(255) NOT NULL unique,
first_name character varying(255) NOT NULL,
last_name character varying(255) NOT NULL,
password character varying(100) NOT NULL,
user_role character varying(100) NOT NULL,
is_active boolean NOT NULL,
CONSTRAINT user_pkey PRIMARY KEY(id)
);