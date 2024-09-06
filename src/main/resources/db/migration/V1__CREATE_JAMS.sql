CREATE SCHEMA IF NOT EXISTS application;
CREATE TYPE SEVERITY AS ENUM ('LOW', 'MEDIUM', 'HIGH');

CREATE TABLE jam
(
    id          BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    location    VARCHAR(255) NOT NULL UNIQUE,
    name        VARCHAR(255),
    description TEXT,
    active      BOOLEAN               NOT NULL DEFAULT TRUE,
    severity    SEVERITY,
    start_time  TIMESTAMP,
    end_time    TIMESTAMP,

    created_at  TIMESTAMP             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP             NOT NULL DEFAULT CURRENT_TIMESTAMP
);