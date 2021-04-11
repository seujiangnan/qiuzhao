package com.jiangnan.qiuzhao.service;

import com.jiangnan.qiuzhao.entity.Friends;

import java.util.List;

public interface FriendsService {
    List<Friends> findAllFrinendAsk(String userTwoId);
    void save(Friends friends);
    void update(Friends friends);
    void delete(String id);
    String findFriendId(String userOneId,String userTwoId);
    Friends findById(String id);
}
