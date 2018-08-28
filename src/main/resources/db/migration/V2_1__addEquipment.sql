CREATE SEQUENCE equipment_seq START 1;

CREATE TABLE equipments(
  id bigint NOT NULL DEFAULT nextval('equipment_seq'),
  name character varying(255) NOT NULL,
  code character varying(255) NOT NULL unique,
  mabec_code character varying(10) NOT NULL,
  protection_type character varying(255) NOT NULL,
  size character varying(10) NOT NULL,
  sex character varying(10) NOT NULL,
  CONSTRAINT equipment_pkey PRIMARY KEY(id)
);