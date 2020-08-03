package com.jc.fire.service;

import java.util.List;

import com.jc.fire.core.Service;
import com.jc.fire.model.User;
import com.jc.fire.model.request.UserRequestDto;


/**
 * <Description> <br>
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/7/30 9:37
 * @see com.jc.fire.service <br>
 * @since V8.1<br>
 */
public interface UserService extends Service<User> {

    /**
     * @Description: 新增用户
     * @author duan.xin
     * @param
     * @param: user
     * @return void
     * @throws
     */
    void saveUser(User user);


    /**
     * @Description: 查询用户
     * @author duan.xin
     * @param
     * @param: user
     * @return java.util.List<User>
     * @throws
     */
    List<User> queryUserByCondition(UserRequestDto user);

    /**
     * @Description: 更新用户
     * @author duan.xin
     * @param
     * @return
     * @throws
     */
    void updateUserById(User user);


    /**
     * @Description: 删除用户
     * @author duan.xin
     * @param
     * @return
     * @throws
     */
    void deleteUserById(String userId);
}
