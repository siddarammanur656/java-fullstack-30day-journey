-- V3: Create tags and task_tags join table

CREATE TABLE tags (
    id    BIGINT      AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(50) NOT NULL UNIQUE,
    color VARCHAR(7)
);

CREATE TABLE task_tags (
    task_id BIGINT NOT NULL,
    tag_id  BIGINT NOT NULL,
    PRIMARY KEY (task_id, tag_id),
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id)  REFERENCES tags(id)  ON DELETE CASCADE
);

-- Seed default tags
INSERT INTO tags (name, color) VALUES
    ('urgent',  '#FF0000'),
    ('bug',     '#FF5733'),
    ('feature', '#33FF57'),
    ('review',  '#3357FF'),
    ('blocked', '#FF33F5');
