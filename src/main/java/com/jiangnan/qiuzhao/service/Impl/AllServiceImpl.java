package com.jiangnan.qiuzhao.service.Impl;

import com.aliyuncs.exceptions.ClientException;
import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.User;
import com.jiangnan.qiuzhao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional
public class AllServiceImpl implements AllService {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private UserService userService;
    @Autowired
    private SendSmsService sendSmsService;
    @Override
    public void allAdd(User user, Company company)  {
        companyService.save(company);
        String cpyId = company.getId();
        Collect collect = new Collect();
        collect.setUserId(user.getId());
        collect.setCpyId(cpyId);
        collectService.save(collect);
        HashSet<String> allPhoneNumber = userService.findAllPhoneNumber();
        allPhoneNumber.remove(user.getPhoneNumber());
        try{
            for(String phoneNumber:allPhoneNumber){
                sendSmsService.sendMessage(phoneNumber,"add"+company.getName());
            }
        }
        catch (ClientException e) {
                e.printStackTrace();
            }

        }
    @Override
    public void allupdate(User user, Company company) {
        companyService.update(company);
        HashSet<String> userphoneNumberByCpyId = collectService.findUserphoneNumberByCpyId(company.getId());
        String phone = user.getPhoneNumber();
        userphoneNumberByCpyId.remove(phone);
        try{
            for(String phoneNumber:userphoneNumberByCpyId){
                sendSmsService.sendMessage(phoneNumber,"update"+company.getName());
            }
        }
        catch (ClientException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void alldelete(User user, String cpyId) {
        String userId = user.getId();
        HashSet<String> userphoneNumberByCpyId = collectService.findUserphoneNumberByCpyId(cpyId);
        collectService.deleteAll(cpyId);
        Company one = companyService.findOne(cpyId);
        String cpyName = one.getName();
        companyService.delete(cpyId);
        String phone = user.getPhoneNumber();
        userphoneNumberByCpyId.remove(phone);
        try{
            for(String phoneNumber:userphoneNumberByCpyId){
                sendSmsService.sendMessage(phoneNumber,"delete"+cpyName);
            }
        }
        catch (ClientException e) {
            e.printStackTrace();
        }


    }

}
