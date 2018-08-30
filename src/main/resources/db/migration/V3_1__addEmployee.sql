CREATE SEQUENCE employee_seq START 1;

CREATE TABLE employees(
  id BIGINT NOT NULL DEFAULT nextval('employee_seq'),
  first_name CHARACTER VARYING(255) NOT NULL,
  last_name CHARACTER VARYING(255) NOT NULL,
  working_station CHARACTER VARYING (255) NOT NULL,
  CONSTRAINT employee_pkey PRIMARY KEY(id)
);