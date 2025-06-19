CREATE TABLE lessons (
    id          UUID        PRIMARY KEY,
    module_id   UUID        NOT NULL REFERENCES modules(id) ON DELETE CASCADE,
    title       TEXT        NOT NULL,
    content     TEXT,
    order_index INT         NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP
);
