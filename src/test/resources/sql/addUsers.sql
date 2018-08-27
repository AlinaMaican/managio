-- password asd using BCrypt
insert into users (id, username, first_name, last_name, password, user_role, is_active)
values
  (-1, 'activeUser', 'firstName', 'lastName', '$2a$10$b2Xhtf5oqTsFNh.qmoDyxuVHxaWDUhTJjSelS4DZEAXmRWrOTmRh2', 'USER',
   true);
insert into users (id, username, first_name, last_name, password, user_role, is_active)
values
  (-2, 'inactiveUser', 'inactiveUser', 'lastName', '$2a$10$b2Xhtf5oqTsFNh.qmoDyxuVHxaWDUhTJjSelS4DZEAXmRWrOTmRh2',
   'USER', false)
