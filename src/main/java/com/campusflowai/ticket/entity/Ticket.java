package com.campusflowai.ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.campusflowai.ticket.enums.TicketPriority;
import com.campusflowai.ticket.enums.TicketStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 工单实体类，对应数据库中的 ticket 表。
 */
@Data
@TableName("ticket")
public class Ticket {

    /**
     * 工单主键 ID。
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单标题。
     */
    private String title;

    /**
     * 工单详细描述。
     */
    private String description;

    /**
     * 工单分类。
     */
    private String category;

    /**
     * 工单优先级。
     */
    private TicketPriority priority;

    private Long creatorId;

    private Long assigneeId;

    /**
     * 提交人姓名。
     */
    private String reporterName;

    /**
     * 提交人邮箱。
     */
    private String reporterEmail;

    /**
     * 工单当前状态。
     */
    private TicketStatus status;

    /**
     * 创建时间。
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间。
     */
    private LocalDateTime updatedAt;
}
