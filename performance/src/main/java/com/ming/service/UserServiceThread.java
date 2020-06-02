package com.ming.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Service
@RequestMapping("/user")
public class UserServiceThread {

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

    public String getAllInfo() throws Exception{
        long start = System.currentTimeMillis();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                String v1 = getUserById(1);
            }
        }).start();*/

        Callable<JSONObject> user1Call = new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                String v1 = getUserById(1);
                JSONObject user1 = JSONObject.parseObject(v1);
                return user1;
            }
        };
        // FutureTask重写了Runnable接口
        FutureTask<JSONObject> user1Task = new FutureTask<JSONObject>(user1Call);
        new Thread(user1Task).start();

        Callable<JSONObject> user2Call = new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                String v2 = getUserById(2);
                JSONObject user2= JSONObject.parseObject(v2);
                return user2;
            }
        };
        // FutureTask重写了Runnable接口
        FutureTask<JSONObject> user2Task = new FutureTask<JSONObject>(user2Call);
        new Thread(user2Task).start();

        // 合并结果
        JSONObject result = new JSONObject();
        result.put("user1",user1Task.get());
        result.put("user2",user2Task.get());

        /*String v1 = getUserById(1);
        JSONObject user1 = JSONObject.parseObject(v1);

        String v2 = getUserById(2);
        JSONObject user2 = JSONObject.parseObject(v2);

        // 合并结果
        JSONObject result = new JSONObject();
        result.put("user1",user1);
        result.put("user2",user2);*/
        System.out.println("执行总时间为： " + (System.currentTimeMillis() - start));
        return result.toJSONString();
    }

}
