package com.campusflowai.ticket.service;

import com.campusflowai.ticket.dto.TicketCreateRequest;
import com.campusflowai.ticket.enums.TicketStatus;
import com.campusflowai.ticket.vo.TicketVO;

import java.util.List;

/**
 * 工单业务服务接口，定义工单模块对外提供的业务能力。
 */
public interface TicketService {

    /**
     * 创建一条新工单。
     *
     * @param request 创建工单请求
     * @return 创建后的工单信息
     */
    TicketVO createTicket(TicketCreateRequest request);

    /**
     * 查询工单列表。
     *
     * @return 工单列表
     */
    List<TicketVO> listTickets();

    /**
     * 根据工单 ID 查询工单详情。
     *
     * @param id 工单 ID
     * @return 工单详情；不存在时返回 null
     */
    TicketVO getTicketById(Long id);

    TicketVO updateTicketStatus(Long id, TicketStatus status);

    TicketVO assignTicket(Long id, Long assigneeId);
}
