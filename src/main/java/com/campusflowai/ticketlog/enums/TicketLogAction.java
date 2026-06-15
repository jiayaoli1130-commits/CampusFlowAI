package com.campusflowai.ticketlog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TicketLogAction {

    CREATE("CREATE"),
    ASSIGN("ASSIGN"),
    STATUS_CHANGE("STATUS_CHANGE");

    @EnumValue
    @JsonValue
    private final String code;

    TicketLogAction(String code) {
        this.code = code;
    }
}
