CREATE TABLE IF NOT EXISTS c_user(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS message(
    id SERIAL PRIMARY KEY,
    message_date TIMESTAMP NOT NULL,
    message_text VARCHAR(255) NOT NULL,
    message_from_id INTEGER NOT NULL REFERENCES c_user(id),
    message_to_id INTEGER NOT NULL REFERENCES c_user(id)
);