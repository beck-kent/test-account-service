CREATE TABLE user_balance
(
    id       BIGSERIAL NOT NULL CONSTRAINT user_balance_pk PRIMARY KEY,
    user_id  BIGINT NOT NULL,
    balance_usd NUMERIC NOT NULL DEFAULT 0,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE
);

COMMENT ON TABLE user_balance IS 'Текущий баланс пользователя';

COMMENT ON COLUMN user_balance.id IS 'Уникальный идентификатор записи';
COMMENT ON COLUMN user_balance.user_id IS 'ID пользователя';
COMMENT ON COLUMN user_balance.balance_usd IS 'Баланс в долларах';
COMMENT ON COLUMN user_balance.updated_at IS 'Дата и время обновления';

CREATE INDEX user_balance_user_idx ON user_balance (user_id);