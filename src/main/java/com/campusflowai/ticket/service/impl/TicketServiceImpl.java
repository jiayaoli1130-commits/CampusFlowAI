package com.campusflowai.ticket.service.impl;

import com.campusflowai.ticket.dto.TicketCreateRequest;
import com.campusflowai.ticket.entity.Ticket;
import com.campusflowai.ticket.enums.TicketStatus;
import com.campusflowai.ticket.mapper.TicketMapper;
import com.campusflowai.ticket.service.TicketService;
import com.campusflowai.ticket.vo.TicketVO;
import com.campusflowai.user.entity.User;
import com.campusflowai.user.enums.UserRole;
import com.campusflowai.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工单业务服务实现类，负责工单数据转换和持久化操作。
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketMapper ticketMapper;
    private final UserMapper userMapper;

    @Override
    public TicketVO createTicket(TicketCreateRequest request) {
        if (request.getCreatorId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Creator id must not be null");
        }

        User creator = userMapper.selectById(request.getCreatorId());
        if (creator == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Creator not found");
        }

        LocalDateTime now = LocalDateTime.now();

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setCategory(request.getCategory());
        ticket.setPriority(request.getPriority());
        ticket.setCreatorId(request.getCreatorId());
        ticket.setReporterName(request.getReporterName());
        ticket.setReporterEmail(request.getReporterEmail());
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setCreatedAt(now);
        ticket.setUpdatedAt(now);

        ticketMapper.insert(ticket);
        return toVO(ticket);
    }

    @Override
    public TicketVO assignTicket(Long id, Long assigneeId) {
        if (assigneeId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assignee id must not be null");
        }

        Ticket ticket = ticketMapper.selectById(id);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }

        User assignee = userMapper.selectById(assigneeId);
        if (assignee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found");
        }

        UserRole role = assignee.getRole();
        if (role != UserRole.STAFF && role != UserRole.ADMIN) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assignee must be STAFF or ADMIN");
        }

        ticket.setAssigneeId(assigneeId);
        ticket.setStatus(TicketStatus.ASSIGNED);
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketMapper.updateById(ticket);
        return toVO(ticket);
    }

    @Override
    public List<TicketVO> listTickets() {
        return ticketMapper.selectList(null).stream()
                .map(this::toVO)
                .toList();
    }

    @Override
    public TicketVO getTicketById(Long id) {
        Ticket ticket = ticketMapper.selectById(id);
        if (ticket == null) {
            return null;
        }
        return toVO(ticket);
    }

    @Override
    public TicketVO updateTicketStatus(Long id, TicketStatus status) {
        if (status == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket status must not be null");
        }

        Ticket ticket = ticketMapper.selectById(id);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }

        TicketStatus currentStatus = ticket.getStatus();
        if (!isValidStatusTransition(currentStatus, status)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Invalid ticket status transition: " + currentStatus + " -> " + status);
        }

        ticket.setStatus(status);
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketMapper.updateById(ticket);
        return toVO(ticket);
    }

    private boolean isValidStatusTransition(TicketStatus currentStatus, TicketStatus targetStatus) {
        if (currentStatus == null || targetStatus == null || currentStatus == targetStatus) {
            return false;
        }

        return switch (currentStatus) {
            case PENDING -> targetStatus == TicketStatus.ASSIGNED;
            case ASSIGNED -> targetStatus == TicketStatus.PROCESSING;
            case PROCESSING -> targetStatus == TicketStatus.RESOLVED;
            case RESOLVED -> targetStatus == TicketStatus.CLOSED;
            case CLOSED -> false;
        };
    }

    private TicketVO toVO(Ticket ticket) {
        TicketVO ticketVO = new TicketVO();
        ticketVO.setId(ticket.getId());
        ticketVO.setTitle(ticket.getTitle());
        ticketVO.setDescription(ticket.getDescription());
        ticketVO.setCategory(ticket.getCategory());
        ticketVO.setPriority(ticket.getPriority());
        ticketVO.setCreatorId(ticket.getCreatorId());
        ticketVO.setAssigneeId(ticket.getAssigneeId());
        ticketVO.setReporterName(ticket.getReporterName());
        ticketVO.setReporterEmail(ticket.getReporterEmail());
        ticketVO.setStatus(ticket.getStatus());
        ticketVO.setCreatedAt(ticket.getCreatedAt());
        ticketVO.setUpdatedAt(ticket.getUpdatedAt());
        return ticketVO;
    }
}
