package com.campusflowai.ticket.dto;

import com.campusflowai.ticket.enums.TicketPriority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 创建工单请求 DTO，用于接收前端提交的新工单信息。
 */
@Data
public class TicketCreateRequest {

    /**
     * 工单标题。
     */
    @NotBlank(message = "工单标题不能为空")
    @Size(max = 100, message = "工单标题不能超过 100 个字符")
    private String title;

    /**
     * 工单详细描述。
     */
    @NotBlank(message = "工单描述不能为空")
    @Size(max = 2000, message = "工单描述不能超过 2000 个字符")
    private String description;

    /**
     * 工单分类。
     */
    @NotBlank(message = "工单分类不能为空")
    @Size(max = 50, message = "工单分类不能超过 50 个字符")
    private String category;

    /**
     * 工单优先级。
     */
    @NotNull(message = "工单优先级不能为空")
    private TicketPriority priority;

    @NotNull(message = "提交人 ID 不能为空")
    private Long creatorId;

    /**
     * 提交人姓名。
     */
    @NotBlank(message = "提交人姓名不能为空")
    @Size(max = 50, message = "提交人姓名不能超过 50 个字符")
    private String reporterName;

    /**
     * 提交人邮箱。
     */
    @NotBlank(message = "提交人邮箱不能为空")
    @Email(message = "提交人邮箱格式不正确")
    @Size(max = 100, message = "提交人邮箱不能超过 100 个字符")
    private String reporterEmail;
}
