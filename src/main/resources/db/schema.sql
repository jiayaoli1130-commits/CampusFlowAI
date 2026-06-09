CREATE TABLE IF NOT EXISTS ticket (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '工单主键 ID',
    title VARCHAR(100) NOT NULL COMMENT '工单标题',
    description TEXT NOT NULL COMMENT '工单详细描述',
    reporter_name VARCHAR(50) NOT NULL COMMENT '提交人姓名',
    reporter_email VARCHAR(100) NOT NULL COMMENT '提交人邮箱',
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '工单状态',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (id),
    INDEX idx_ticket_status (status),
    INDEX idx_ticket_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工单表';
