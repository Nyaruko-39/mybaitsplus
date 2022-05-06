package com.example.mybaitsplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybaitsplus.mapper.UserMapper;
import com.example.mybaitsplus.pojo.User;
import com.example.mybaitsplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
