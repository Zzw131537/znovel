package com.zhouzw.znovel;

import com.zhouzw.znovel.core.constant.ApiRouterConsts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZnovelApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(ApiRouterConsts.API_FRONT_USER_URL_PREFIX);
    }

    @Test
    void test2() {
        System.out.println("你好");
    }
}
