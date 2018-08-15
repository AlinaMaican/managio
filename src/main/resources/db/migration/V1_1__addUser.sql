CREATE TYPE e_user_role AS ENUM (
  'USER','ADMIN','MANAGER','CONSULTANT');

CREATE TABLE users(
id bigint NOT NULL,
username character varying(255),
first_name character varying(255),
last_name character varying(255),
password character varying(100),
user_role e_user_role,
is_active boolean,
CONSTRAINT user_pkey PRIMARY KEY(id)
);

CREATE SEQUENCE user_seq START 1;