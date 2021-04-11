package com.jiangnan.qiuzhao.controller;

import com.jiangnan.qiuzhao.entity.AllUser;
import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.User;
import com.jiangnan.qiuzhao.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("collect")
@CrossOrigin
public class CollectController {
    @Autowired
    private CollectService collectService;
    @GetMapping("save")
    private Map<String,Object> save(String cpyId,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        Collect collect = new Collect();
        collect.setUserId(user.getId());
        collect.setCpyId(cpyId);
        try {
            collectService.save(collect);
            map.put("state",true);
            map.put("msg","收藏成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","收藏失败");
        }
        System.out.println(map);
        return map;
    }
    @GetMapping("findAll")
    private List<Company> findAll(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return collectService.findByUserId(user.getId());

    }
    @GetMapping("findAllCpyId")
    private List<String> findAllCpyId(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return collectService.findCpyIDByUserId(user.getId());
    }
    @GetMapping("delete")
    private Map<String,Object> delete(String cpyId,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        try {
            collectService.delete(user.getId(),cpyId);
            map.put("state",true);
            map.put("msg","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","删除失败");
        }
        System.out.println(map);
        return map;
    }
}
