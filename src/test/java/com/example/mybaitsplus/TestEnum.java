package com.example.mybaitsplus;

import com.example.mybaitsplus.enums.SexEnum;
import com.example.mybaitsplus.mapper.UserMapper;
import com.example.mybaitsplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEnum {
    @Autowired
    UserMapper userMapper;
    @Test
    public void test01(){
        userMapper.insert(new User(null, "Nyaruko", 10000, null, false, SexEnum.FEMALE));
    }
}
