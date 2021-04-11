package com.jiangnan.qiuzhao.service.Impl;

import com.jiangnan.qiuzhao.Dao.CollectDao;
import com.jiangnan.qiuzhao.Dao.CompanyDao;
import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.Condition;
import com.jiangnan.qiuzhao.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private CollectDao collectDao;
    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }
    @Override
    public void delete(String id) {
        companyDao.delete(id);
    }
    @Override
    public Company findOne(String id) {
        return companyDao.findOne(id);
    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }

    @Override
    public List<Company> conditionFind(Condition condition) {
        return companyDao.ConditionFind(condition);
    }
}
