package com.jc.fire.service;
import java.util.List;

import com.jc.fire.model.User;
import com.jc.fire.core.Service;


/**
 * Created by CodeGenerator on 2020/07/27.
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
    List<User> queryUserByCondition(User user);
}
