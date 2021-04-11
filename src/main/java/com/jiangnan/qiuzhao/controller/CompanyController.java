package com.jiangnan.qiuzhao.controller;

import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.Condition;
import com.jiangnan.qiuzhao.entity.User;
import com.jiangnan.qiuzhao.service.AllService;
import com.jiangnan.qiuzhao.service.CollectService;
import com.jiangnan.qiuzhao.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("company")
@CrossOrigin
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private AllService allService;
    @GetMapping("findOne")
    private Company findOne(String id){
        return companyService.findOne(id);
    }
    @PostMapping("update")
    private Map<String,Object> update(@RequestBody Company company,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        try {
            allService.allupdate(user,company);
            map.put("state",true);
            map.put("msg","修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","修改失败");
        }
        return map;
    }
    @GetMapping("delete")
    private Map<String,Object> delete(String id,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        try {
            allService.alldelete(user,id);
            map.put("state",false);
            map.put("msg","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","删除失败");
        }
        System.out.println(map);
        return map;
    }
    @PostMapping("save")
    public Map<String,Object> save(@RequestBody Company company, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        try {
            allService.allAdd(user,company);
            map.put("state",true);
            map.put("msg","添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","添加失败");
        }
        return map;
    }
    @GetMapping("findAll")
    private List<Company> findAll(){
        return companyService.findAll();
    }
    @PostMapping("conditionFind")
    private List<Company> conditionFind(@RequestBody Condition condition){
        System.out.println("condition"+condition);
        return companyService.conditionFind(condition);
    }
}
