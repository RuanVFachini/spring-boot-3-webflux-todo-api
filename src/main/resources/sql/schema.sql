CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     username TEXT NOT NULL,
                                     hash TEXT NOT NULL,
                                     CONSTRAINT unique_user UNIQUE (username)
    );

DROP TABLE IF EXISTS todos;

CREATE TABLE todos (
                       id BIGSERIAL PRIMARY KEY,
                       description TEXT NOT NULL,
                       completed BOOLEAN NOT NULL DEFAULT false,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       completed_at TIMESTAMP
);