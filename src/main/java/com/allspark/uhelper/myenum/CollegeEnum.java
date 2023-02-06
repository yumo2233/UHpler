package com.allspark.uhelper.myenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import org.junit.Test;

@Getter
public enum CollegeEnum {
    /**
    * @author 86159
    * @Description 学院的枚举类
    * @Date 18:36 2023/1/12
    * @Param
    * @return
    **/

    TONGGONG(1, "communAndInfo"),
    DIANGONG(2, "electronAndEng"),
    ZIDONGHUA(3, "autoMation"),
    JISUANJI(4, "computing"),
    JINGGUAN(5, "economyAndMag");

    CollegeEnum(int i, String s) {
        code = i;
        name = s;
    }

    public static String getEnumType(int val) {
        for (CollegeEnum type : CollegeEnum.values()) {
            if (type.getCode() == val) {
                return type.getName();
            }
        }
        return null;
    }


    @EnumValue
    private final Integer code;
    @JsonValue
    private final String name;

}

