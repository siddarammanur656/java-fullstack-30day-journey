-- V2: Create tasks table (depends on users table from V1)
-- NOTE: H2 does not support inline INDEX in CREATE TABLE.
--       Indexes are created separately below.

CREATE TABLE tasks (
    id          BIGINT        AUTO_INCREMENT PRIMARY KEY,
    task_title  VARCHAR(100)  NOT NULL,
    description VARCHAR(1000),
    status      VARCHAR(20)   NOT NULL DEFAULT 'TODO',
    priority    INT           NOT NULL DEFAULT 3,
    user_id     BIGINT        NOT NULL,
    created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    due_date    TIMESTAMP,

    CONSTRAINT fk_task_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- Separate CREATE INDEX statements (H2 compatible)
CREATE INDEX idx_task_user_id  ON tasks(user_id);
CREATE INDEX idx_task_status   ON tasks(status);
CREATE INDEX idx_task_priority ON tasks(priority);
