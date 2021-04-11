package com.jiangnan.qiuzhao.service.Impl;

import com.jiangnan.qiuzhao.Dao.FriendsDao;
import com.jiangnan.qiuzhao.entity.Friends;
import com.jiangnan.qiuzhao.service.FriendsService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FrinendsServiceImpl implements FriendsService {
    @Autowired
    private FriendsDao friendsDao;
    @Override
    public List<Friends> findAllFrinendAsk(String userTwoId) {
        return friendsDao.findAllFrinendAsk(userTwoId);
    }

    @Override
    public void save(Friends friends) {
        friendsDao.save(friends);

    }

    @Override
    public void update(Friends friends) {
        friendsDao.update(friends);
    }

    @Override
    public void delete(String id) {
        friendsDao.delete(id);
    }

    @Override
    public String findFriendId(String userOneId, String userTwoId) {
        if(friendsDao.findFriendId(userOneId,userTwoId) != null){
            return friendsDao.findFriendId(userOneId,userTwoId);
        }else{
            return friendsDao.findFriendId(userTwoId,userOneId);
        }
    }

    @Override
    public Friends findById(String id) {
        return friendsDao.findById(id);
    }
}
