package com.campusflowai.ticketlog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.campusflowai.ticket.enums.TicketStatus;
import com.campusflowai.ticketlog.enums.TicketLogAction;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("ticket_log")
public class TicketLog {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long ticketId;

    private Long operatorId;

    private TicketLogAction action;

    private TicketStatus fromStatus;

    private TicketStatus toStatus;

    private String content;

    private LocalDateTime createdAt;
}
