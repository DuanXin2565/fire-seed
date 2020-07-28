package com.jc.fire.dao;

import java.util.List;

import com.jc.fire.core.Mapper;
import com.jc.fire.model.User;

public interface UserMapper extends Mapper<User> {

    void saveUser(User user);

    List<User> queryUserByCondition(User user);
}