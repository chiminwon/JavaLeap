package com.ming.mapper;

import com.ming.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUsers();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "u"),
            @Result(property = "address", column = "a")
    })
    @Select("select username as u, address as a, id as id from user where id = #{id}")
    User getUserById(Integer id);

    @Select("select * from user where username like concat('%',#{name},'%')")
    List<User> getUsersByName(String name);

    @SelectKey(
            statement = "select LAST_INSERT_ID()",
            keyProperty = "id",
            before = false,
            resultType = Integer.class)
    @Options(keyColumn = "id", useGeneratedKeys = true)
    @Insert({"insert into user(username, address) values (#{username},#{address})"})
    Integer addUser(User user);

    @Update("update user set username=#{username},address=#{address} where id=#{id}")
    Integer updateUserById(User user);

    @Delete("delete from user where id=#{id}")
    Integer deleteUserById(Integer id);
}
