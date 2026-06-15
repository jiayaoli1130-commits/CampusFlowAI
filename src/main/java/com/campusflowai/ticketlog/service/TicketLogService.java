package com.campusflowai.ticketlog.service;

import com.campusflowai.ticket.enums.TicketStatus;
import com.campusflowai.ticketlog.enums.TicketLogAction;
import com.campusflowai.ticketlog.vo.TicketLogVO;

import java.util.List;

public interface TicketLogService {

    void createLog(
            Long ticketId,
            Long operatorId,
            TicketLogAction action,
            TicketStatus fromStatus,
            TicketStatus toStatus,
            String content);

    List<TicketLogVO> listByTicketId(Long ticketId);
}
