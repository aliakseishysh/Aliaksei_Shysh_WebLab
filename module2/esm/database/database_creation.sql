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
    "create_date" DATE,
    "last_update_date" DATE
);

CREATE TABLE IF NOT EXISTS tags_gift_certificates (
    "tag_id" BIGINT NOT NULL REFERENCES tags ("id"),
    "certificate_id" BIGINT NOT NULL REFERENCES gift_certificates ("id")
);