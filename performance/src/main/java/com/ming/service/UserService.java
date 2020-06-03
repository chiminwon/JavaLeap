package com.ming.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Service
@RequestMapping("/user")
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public String getAllUsers() {
        final String baseUrl = "http://127.0.0.1:" + 8080 + "/user/getAllUsers";
        String result = restTemplate.getForObject(baseUrl, String.class);
        System.out.println(result);
        return result;
    }

    public String getUserById(Integer id) {
        String baseUrl = "http://localhost:" + 8080 + "/user/getUserById/" + id;
        String result = restTemplate.getForObject(baseUrl, String.class);
        return result;
    }

    public String getAllInfo() {
        long start = System.currentTimeMillis();

        String v1 = this.getUserById(1);
        JSONObject user1 = JSONObject.parseObject(v1);

        String v2 = this.getUserById(2);
        JSONObject user2 = JSONObject.parseObject(v2);

        // 合并结果
        JSONObject result = new JSONObject();
        result.put("user1",user1);
        result.put("user2",user2);
        System.out.println("执行总时间为： " + (System.currentTimeMillis() - start));
        return result.toJSONString();
    }

}
