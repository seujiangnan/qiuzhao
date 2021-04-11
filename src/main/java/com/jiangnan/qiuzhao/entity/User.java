package com.jiangnan.qiuzhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//    id int(6) primary key auto_increment,
//            username varchar(60),
//            password varchar(4),
//            phoneNumber int(12)

@Data
@AllArgsConstructor//生成全参数的构造函数
@Accessors(chain = true)
@NoArgsConstructor
public class User implements Serializable{
    public String id;
    public String username;
    public String password;
    public String phoneNumber;
}
