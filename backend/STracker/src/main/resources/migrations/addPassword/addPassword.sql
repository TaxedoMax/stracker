CREATE TABLE user_password (
       login VARCHAR(255) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL
);