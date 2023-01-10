insert into
    users (email, password)
values
    ('admin@example.com', '{noop}admin'),   -- 1
    ('user@example.com', '{noop}user');     -- 2

insert into
    user_role (name, description)
values
    ('ADMIN', 'pełne uprawnienia'),   -- 1
    ('USER', 'podstawowe uprawnienia');   -- 2

insert into
    user_roles (user_id, role_id)
values
    (1, 1),
    (2, 2);