package com.example.mybaitsplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.example.mybaitsplus.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @TableName
    描述：表名注解，标识实体类对应的表
    使用位置:实体类
    更多注解:https://baomidou.com/pages/223848/#tablename
 */
//@TableName("t_user")
//或者在配置文件中配置 global-config.db-config.table-prefix: t_ #配置实体类对应的表的统一前缀
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /*
        MP会将属性为"id"的字段作为主键,进行雪花算法自动赋值
        如果字段名不是"id",比如是uid,MP就不能自动识别
        此时课可在字段上天津@TableId注解,MP就会把注解对应的字段当做主键处理
        @TableId
            描述：主键注解
            使用位置：实体类主键字段
            属性:
                value 表对应字段名
                type: 主键生成策略
                    type = IdType.NONE  默认雪花算法
                    type = IdType.AUTO  数据库ID自增 该类型请确保数据库设置了 ID自增 否则无效
                                                    如果数据库设置了自增,这里不设置type,仍然是雪花算法
        @TableField:
            描述：字段注解
            使用位置：实体类普通字段
            属性:
                value 表对应字段名

        @TableLogic
            描述：表字段逻辑处理注解（逻辑删除）
            使用位置：实体类普通字段
            在表中添加一个字段is_deleted来用来存储一个逻辑数值,实体类中使用@TableLogic标注在字段上。
            0代表未删除，1代表已删除(但没有实际从表中删除,可以做到假删除的效果)
            此时mybatis的查询(自动判断逻辑值)和删除(自动变修改)操作会自动判断是否假删除


     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)//(xxx) xxx是表字段名
    private Long id;
    private String name;
    private Integer age;
    @TableField("user_email")
    private String email;
    @TableLogic
    private boolean isDeleted;
    private SexEnum sex;
}
