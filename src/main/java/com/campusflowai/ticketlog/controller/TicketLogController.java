package com.campusflowai.ticketlog.controller;

import com.campusflowai.ticketlog.service.TicketLogService;
import com.campusflowai.ticketlog.vo.TicketLogVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets/{ticketId}/logs")
public class TicketLogController {

    private final TicketLogService ticketLogService;

    @GetMapping
    public ResponseEntity<List<TicketLogVO>> listByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketLogService.listByTicketId(ticketId));
    }
}
