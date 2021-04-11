package com.jiangnan.qiuzhao.Dao;

import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.Condition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyDao{
    List<Company> findAll();
    void save(Company company);
    void delete(String id);
    Company findOne(String id);
    void update(Company emp);
    List<Company> ConditionFind(Condition condition);
}
