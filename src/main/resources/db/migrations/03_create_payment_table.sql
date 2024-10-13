CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,                 -- Auto-incrementing ID column
    account_id BIGINT NOT NULL,               -- Foreign key to accounts table
    payment_type VARCHAR(50) NOT NULL,        -- Payment type column (CREDIT_CARD or DIRECT_DEBIT)
    amount NUMERIC(15, 2) NOT NULL,           -- Payment amount column
    payment_date DATE NOT NULL,               -- Payment date column
    FOREIGN KEY (account_id) REFERENCES accounts(id) -- Foreign key constraint
);