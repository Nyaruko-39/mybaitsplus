package com.example.mybaitsplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybaitsplus.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {
    List<User> selectByMyself(@Param("id") Integer id);

    //自己的sql语句想要使用page分页插件,返回值和第一个参数一定要是Page对象
    Page<User> selectByAge(@Param("page")Page<User> page,@Param("age")Integer age);
}
