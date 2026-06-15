package com.campusflowai.ticketlog.vo;

import com.campusflowai.ticket.enums.TicketStatus;
import com.campusflowai.ticketlog.enums.TicketLogAction;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketLogVO {

    private Long id;

    private Long ticketId;

    private Long operatorId;

    private TicketLogAction action;

    private TicketStatus fromStatus;

    private TicketStatus toStatus;

    private String content;

    private LocalDateTime createdAt;
}
