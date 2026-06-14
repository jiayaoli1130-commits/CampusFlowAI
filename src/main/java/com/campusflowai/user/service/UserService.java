package com.campusflowai.user.service;

import com.campusflowai.user.dto.UserCreateRequest;
import com.campusflowai.user.vo.UserVO;

import java.util.List;

public interface UserService {

    UserVO createUser(UserCreateRequest request);

    UserVO getUserById(Long id);

    List<UserVO> listUsers();
}
