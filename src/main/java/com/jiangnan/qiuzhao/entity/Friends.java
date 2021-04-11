package com.jiangnan.qiuzhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor//生成全参数的构造函数
@Accessors(chain = true)
@NoArgsConstructor
public class Friends {
    String id;
    String userOneId;
    String userTwoId;
    String userOneState;
    String userTwoState;
}
