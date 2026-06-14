package com.campusflowai.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserRole {

    STUDENT("STUDENT", "学生"),
    STAFF("STAFF", "处理人员"),
    ADMIN("ADMIN", "管理员");

    @EnumValue
    @JsonValue
    private final String code;

    private final String description;

    UserRole(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
