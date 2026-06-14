package com.campusflowai.user.controller;

import com.campusflowai.user.dto.UserCreateRequest;
import com.campusflowai.user.service.UserService;
import com.campusflowai.user.vo.UserVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserVO> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserVO userVO = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userVO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserVO> getUserById(@PathVariable Long id) {
        UserVO userVO = userService.getUserById(id);
        if (userVO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userVO);
    }

    @GetMapping
    public ResponseEntity<List<UserVO>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }
}
