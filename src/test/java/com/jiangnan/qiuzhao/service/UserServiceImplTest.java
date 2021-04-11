package com.jiangnan.qiuzhao.service;

import com.jiangnan.qiuzhao.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    void findByUsername() {
        System.out.println(userService.findByUsername("admin"));
    }
    @Test
    void save() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setPhoneNumber("15667021563");
        userService.save(user);
    }
    @Test
    void findAllPhoneNumber() {

        System.out.println(userService.findAllPhoneNumber());
    }
}