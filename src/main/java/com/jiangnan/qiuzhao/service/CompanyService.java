package com.jiangnan.qiuzhao.service;

import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.Condition;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    void save(Company company);
    void delete(String id);
    Company findOne(String id);
    void update(Company company);
    List<Company> conditionFind(Condition condition);
}
