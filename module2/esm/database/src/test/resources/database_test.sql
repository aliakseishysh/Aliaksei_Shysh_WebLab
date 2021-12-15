SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS tags (
    "id" bigserial PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL UNIQUE
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
    "tag_id" BIGINT NOT NULL REFERENCES tags ("id") ON DELETE CASCADE,
    "certificate_id" BIGINT NOT NULL REFERENCES gift_certificates ("id") ON DELETE CASCADE,
    PRIMARY KEY ("tag_id", "certificate_id")
);

INSERT INTO tags("name")
    VALUES ('tag name 1'), ('tag name 2'), ('tag name 3');
INSERT INTO gift_certificates ("name", "description", "price", "duration", "create_date", "last_update_date")
    VALUES ('cert name 1', 'cert desc 1', 1, 1, '2021-01-01T09:10:12.100', '2021-05-02T09:10:12.100');
INSERT INTO tags_gift_certificates ("tag_id", "certificate_id")
    VALUES (1, 1), (2, 1);
