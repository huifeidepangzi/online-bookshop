CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,                -- Auto-incrementing ID column
    name VARCHAR(255) NOT NULL,              -- Name column (not null)
    email VARCHAR(100) NOT NULL UNIQUE,      -- Email column (not null and unique, with max length 100)
    age INTEGER                              -- Age column (nullable)
);