ALTER TABLE employees_equipments
  DROP CONSTRAINT employee_equipment_fkey1;

ALTER TABLE employees_equipments
  ADD CONSTRAINT employee_equipment_fkey1 FOREIGN KEY (employee_id) REFERENCES employees (id);

ALTER TABLE employees_equipments
  DROP CONSTRAINT employee_equipment_fkey2;

ALTER TABLE employees_equipments
  ADD CONSTRAINT employee_equipment_fkey2 FOREIGN KEY (equipment_id) REFERENCES equipments (id);