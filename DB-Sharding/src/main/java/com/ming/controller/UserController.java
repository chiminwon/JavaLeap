package com.ming.controller;

import com.ming.model.User;
import com.ming.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理相关接口")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    @ApiOperation("获取所有用户接口")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getUserById/{id}")
    @ApiOperation("根据id查找用户接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1", required = true)
    public User getUserById(@RequestParam(value = "id", required = true) Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/addUser")
    @ApiOperation("增加用户接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", required = true),
//            @ApiImplicitParam(name = "address", value = "用户地址", required = true)
//    })
    public Integer addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping(value = "/updateUserById")
    @ApiOperation("根据用户id更新用户接口")
    //@ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1", required = true)
    public Integer updateUserById(@RequestBody User user) {
        return userService.updateUserById(user);
    }
}
