SELECT 'CREATE DATABASE esm' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'esm')\gexec
\c 'esm'

CREATE TABLE IF NOT EXISTS tags (
    "id" bigserial PRIMARY KEY,
    "name" VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS gift_certificates (
    "id" bigserial PRIMARY KEY,
    "name" VARCHAR(255),
    "description" VARCHAR(255),
    "price" DECIMAL,
    "duration" SMALLINT,
    "create_date" TIMESTAMP,
    "last_update_date" TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tags_gift_certificates (
    "tag_id" BIGINT NOT NULL REFERENCES tags ("id"),
    "certificate_id" BIGINT NOT NULL REFERENCES gift_certificates ("id")
);