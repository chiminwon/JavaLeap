package com.ming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ming.mapper")
public class MySQLApp {

    public static void main(String[] args) {
        SpringApplication.run(MySQLApp.class, args);
    }

}
