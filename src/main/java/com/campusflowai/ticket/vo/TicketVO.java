package com.campusflowai.ticket.vo;

import com.campusflowai.ticket.enums.TicketPriority;
import com.campusflowai.ticket.enums.TicketStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 工单返回对象 VO，用于向前端展示工单详情。
 */
@Data
public class TicketVO {

    /**
     * 工单主键 ID。
     */
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
