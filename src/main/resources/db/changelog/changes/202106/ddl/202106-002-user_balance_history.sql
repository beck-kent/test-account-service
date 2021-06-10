CREATE TABLE user_balance_history
(
    id BIGSERIAL CONSTRAINT user_balance_history_pk PRIMARY KEY,
    user_id           BIGINT NOT NULL,
    operation_type    VARCHAR(15) NOT NULL,
    amount            NUMERIC     NOT NULL,
    currency          VARCHAR(3)  NOT NULL,
    created_at        TIMESTAMP WITH TIME ZONE NOT NULL
);

COMMENT ON TABLE user_balance_history IS 'Таблица user_balance_history лог операций над балансом пользователя';

COMMENT ON COLUMN user_balance_history.id IS 'Уникальный идентификатор записи';
COMMENT ON COLUMN user_balance_history.user_id IS 'ID пользователя';
COMMENT ON COLUMN user_balance_history.operation_type IS 'Тип операции';
COMMENT ON COLUMN user_balance_history.amount IS 'Сумма операции';
COMMENT ON COLUMN user_balance_history.currency IS 'Валюта платежа';
COMMENT ON COLUMN user_balance_history.created_at IS 'Дата и время записи';

CREATE INDEX user_balance_history_created_idx ON user_balance_history (created_at);