-- Create DB and use
# CREATE DATABASE db_example;
USE db_example;

-- Drop tables
DROP TABLE if exists user_roles;
DROP TABLE if exists roles;
DROP TABLE if exists users;

-- Table: users
CREATE TABLE users
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(100) NOT NULL
)
    ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
)
    ENGINE = InnoDB;

-- Insert data
INSERT INTO users
VALUES (1, 'user', '$2a$10$.lka8D/UXBTiGWgxw5c1Suxz4Uk4Uiq2XwHdYZYSjpZKFq5sZ0LBK', 'user@mail.ru');

INSERT INTO users
VALUES (2, 'test', '$2a$10$f7.b2lGEhhuEjNo4r2I3C..c4UCwCbsQXAlt11qIG71qR0IYgI81i', 'test@test.ru');

INSERT INTO users
VALUES (3, 'ADMIN', '$2a$10$AkloRPs5K9S/kPyaMcpOBeKD17Tuf1QXjK4j6a1Da01vk/9dz6yYq', 'admin@ru');

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles
VALUES (1, 2);
INSERT INTO user_roles
VALUES (2, 1);
INSERT INTO user_roles
VALUES (3, 2);
