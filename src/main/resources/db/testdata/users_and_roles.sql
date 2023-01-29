insert into
    users (first_name, last_name, email, role)
values
    ('userTest', 'userTest','user@example.com', 'USER');  -- 1


insert into
    auth_users (password, movie_id, user_id)
values
    ('{noop}authuser', null, 1);  -- 1


--    insert into
--        users (email, password, role)
--    values
--        ('admin@example.com', '{noop}admin', 'ADMIN'),   -- 1
--        ('user@example.com', '{noop}user', 'USER');     -- 2

--insert into
--    user_role (name, description)
--values
--    ('ADMIN', 'pełne uprawnienia'),   -- 1
--    ('USER', 'podstawowe uprawnienia');   -- 2
--
--insert into
--    user_roles (user_id, role_id)
--values
--    (1, 1),
--    (2, 2);