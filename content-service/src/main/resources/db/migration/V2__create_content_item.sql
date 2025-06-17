CREATE TABLE content_items (
    id              UUID        PRIMARY KEY,
    filename        TEXT        NOT NULL,
    content_type    TEXT        NOT NULL,
    size            BIGINT      NOT NULL,
    s3key           TEXT        NOT NULL,
    created_at      TIMESTAMP   NOT NULL DEFAULT now()
);
