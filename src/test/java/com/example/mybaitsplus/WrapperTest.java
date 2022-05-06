package com.example.mybaitsplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.mybaitsplus.mapper.UserMapper;
import com.example.mybaitsplus.pojo.User;
import com.example.mybaitsplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){//通过queryWrapper设置查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("id",2,5).like("name","a").isNotNull("user_email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test02(){//通过queryWrapper设置升降排序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");//年龄降序,相同时id升序;
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test03(){//删除也使用的是queryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("id",4);//lt <  gt >    le <=   ge >=
        int result = userMapper.delete(queryWrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void test04(){//使用queryWrapper实现修改 将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20).like("name","a").or()
                .like("user_email","sl");
        User user = new User();
        user.setName("小明");
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);
    }

    @Test
    public void test05(){//使用queryWrapper实现修改 将年龄大于20并且(用户名中包含有a或邮箱为null)的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20).and(i->i.like("name","小").or()
                .like("user_email","sl"));
        User user = new User();
        user.setName("小红");
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);
    }

    @Test
    public void test06(){ //使用queryWrapper组装要查询的字段
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07(){
        //UpdateWrapper 可以同时设置要修改的条件和字段  queryWrapper实现修改还需要一个设置属性的实体类对象
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("name","笑死");
        userUpdateWrapper.le("id",3);
        int result = userMapper.update(null, userUpdateWrapper);
        System.out.println("result = " + result);
    }

    @Test //使用condition属性来拼接sql条件
    public void test08(){
        String name = "a";
        Integer ageBegin = 50;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(name),"name","a").ge(ageBegin!=null,"age",ageBegin);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test //测试LambdaQueryWrapper
    public void test09(){
        String name = "a";
        Integer ageBegin = 50;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name),User::getName,name);//通过lambda的方法引用可以防止字段写错
        lambdaQueryWrapper.ge(ageBegin!=null,User::getAge,ageBegin);

        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test//测试LambdaUpdateWrapper
    public void test10(){
        //UpdateWrapper 可以同时设置要修改的条件和字段  queryWrapper实现修改还需要一个设置属性的实体类对象
        LambdaUpdateWrapper<User> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.set(User::getName,"笑活");
        userUpdateWrapper.le(User::getId,3);
        int result = userMapper.update(null, userUpdateWrapper);
        System.out.println("result = " + result);

    }
}
