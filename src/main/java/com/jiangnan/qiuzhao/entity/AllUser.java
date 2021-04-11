package com.jiangnan.qiuzhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor//生成全参数的构造函数
@Accessors(chain = true)
@NoArgsConstructor
public class AllUser {
    public String id;
    public String username;
    public String phoneNumber;
    List<UserFriend> friendsAskList;
}
