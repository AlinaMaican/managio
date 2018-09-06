INSERT INTO users (id, username, first_name, last_name, password, user_role, is_active,email) VALUES
  (nextval('user_seq'), 'admin', 'Admin', 'Admin', '$2a$10$60mEY3ZKWmHVqCThLradq.PYHv5ZEEPPnO1vy2yek0tQ3wwIzc6IS',
   'ADMIN', TRUE,'admin@admin.admin');