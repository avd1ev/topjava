DELETE FROM meals;
DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (description, calories, user_id, date_time)
VALUES ('breakfast', 900, 100000, '2023-02-22 08:00:00'),
       ('dinner', 1400, 100000, '2023-02-22 12:00:00'),
       ('vechir', 800, 100000, '2023-02-22 18:00:00');