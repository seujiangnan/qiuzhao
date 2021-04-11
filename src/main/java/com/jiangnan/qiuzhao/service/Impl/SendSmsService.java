package com.jiangnan.qiuzhao.service.Impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SendSmsService {
    @Value("${access.keyId}")
    private String accessKeyId;

    @Value("${access.keySecret}")
    private String accessSecret;

    public Map<String,String> sendcheckCode(String phoneNumber, String checkCode) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "一起求职");
        request.putQueryParameter("TemplateCode", "SMS_195723002");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+checkCode+"\"}");
        CommonResponse response = client.getCommonResponse(request);
        Map parse = (Map) JSONUtils.parse(response.getData());
        System.out.println(response.getData());
        return parse;
    }
    public void sendMessage(String phoneNumber, String checkCode) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "一起求职");
        request.putQueryParameter("TemplateCode", "SMS_195723002");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+checkCode+"\"}");
        CommonResponse response = client.getCommonResponse(request);
        Map parse = (Map) JSONUtils.parse(response.getData());
        System.out.println(response.getData());
    }

}
