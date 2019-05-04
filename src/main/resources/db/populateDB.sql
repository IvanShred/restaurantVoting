DELETE
FROM user_roles;
DELETE
FROM vote;
DELETE
FROM meals;
DELETE
FROM restaurants;
DELETE
FROM users;
ALTER SEQUENCE global_seq
  RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO restaurants (name, address)
VALUES ('Арарат', 'ул. Ноябрьская, 58/1'),
       ('Забой', 'ул. Институтская, 2');

INSERT INTO meals (date_meal, description, restaurant_id, price)
VALUES (CURRENT_DATE, 'Кофе', 100002, 60),
       (CURRENT_DATE, 'Салат', 100002, 80),
       (CURRENT_DATE, 'Борщ', 100002, 100),
       (CURRENT_DATE, 'Паста', 100002, 120),
       (CURRENT_DATE, 'Чай', 100003, 50),
       (CURRENT_DATE, 'Амлет', 100003, 70),
       (CURRENT_DATE, 'Щи', 100003, 90),
       (CURRENT_DATE, 'Плов', 100003, 150);

INSERT INTO vote (date_vote, user_id, restaurant_id)
VALUES (CURRENT_DATE, 100000, 100002)/*,
       (CURRENT_DATE, 100001, 100003)*/;