package com.jc.fire.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.fire.core.AbstractService;
import com.jc.fire.dao.UserMapper;
import com.jc.fire.model.User;
import com.jc.fire.model.request.UserRequestDto;
import com.jc.fire.service.UserService;


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
        user.setStateDate(new Date());
        userMapper.saveUser(user);
    }

    @Override
    public List<User> queryUserByCondition(UserRequestDto user) {
        return userMapper.queryUserByCondition(user);
    }

    @Override
    public void updateUserById(User user) {
        user.setStateDate(new Date());
        userMapper.updateUserById(user);
    }

    @Override
    public void deleteUserById(String userId) {
        userMapper.deleteUserById(userId);
    }


}
