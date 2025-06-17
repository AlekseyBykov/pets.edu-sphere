CREATE TABLE modules (
    id          UUID        PRIMARY KEY,
    course_id   UUID        NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
    title       TEXT        NOT NULL,
    description TEXT,
    order_index INT         NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP
);
