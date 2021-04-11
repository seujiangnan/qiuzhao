package com.jiangnan.qiuzhao.Dao;

import com.jiangnan.qiuzhao.entity.Friends;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendsDao {
    List<Friends> findAllFrinendAsk(String userTwoId);
    Friends findById(String id);
    void save(Friends friends);
    void update(Friends friends);
    void delete(String id);
    String findFriendId(String userOneId,String userTwoId);
}
