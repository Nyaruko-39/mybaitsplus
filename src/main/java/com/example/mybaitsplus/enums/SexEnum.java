package com.example.mybaitsplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
/*
    在MybatisPlus使用枚举类填入的步骤
    1.配置枚举类包扫描路径
    2.在枚举类属性上标注@EnumValue
 */
@Getter
public enum SexEnum {
    MALE(1,"男"),
    FEMALE(2,"女");

    @EnumValue //注解标注的字段会填入数据库
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
