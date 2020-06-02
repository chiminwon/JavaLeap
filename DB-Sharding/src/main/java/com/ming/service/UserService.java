package com.ming.service;

import com.ming.anotation.Master;
import com.ming.anotation.Slave;
import com.ming.mapper.UserMapper;
import com.ming.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 查询的时候需要提供一个 RowMapper，就是需要自己手动映射，将数据库中的字段和对象的属性一一对应起来
    @Slave
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Slave
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Master
    public Integer addUser(User user) {
        Integer code = userMapper.addUser(user);
        return user.getId();
    }

    @Master
    public Integer updateUserById(User user) {
        return userMapper.updateUserById(user);
    }
}
