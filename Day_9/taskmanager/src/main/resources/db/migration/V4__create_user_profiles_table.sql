-- V4: Create user_profiles table (One-to-One with users)

CREATE TABLE user_profiles (
    id         BIGINT       AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT       NOT NULL UNIQUE,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    bio        VARCHAR(1000),
    avatar_url VARCHAR(255),
    phone      VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
