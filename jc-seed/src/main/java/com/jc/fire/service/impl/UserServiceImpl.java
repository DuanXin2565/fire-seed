package com.jc.fire.service.impl;

import java.util.List;

import com.jc.fire.dao.UserMapper;
import com.jc.fire.model.User;
import com.jc.fire.service.UserService;
import com.jc.fire.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/07/27.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public List<User> queryUserByCondition(User user) {
        return userMapper.queryUserByCondition(user);
    }


}
