package com.jc.fire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jc.fire.core.Mapper;
import com.jc.fire.model.User;
import com.jc.fire.model.request.UserRequestDto;

public interface UserMapper extends Mapper<User> {

    /**
     * @Description: 添加用户
     * @author duan.xin
     * @param
     * @param: user
     * @return void
     * @throws
     */
    void saveUser(@Param("user") User user);

    /**
     * @Description: 查询用户
     * @author duan.xin
     * @param
     * @return
     * @throws
     */
    List<User> queryUserByCondition(UserRequestDto user);

    /**
     * @Description: 删除用户
     * @author duan.xin
     * @param
     * @return
     * @throws
     */
    void deleteUserById(@Param("userId") String userId);


    /**
     * @Description: 更新用户
     * @author duan.xin
     * @param
     * @return
     * @throws
     */
    void updateUserById(User user);


}