package com.campusflowai.ticket.controller;

import com.campusflowai.ticket.dto.TicketAssignRequest;
import com.campusflowai.ticket.dto.TicketCreateRequest;
import com.campusflowai.ticket.dto.TicketStatusUpdateRequest;
import com.campusflowai.ticket.service.TicketService;
import com.campusflowai.ticket.vo.TicketVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工单接口控制器，提供工单创建和查询的 HTTP API。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    /**
     * 创建一条新工单。
     * {
     *   "title": "宿舍漏水",
     *   "description": "一直漏水",
     *   "category": "报修",
     *   "priority": "HIGH",
     *   "reporterName": "李四",
     *   "reporterEmail": "ljy@qq.com"
     * }
     */
    @PostMapping
    public ResponseEntity<TicketVO> createTicket(@Valid @RequestBody TicketCreateRequest request) {
        TicketVO ticketVO = ticketService.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketVO);

    }

    /**
     * 查询工单列表。
     */
    @GetMapping
    public ResponseEntity<List<TicketVO>> listTickets() {
        return ResponseEntity.ok(ticketService.listTickets());
    }

    /**
     * 根据工单 ID 查询工单详情。
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketVO> getTicketById(@PathVariable Long id) {
        TicketVO ticketVO = ticketService.getTicketById(id);
        if (ticketVO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticketVO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TicketVO> updateTicketStatus(
            @PathVariable Long id,
            @Valid @RequestBody TicketStatusUpdateRequest request) {
        return ResponseEntity.ok(ticketService.updateTicketStatus(id, request.getStatus()));
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<TicketVO> assignTicket(
            @PathVariable Long id,
            @Valid @RequestBody TicketAssignRequest request) {
        return ResponseEntity.ok(ticketService.assignTicket(id, request.getAssigneeId()));
    }
}
