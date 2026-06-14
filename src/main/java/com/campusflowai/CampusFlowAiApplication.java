package com.campusflowai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CampusFlow AI 后端应用启动入口。
 */
@MapperScan({"com.campusflowai.ticket.mapper", "com.campusflowai.user.mapper"})
@SpringBootApplication
public class CampusFlowAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusFlowAiApplication.class, args);
    }
}
