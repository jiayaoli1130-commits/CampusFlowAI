package com.campusflowai.user.service.impl;

import com.campusflowai.user.dto.UserCreateRequest;
import com.campusflowai.user.entity.User;
import com.campusflowai.user.mapper.UserMapper;
import com.campusflowai.user.service.UserService;
import com.campusflowai.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserVO createUser(UserCreateRequest request) {
        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        userMapper.insert(user);
        return toVO(user);
    }

    @Override
    public UserVO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return null;
        }
        return toVO(user);
    }

    @Override
    public List<UserVO> listUsers() {
        return userMapper.selectList(null).stream()
                .map(this::toVO)
                .toList();
    }

    private UserVO toVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setEmail(user.getEmail());
        userVO.setRole(user.getRole());
        userVO.setCreatedAt(user.getCreatedAt());
        userVO.setUpdatedAt(user.getUpdatedAt());
        return userVO;
    }
}
