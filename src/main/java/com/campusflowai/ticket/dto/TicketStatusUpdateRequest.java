package com.campusflowai.ticket.dto;

import com.campusflowai.ticket.enums.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketStatusUpdateRequest {

    @NotNull(message = "status must not be null")
    private TicketStatus status;
}
