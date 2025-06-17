CREATE TABLE profiles (
    id           UUID        PRIMARY KEY,
    username     TEXT        NOT NULL UNIQUE,
    email        TEXT        NOT NULL UNIQUE,
    phone        TEXT,
    created_at   TIMESTAMP   NOT NULL DEFAULT now(),
    created_by   TEXT,
    updated_at   TIMESTAMP,
    updated_by   TEXT
);
