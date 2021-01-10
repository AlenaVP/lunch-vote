DROP TABLE IF EXISTS lunch_dishes;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS lunches;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR(125)                     NOT NULL,
    email      VARCHAR(125)                     NOT NULL,
    password   VARCHAR(125)                     NOT NULL,
    registered TIMESTAMP          DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id BIGINT       NOT NULL,
    role    VARCHAR(125) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR(125) NOT NULL
);
CREATE UNIQUE INDEX restaurants_name_idx ON restaurants (name);

CREATE TABLE lunches
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id INTEGER                          NOT NULL,
    date          DATE               DEFAULT now() NOT NULL,
    CONSTRAINT lunches_restaurant_id_date UNIQUE (restaurant_id, date),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX lunches_date_restaurant_idx ON lunches (date, restaurant_id);

CREATE TABLE dishes
(
    id    BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    name  VARCHAR(255) NOT NULL,
    price INTEGER      NOT NULL,
    CONSTRAINT dishes_name_price_idx UNIQUE (name, price)
);
CREATE INDEX dishes_name_idx ON dishes (name);

CREATE TABLE votes
(
    user_id  INTEGER            NOT NULL,
    lunch_id INTEGER            NOT NULL,
    date     DATE DEFAULT now() NOT NULL,
    CONSTRAINT votes_user_lunch_idx UNIQUE (user_id, lunch_id),
    CONSTRAINT votes_user_date_idx UNIQUE (user_id, date),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (lunch_id) REFERENCES lunches (id) ON DELETE CASCADE
);

CREATE TABLE lunch_dishes
(
    lunch_id INTEGER NOT NULL,
    dish_id  INTEGER NOT NULL,
    CONSTRAINT lunch_dishes_idx UNIQUE (lunch_id, dish_id),
    FOREIGN KEY (lunch_id) REFERENCES lunches (id) ON DELETE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES dishes (id) ON DELETE CASCADE
);