-- V1: Create users table
-- Flyway runs this ONCE and records it in flyway_schema_history

CREATE TABLE users (
    id         BIGINT       AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(20)  NOT NULL DEFAULT 'USER',
    active     BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Insert a default admin user for testing
-- NOTE: In Day 15 (Spring Security) this password will be BCrypt encoded
INSERT INTO users (username, email, password, role)
VALUES ('admin', 'admin@taskmanager.com', 'admin1234', 'ADMIN');
