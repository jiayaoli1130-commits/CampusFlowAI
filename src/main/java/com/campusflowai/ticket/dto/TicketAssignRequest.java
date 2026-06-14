package com.campusflowai.ticket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketAssignRequest {

    @NotNull(message = "处理人 ID 不能为空")
    private Long assigneeId;
}
