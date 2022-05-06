package com.example.mybaitsplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MybaitsplusApplicationTests {

    @Transactional//在junit5中使用此注解标注的方法,如果对数据库进行操作会自动回滚
    @Test
    void contextLoads() {
    }

}
