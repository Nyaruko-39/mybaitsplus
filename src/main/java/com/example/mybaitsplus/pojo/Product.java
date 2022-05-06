package com.example.mybaitsplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version //标注乐观锁版本字段
    private Integer version;
}
