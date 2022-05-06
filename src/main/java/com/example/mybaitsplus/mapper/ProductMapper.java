package com.example.mybaitsplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybaitsplus.pojo.Product;
import com.example.mybaitsplus.pojo.User;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper extends BaseMapper<Product> {
}
