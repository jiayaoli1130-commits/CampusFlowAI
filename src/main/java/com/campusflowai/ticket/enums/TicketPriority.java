package com.campusflowai.ticket.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 工单优先级枚举，用于描述工单处理的紧急程度。
 */
@Getter
public enum TicketPriority {

    /**
     * 低优先级。
     */
    LOW("LOW", "低"),

    /**
     * 中优先级。
     */
    MEDIUM("MEDIUM", "中"),

    /**
     * 高优先级。
     */
    HIGH("HIGH", "高"),

    /**
     * 紧急优先级。
     */
    URGENT("URGENT", "紧急");

    @EnumValue
    @JsonValue
    private final String code;

    private final String description;

    TicketPriority(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
