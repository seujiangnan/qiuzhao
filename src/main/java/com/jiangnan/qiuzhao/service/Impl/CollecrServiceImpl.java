package com.jiangnan.qiuzhao.service.Impl;

import com.jiangnan.qiuzhao.Dao.CollectDao;
import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CollecrServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;
    @Override
    public void save(Collect collect) {
        collectDao.save(collect);
    }
    @Override
    public void delete(String userId, String cpyId) {
        collectDao.delete(userId,cpyId);
    }

    @Override
    public void deleteAll(String cpyId) {
        collectDao.deleteAll(cpyId);
    }

    @Override
    public List<Company> findByUserId(String userId) {
        List<Company> byUserId = collectDao.findByUserId(userId);
        return byUserId;
    }

    @Override
    public HashSet<String> findUserphoneNumberByCpyId(String cpyId) {
        return collectDao.findUserphoneNumberByCpyId(cpyId);
    }

    @Override
    public List<String> findCpyIDByUserId(String userId) {
        List<Company> byUserId = collectDao.findByUserId(userId);
        List<String> collect = byUserId.stream().map(company -> company.getId()).collect(Collectors.toList());
        return collect;
    }
}
