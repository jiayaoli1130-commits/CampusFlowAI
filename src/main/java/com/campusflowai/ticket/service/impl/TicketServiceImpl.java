package com.campusflowai.ticket.service.impl;

import com.campusflowai.ticket.dto.TicketCreateRequest;
import com.campusflowai.ticket.entity.Ticket;
import com.campusflowai.ticket.enums.TicketStatus;
import com.campusflowai.ticket.mapper.TicketMapper;
import com.campusflowai.ticket.service.TicketService;
import com.campusflowai.ticket.vo.TicketVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 工单业务服务实现类，负责工单数据转换和持久化操作。
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketMapper ticketMapper;

    @Override
    public TicketVO createTicket(TicketCreateRequest request) {
        LocalDateTime now = LocalDateTime.now();

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setReporterName(request.getReporterName());
        ticket.setReporterEmail(request.getReporterEmail());
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setCreatedAt(now);
        ticket.setUpdatedAt(now);

        ticketMapper.insert(ticket);
        return toVO(ticket);
    }

    @Override
    public TicketVO getTicketById(Long id) {
        Ticket ticket = ticketMapper.selectById(id);
        if (ticket == null) {
            return null;
        }
        return toVO(ticket);
    }

    private TicketVO toVO(Ticket ticket) {
        TicketVO ticketVO = new TicketVO();
        ticketVO.setId(ticket.getId());
        ticketVO.setTitle(ticket.getTitle());
        ticketVO.setDescription(ticket.getDescription());
        ticketVO.setReporterName(ticket.getReporterName());
        ticketVO.setReporterEmail(ticket.getReporterEmail());
        ticketVO.setStatus(ticket.getStatus());
        ticketVO.setCreatedAt(ticket.getCreatedAt());
        ticketVO.setUpdatedAt(ticket.getUpdatedAt());
        return ticketVO;
    }
}
