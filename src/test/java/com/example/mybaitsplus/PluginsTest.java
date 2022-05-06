package com.example.mybaitsplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybaitsplus.mapper.ProductMapper;
import com.example.mybaitsplus.mapper.UserMapper;
import com.example.mybaitsplus.pojo.Product;
import com.example.mybaitsplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PluginsTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;
    @Test//测试page插件
    public void test01(){
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page,null); //不传入条件构造器,则查询所有结果,查询的结果封装在page对象中
        System.out.println(page);

        List<User> records = page.getRecords(); //getRecords()获取当前页数据
        records.forEach(System.out::println);

        long current = page.getCurrent(); //获取当前页码
        System.out.println("current = " + current);

        long size = page.getSize();//获取当前页数据条数
        System.out.println("size = " + size);

        long pages = page.getPages();//获取总页数
        System.out.println("pages = " + pages);

        long total = page.getTotal();//获取总数据条数
        System.out.println("total = " + total);

        boolean b = page.hasPrevious();//是否有上一页
        System.out.println("b = " + b);

        boolean b1 = page.hasNext();//是否有下一页
        System.out.println("b1 = " + b1);
    }
    @Test//测试自己的sql语句使用page插件
    public void test02(){
        Page<User> page = new Page<>(1,3);
        userMapper.selectByAge(page, 20);//不传入条件构造器,则查询所有结果,查询的结果封装在page对象中
        System.out.println(page);

        List<User> records = page.getRecords(); //getRecords()获取当前页数据
        records.forEach(System.out::println);

        long current = page.getCurrent(); //获取当前页码
        System.out.println("current = " + current);

        long size = page.getSize();//获取当前页数据条数
        System.out.println("size = " + size);

        long pages = page.getPages();//获取总页数
        System.out.println("pages = " + pages);

        long total = page.getTotal();//获取总数据条数
        System.out.println("total = " + total);

        boolean b = page.hasPrevious();//是否有上一页
        System.out.println("b = " + b);

        boolean b1 = page.hasNext();//是否有下一页
        System.out.println("b1 = " + b1);
    }

    @Test
    public void test03(){
        Product productLi = productMapper.selectById("1");
        System.out.println("小李查询 = " + productLi);
        Product productWang = productMapper.selectById("1");
        System.out.println("小王查询 = " + productWang);

        //小李修改
        productLi.setPrice(productLi.getPrice()+50);
       productMapper.updateById(productLi);

        //小王修改
        productWang.setPrice(productWang.getPrice()-30);
        int result = productMapper.updateById(productWang);

        //优化
        if (result==0){
            productWang = productMapper.selectById(1);
            productWang.setPrice(productWang.getPrice()-30);
            productMapper.updateById(productWang);
        }

        //老板查询
        Product productBoss = productMapper.selectById("1");
        System.out.println("老板查询 = " + productBoss);
    }
}
