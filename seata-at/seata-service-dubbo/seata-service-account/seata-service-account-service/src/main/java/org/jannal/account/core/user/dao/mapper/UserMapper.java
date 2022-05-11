package org.jannal.account.core.user.dao.mapper;

import org.jannal.account.core.user.entity.User;


public interface UserMapper {

    public int insert(User user);

    User findByUsername(String username);
}
    
