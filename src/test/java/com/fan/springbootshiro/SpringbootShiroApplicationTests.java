package com.fan.springbootshiro;

import com.fan.springbootshiro.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.soap.Addressing;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Autowired
    UserDao userDao;
    @Test
    void contextLoads() {
        System.out.println(userDao.queryByName("fan"));
    }

}
