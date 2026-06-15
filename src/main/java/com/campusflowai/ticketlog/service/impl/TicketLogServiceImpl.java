package com.campusflowai.ticketlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.campusflowai.ticket.enums.TicketStatus;
import com.campusflowai.ticketlog.entity.TicketLog;
import com.campusflowai.ticketlog.enums.TicketLogAction;
import com.campusflowai.ticketlog.mapper.TicketLogMapper;
import com.campusflowai.ticketlog.service.TicketLogService;
import com.campusflowai.ticketlog.vo.TicketLogVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketLogServiceImpl implements TicketLogService {

    private final TicketLogMapper ticketLogMapper;

    @Override
    public void createLog(
            Long ticketId,
            Long operatorId,
            TicketLogAction action,
            TicketStatus fromStatus,
            TicketStatus toStatus,
            String content) {
        TicketLog ticketLog = new TicketLog();
        ticketLog.setTicketId(ticketId);
        ticketLog.setOperatorId(operatorId);
        ticketLog.setAction(action);
        ticketLog.setFromStatus(fromStatus);
        ticketLog.setToStatus(toStatus);
        ticketLog.setContent(content);
        ticketLog.setCreatedAt(LocalDateTime.now());
        ticketLogMapper.insert(ticketLog);
    }

    @Override
    public List<TicketLogVO> listByTicketId(Long ticketId) {
        LambdaQueryWrapper<TicketLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TicketLog::getTicketId, ticketId)
                .orderByAsc(TicketLog::getCreatedAt);

        return ticketLogMapper.selectList(queryWrapper).stream()
                .map(this::toVO)
                .toList();
    }

    private TicketLogVO toVO(TicketLog ticketLog) {
        TicketLogVO ticketLogVO = new TicketLogVO();
        ticketLogVO.setId(ticketLog.getId());
        ticketLogVO.setTicketId(ticketLog.getTicketId());
        ticketLogVO.setOperatorId(ticketLog.getOperatorId());
        ticketLogVO.setAction(ticketLog.getAction());
        ticketLogVO.setFromStatus(ticketLog.getFromStatus());
        ticketLogVO.setToStatus(ticketLog.getToStatus());
        ticketLogVO.setContent(ticketLog.getContent());
        ticketLogVO.setCreatedAt(ticketLog.getCreatedAt());
        return ticketLogVO;
    }
}
