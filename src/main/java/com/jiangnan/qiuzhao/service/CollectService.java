package com.jiangnan.qiuzhao.service;

import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.User;

import java.util.HashSet;
import java.util.List;

public interface CollectService {
    void save(Collect collect);
    void delete(String userId,String cpyId);
    void deleteAll(String cpyId);
    List<Company> findByUserId(String userId);
    HashSet<String> findUserphoneNumberByCpyId(String cpyId);
    List<String> findCpyIDByUserId(String userId);
}
