package com.jiangnan.qiuzhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor//生成全参数的构造函数
@Accessors(chain = true)
@NoArgsConstructor
public class UserFriend {
    String userId;//当前好友的userId
    String friendId;//两个好友之间的id 进入聊天室的唯一标示
    String username;
}
