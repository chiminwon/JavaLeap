package com.ming.controller;

import com.ming.model.User;
import com.ming.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    public String getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getUserById/{id}")
    @ApiOperation("根据id查找用户接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1", required = true)
    public String getUserById(@PathVariable(value = "id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/getAllInfo")
    public String getAllInfo() {
        return userService.getAllInfo();
    }
}
