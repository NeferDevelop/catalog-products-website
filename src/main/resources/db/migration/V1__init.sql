CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price double precision,
    order_number INTEGER,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

