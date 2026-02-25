CREATE TABLE users (
    id TEXT PRIMARY KEY,
    role TEXT NOT NULL,
    name TEXT,
    email TEXT,
    password TEXT,
    fixed_income NUMERIC(15,2),
    creation_date TIMESTAMP
);