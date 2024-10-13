CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,                 -- Auto-incrementing ID column
    user_id BIGINT NOT NULL,                  -- Foreign key to users table
    account_number VARCHAR(255),              -- Account number column
    FOREIGN KEY (user_id) REFERENCES users(id) -- Foreign key constraint
);