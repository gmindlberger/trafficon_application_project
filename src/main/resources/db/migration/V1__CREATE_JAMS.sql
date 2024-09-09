CREATE SCHEMA IF NOT EXISTS application;
CREATE TYPE SEVERITY AS ENUM ('LOW', 'MEDIUM', 'HIGH');

CREATE TABLE jam
(
    id          BIGSERIAL PRIMARY KEY    NOT NULL,
    location    VARCHAR(255)             NOT NULL UNIQUE,
    name        VARCHAR(255),
    description TEXT,
    severity    SEVERITY,
    district    VARCHAR(255)             NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE
);
