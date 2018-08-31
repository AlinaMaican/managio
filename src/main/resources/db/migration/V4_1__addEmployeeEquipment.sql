CREATE SEQUENCE employee_equipment_seq START 1;

CREATE TABLE employees_equipments(
  id BIGINT NOT NULL DEFAULT nextval('employee_equipment_seq'),
  employee_id BIGINT NOT NULL,
  equipment_id BIGINT NOT NULL,
  UNIQUE (employee_id, equipment_id),
  CONSTRAINT employee_equipment_pkey PRIMARY KEY(id),
  CONSTRAINT employee_equipment_fkey1 FOREIGN KEY (employee_id)
    REFERENCES employees (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT employee_equipment_fkey2 FOREIGN KEY (equipment_id)
    REFERENCES equipments (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
  );