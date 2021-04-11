package com.jiangnan.qiuzhao.service.Impl;

import com.jiangnan.qiuzhao.entity.Friends;
import com.jiangnan.qiuzhao.service.FriendsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class FrinendsServiceImplTest {
    @Autowired
    private FriendsService friendsService;

    @Test
    void findAllFrinendAsk() {
        Friends byId = friendsService.findById("18");
        System.out.println(byId);
    }

    @Test
    void save() {
        Friends friends = new Friends();
        friends.setUserOneId("2");
        friends.setUserTwoId("3");
        friends.setUserOneState("1");
        friends.setUserTwoState("0");
        friendsService.save(friends);
    }
}