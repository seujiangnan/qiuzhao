package com.jiangnan.qiuzhao.service;

import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.Condition;
import com.jiangnan.qiuzhao.service.Impl.CompanyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class CompanyServiceImplTest {
    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    @Test
    void conditionFind() {
        Condition condition = new Condition();
        condition.setName("h");
        condition.setStation("算");
        List<Company> companies = companyServiceImpl.conditionFind(condition);
        System.out.println(companies);
    }
    @Test
    void findAll() {
        List<Company> all = companyServiceImpl.findAll();
        System.out.println(all);
    }
    @Test
    void save() throws ParseException {
        Company company = new Company();
        company.setName("华为");
        company.setStation("软件开发工程师");
        company.setHref("http://career.huawei.com/reccampportal/portal5/index.html");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate=Date.valueOf("2020-8-10");
        company.setStartTime(sqlDate);
        Date sqlDate2=Date.valueOf("2020-9-12");
        company.setEndTime(sqlDate2);
        companyServiceImpl.save(company);
    }
    @Test
    void delete() {
        companyServiceImpl.delete("1");
    }

    @Test
    void findOne() {
        Company company = companyServiceImpl.findOne("7");
        System.out.println(company);
    }

    @Test
    void update() {
        Company one = companyServiceImpl.findOne("26");
        one.setName("字节跳动");
        companyServiceImpl.update(one);
    }
}