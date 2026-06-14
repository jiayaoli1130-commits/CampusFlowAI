package com.campusflowai.user.vo;

import com.campusflowai.user.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {

    private Long id;

    private String username;

    private String email;

    private UserRole role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
