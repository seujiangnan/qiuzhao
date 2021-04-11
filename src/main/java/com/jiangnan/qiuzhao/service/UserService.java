package com.jiangnan.qiuzhao.service;

import com.jiangnan.qiuzhao.entity.User;

import java.util.HashSet;
import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void save(User user);
    HashSet<String> findAllPhoneNumber();
    User findById(String id);
    List<User> findALlfriend(String id);
}
