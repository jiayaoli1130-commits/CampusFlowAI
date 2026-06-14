CREATE TABLE IF NOT EXISTS ticket (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '工单主键 ID',
    title VARCHAR(100) NOT NULL COMMENT '工单标题',
    description TEXT NOT NULL COMMENT '工单详细描述',
    category VARCHAR(50) NOT NULL COMMENT '工单分类',
    priority VARCHAR(20) NOT NULL COMMENT '工单优先级',
    creator_id BIGINT NULL COMMENT '工单提交人 ID',
    assignee_id BIGINT NULL COMMENT '工单处理人 ID',
    reporter_name VARCHAR(50) NOT NULL COMMENT '提交人姓名',
    reporter_email VARCHAR(100) NOT NULL COMMENT '提交人邮箱',
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '工单状态',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    INDEX idx_ticket_creator_id (creator_id),
    INDEX idx_ticket_assignee_id (assignee_id),
    INDEX idx_ticket_status (status),
    INDEX idx_ticket_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工单表';

CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(30) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);

SET @creator_column_count = (
    SELECT COUNT(*)
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'ticket'
      AND COLUMN_NAME = 'creator_id'
);
SET @sql = IF(
    @creator_column_count = 0,
    'ALTER TABLE ticket ADD COLUMN creator_id BIGINT NULL COMMENT ''工单提交人 ID'' AFTER priority',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @assignee_column_count = (
    SELECT COUNT(*)
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'ticket'
      AND COLUMN_NAME = 'assignee_id'
);
SET @sql = IF(
    @assignee_column_count = 0,
    'ALTER TABLE ticket ADD COLUMN assignee_id BIGINT NULL COMMENT ''工单处理人 ID'' AFTER creator_id',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @creator_index_count = (
    SELECT COUNT(*)
    FROM information_schema.STATISTICS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'ticket'
      AND INDEX_NAME = 'idx_ticket_creator_id'
);
SET @sql = IF(
    @creator_index_count = 0,
    'CREATE INDEX idx_ticket_creator_id ON ticket (creator_id)',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @assignee_index_count = (
    SELECT COUNT(*)
    FROM information_schema.STATISTICS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'ticket'
      AND INDEX_NAME = 'idx_ticket_assignee_id'
);
SET @sql = IF(
    @assignee_index_count = 0,
    'CREATE INDEX idx_ticket_assignee_id ON ticket (assignee_id)',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
