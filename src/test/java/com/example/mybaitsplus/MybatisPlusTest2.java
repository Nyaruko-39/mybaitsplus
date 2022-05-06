package com.example.mybaitsplus;

import com.example.mybaitsplus.enums.SexEnum;
import com.example.mybaitsplus.pojo.User;
import com.example.mybaitsplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class MybatisPlusTest2 {
    @Autowired
    private UserService userService;

    /*
        当表名和实体类名相同时自动映射
        当不同时使用注解@TableName
     */
    @Test
    public void testCount(){
        //该方法查询总记录数
        long count = userService.count();
        System.out.println("count = " + count);
    }

    //测试批量添加
    @Test
    public void testBatchInsert(){
        User user1 = new User(null,"miku",39,"miku@39.com",false, SexEnum.FEMALE);
        User user2 = new User(null,"Luka",39,"Luka@39.com",false,SexEnum.FEMALE);
        List<User> userList = new ArrayList<>();
        Collections.addAll(userList,user1,user2);
        boolean b = userService.saveBatch(userList);
        System.out.println("操作是否成功 = " + b);//MP自动生成的id是Long类型(通过雪花算法生成)
    }



}
