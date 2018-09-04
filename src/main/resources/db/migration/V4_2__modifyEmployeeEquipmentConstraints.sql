ALTER TABLE employees_equipments(
  DROP CONSTRAINT employee_equipment_fkey1,
  DROP CONSTRAINT employee_equipment_fkey2,
  CONSTRAINT employee_equipment_fkey1 FOREIGN KEY (employee_id)
    REFERENCES employees (id),
  CONSTRAINT employee_equipment_fkey2 FOREIGN KEY (equipment_id)
    REFERENCES equipments (id)
  );