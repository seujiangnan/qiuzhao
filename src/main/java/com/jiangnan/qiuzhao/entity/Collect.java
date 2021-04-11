package com.jiangnan.qiuzhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//userId int(6)
//cpyId int(6)
@Data
@AllArgsConstructor//生成全参数的构造函数
@Accessors(chain = true)
@NoArgsConstructor
public class Collect implements Serializable{
    public String userId;
    public String cpyId;
}
