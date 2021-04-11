package com.jiangnan.qiuzhao.Dao;

import com.jiangnan.qiuzhao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Mapper
@Repository
public interface UserDao{
    User findByUsername(String username);
    void save(User user);
    HashSet<String> findAllPhoneNumber();
    User findById(String id);
    List<User> findAllfriend(String id);
}
