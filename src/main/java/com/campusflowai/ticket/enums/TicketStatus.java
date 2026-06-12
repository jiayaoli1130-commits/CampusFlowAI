package com.campusflowai.ticket.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 工单状态枚举，用于描述工单从创建到完成的处理阶段。
 */
@Getter
public enum TicketStatus {

    /**
     * 待处理，工单刚创建还未开始处理。
     */
    PENDING("PENDING", "待处理"),

    /**
     * 已分配，工单已分配给处理人。
     */
    ASSIGNED("ASSIGNED", "已分配"),

    /**
     * 处理中，工单已被开始处理。
     */
    PROCESSING("PROCESSING", "处理中"),

    /**
     * 已完成，工单问题已解决。
     */
    RESOLVED("RESOLVED", "已完成"),

    /**
     * 已关闭，工单不再继续处理。
     */
    CLOSED("CLOSED", "已关闭");

    @EnumValue
    @JsonValue
    private final String code;

    private final String description;

    TicketStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
