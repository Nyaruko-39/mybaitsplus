package com.example.mybaitsplus;

import com.example.mybaitsplus.mapper.UserMapper;
import com.example.mybaitsplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.Name;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusTest {
    @Autowired //在MybaitsplusApplication里的@MapperScan里已扫描过了
    UserMapper userMapper;

    //测试模板的查询功能
    @Test
    public void testSelectForList(){
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    //测试模板的添加功能
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("Aya");
        user.setAge(1000);
        user.setEmail("fsl@th.com");

        int result = userMapper.insert(user);
        System.out.println("result = " + result);
        //当没有设置主键值时,MP自动生成的id是Long类型(通过雪花算法生成)
        System.out.println("id =" + user.getId());
    }

    //测试模板删除 deleteById
    @Test
    public void testDelete1(){
        int result = userMapper.deleteById(1513187445948162050L);
        System.out.println("result = " + result);
    }

    //测试模板删除 deleteByMap
    @Test
    public void testDelete2(){
        //将条件存入map,根据map中设置的条件删除
        Map<String,Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age",28);
        int result = userMapper.deleteByMap(map);
        System.out.println("result = " + result);
    }

    //测试模板删除 deleteBatchIds
    @Test
    public void testDelete3(){
        //将一个list存入
        List<Long> longs = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(longs);
        System.out.println("result = " + result);
    }

    //测试模板更新 updateById updateById
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(4L);
        user.setName("Aya");
        user.setAge(1000);
        user.setEmail("fsl@th.com");

        int result = userMapper.updateById(user);
        System.out.println("result = " + result);
    }

    //测试查询
    @Test
    public void testSelect(){
        //通过id查询用户信息
        User user = userMapper.selectById(4);
        System.out.println("user = " + user);
        System.out.println("=========================");
        //通过多个id查询一个结果集合
        List<Integer> list = Arrays.asList(3, 4, 5);
        List<User> users = userMapper.selectBatchIds(list);
        for (User perUser : users) {
            System.out.println("perUser = " + perUser);
        }
        System.out.println("=========================");
        //通过条件map查询一个结果
        Map<String,Object> map =new HashMap<>();
        map.put("id",4);
        map.put("name","Aya");  //SELECT id,name,age,email FROM user WHERE name = ? AND id = ?
        List<User> users1 = userMapper.selectByMap(map);
        users1.forEach(System.out::println);

        //查询所有数据
        List<User> users2 = userMapper.selectList(null);
        users2.forEach(System.out::println);
    }

    //自定义查询功能
    @Test
    public void testSelectByMyself(){
        List<User> users = userMapper.selectByMyself(2);
        users.forEach(System.out::println);
    }

    @Test
    public void batchInsert(){
//        userMapper.insert()
        /*
            BaseMapper里没有批量添加
            因为在数据量大时会导致sql语句过长
            见service
         */
    }
}
