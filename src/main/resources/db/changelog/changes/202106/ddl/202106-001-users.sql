CREATE TABLE users
(
    id       BIGSERIAL NOT NULL CONSTRAINT users_pk PRIMARY KEY,
    login    VARCHAR(50),
    password VARCHAR(500)
);

COMMENT ON TABLE users IS 'Таблица пользователей';

COMMENT ON COLUMN users.id IS 'Уникальный идентификатор записи';
COMMENT ON COLUMN users.login IS 'Логин';
COMMENT ON COLUMN users.password IS 'Пароль';

CREATE INDEX users_login_idx ON users (login, password);