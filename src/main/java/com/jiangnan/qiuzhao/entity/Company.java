package com.jiangnan.qiuzhao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;


//id int(6) primary key auto_increment,
//        name varchar(40),
//        station varchar(40),
//        href varchar(40),
//        startTime datetime,
//        endTime datetime
@Data
@AllArgsConstructor//生成全参数的构造函数
@Accessors(chain = true)
@NoArgsConstructor
public class Company implements Serializable {
    public String id;
    public String name;
    public String station;
    public String href;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    public Date startTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    public Date endTime;
}
