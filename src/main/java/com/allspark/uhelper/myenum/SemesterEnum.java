package com.allspark.uhelper.myenum;

import com.allspark.uhelper.utils.SemesterUtil;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public enum SemesterEnum {

    /**
    * @author 86159
    * @Description 学期的枚举类
    * @Date 3:44 2023/1/17
    * @Param
    * @return
    **/

    ONE(1,SemesterUtil.select()[0]),
    TWO(2,SemesterUtil.select()[1]),
    THREE(3,SemesterUtil.select()[2]),
    FOUR(4,SemesterUtil.select()[3]),
    FIVE(5,SemesterUtil.select()[4]),
    SIX(6,SemesterUtil.select()[5]),
    SEVEN(7,SemesterUtil.select()[6]),
    EIGHT(8,SemesterUtil.select()[7]);


    SemesterEnum(int i, String s) {
        code=i;
        name=s;
    }
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String name;
}
