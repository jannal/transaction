package org.jannal.account.core.user.service.impl;


import org.jannal.account.core.user.dao.mapper.UserMapper;
import org.jannal.account.core.user.entity.User;
import org.jannal.account.core.user.service.UserService;
import org.jannal.common.exception.ValidateParamsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        User userExist = userMapper.findByUsername(user.getUsername());
        if (userExist != null) {
            throw ValidateParamsException.valueOfParamsIllegal("用户名已注册");
        }
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userMapper.insert(user);
    }


}
