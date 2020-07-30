package com.jc.fire.web;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.fire.core.Result;
import com.jc.fire.core.ResultGenerator;
import com.jc.fire.model.User;
import com.jc.fire.model.request.UserRequestDto;
import com.jc.fire.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * <Description> <br>
 * 用户接口
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2019/10/21 14:19
 * @see com.jc.fire.web <br>
 * @since V8.1<br>
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping(value = "/add")
    public Result addUser(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除用户", notes = "根据UserId删除用户")
    @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String")
    @PostMapping("/delete")
    public Result delete(@RequestParam String userId) {
        userService.deleteUserById(userId);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "更新用户", notes = "根据UserId修改用户数据")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        if (user.getUserId() == null) {
            ResultGenerator.genFailResult("userId不可为空");
        }
        userService.updateUserById(user);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * @param
     * @return java.util.List<com.jc.fire.model.User>
     * @throws
     * @Description: 查询数据 目前新增了一个requestDto,后续看具体情况进行优化
     * @author duan.xin
     * @param: map
     */
    @ApiOperation(value = "查询用户", notes = "根据条件查询用户数据")
    @ApiImplicitParam(name = "userRequestDto", value = "查询条件", dataType = "UserRequestDto")
    @PostMapping("/query")
    public List<User> queryUserByCondition(@RequestBody UserRequestDto userRequestDto) {
        int page = userRequestDto.getPageNum();
        int size = userRequestDto.getPageSize();
        PageHelper.startPage(page, size);
        List<User> list = userService.queryUserByCondition(userRequestDto);
        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
        return list;
    }

}
