-- password asd using BCrypt
insert into users (id, username, first_name, last_name, password, user_role, is_active, email)
values
  (-1, 'activeUser', 'firstName', 'lastName', '$2a$10$b2Xhtf5oqTsFNh.qmoDyxuVHxaWDUhTJjSelS4DZEAXmRWrOTmRh2', 'USER',
   true,'activeUser@users');
insert into users (id, username, first_name, last_name, password, user_role, is_active, email)
values
  (-2, 'inactiveUser', 'inactiveUser', 'lastName', '$2a$10$b2Xhtf5oqTsFNh.qmoDyxuVHxaWDUhTJjSelS4DZEAXmRWrOTmRh2',
   'USER', false, 'inactiveUser@users')
