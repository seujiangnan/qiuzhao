package com.jiangnan.qiuzhao.Dao;

import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Mapper
@Repository
public interface CollectDao {
    void save(Collect collect);
    void delete(String userId,String cpyId);
    void deleteAll(String cpyId);
    List<Company> findByUserId(String userId);
    HashSet<String> findUserphoneNumberByCpyId(String cpyId);
}
