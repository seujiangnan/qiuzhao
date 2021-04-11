package com.jiangnan.qiuzhao.service;

import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class CollecrServiceImplTest {
    @Autowired
    private CollectService collectService;
    @Test
    void save() {
        Collect collect = new Collect();
        collect.setUserId("1");
        collect.setCpyId("25");
        collectService.save(collect);
    }

    @Test
    void delete() {
        collectService.delete("1","2");

    }
    @Test
    void findByUserId(){
        List<String> cpyIDByUserId = collectService.findCpyIDByUserId("1");
        System.out.println(cpyIDByUserId);
    }
    @Test
    void findUserphoneNumberByCpyId(){
        HashSet<String> userphoneNumberByCpyId = collectService.findUserphoneNumberByCpyId("36");
        System.out.println(userphoneNumberByCpyId);
    }
}