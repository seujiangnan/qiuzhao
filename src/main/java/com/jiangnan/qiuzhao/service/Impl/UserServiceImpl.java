package com.jiangnan.qiuzhao.service.Impl;

import com.jiangnan.qiuzhao.Dao.UserDao;
import com.jiangnan.qiuzhao.entity.User;
import com.jiangnan.qiuzhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void save(User user) {
        String newpassword = bCryptPasswordEncoder.encode(user.getPassword().trim());
        user.setPassword(newpassword);
        userDao.save(user);
    }

    @Override
    public HashSet<String> findAllPhoneNumber() {
        return userDao.findAllPhoneNumber();
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findALlfriend(String id) {
        return userDao.findAllfriend(id);
    }
}
