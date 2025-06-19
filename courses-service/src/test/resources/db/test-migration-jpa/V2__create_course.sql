CREATE TABLE courses (
    id          UUID        PRIMARY KEY,
    title       TEXT        NOT NULL,
    description TEXT,
    teacher_id  BIGINT,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP
);
