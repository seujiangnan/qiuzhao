package com.jiangnan.qiuzhao.service;

import com.aliyuncs.exceptions.ClientException;
import com.jiangnan.qiuzhao.entity.Collect;
import com.jiangnan.qiuzhao.entity.Company;
import com.jiangnan.qiuzhao.entity.User;

public interface AllService {
    void allAdd(User user, Company company) throws ClientException;
    void allupdate(User user,Company company);
    void alldelete(User user,String cpyId);
}
