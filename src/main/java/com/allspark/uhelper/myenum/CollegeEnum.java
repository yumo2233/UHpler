package com.allspark.uhelper.myenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum CollegeEnum {
    /**
    * @author 86159
    * @Description 学院的枚举类
    * @Date 18:36 2023/1/12
    * @Param
    * @return
    **/

    TONGGONG(1,"通信工程学院"),
    DIANGONG(2,"电子工程学院"),
    ZIDONGHUA(3,"自动化学院"),
    JISUANJI(4,"计算机学院"),
    JINGGUAN(5,"经济与管理学院");

    @JsonCreator
    CollegeEnum(int i, String s) {
        code=i;
        name=s;
    }
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String name;
}
