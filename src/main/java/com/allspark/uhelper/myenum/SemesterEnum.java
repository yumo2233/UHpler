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

    ONE(SemesterUtil.selectInt()[0],SemesterUtil.select()[0]),
    TWO(SemesterUtil.selectInt()[1],SemesterUtil.select()[1]),
    THREE(SemesterUtil.selectInt()[2],SemesterUtil.select()[2]),
    FOUR(SemesterUtil.selectInt()[3],SemesterUtil.select()[3]),
    FIVE(SemesterUtil.selectInt()[4],SemesterUtil.select()[4]),
    SIX(SemesterUtil.selectInt()[5],SemesterUtil.select()[5]),
    SEVEN(SemesterUtil.selectInt()[6],SemesterUtil.select()[6]),
    EIGHT(SemesterUtil.selectInt()[7],SemesterUtil.select()[7]);


    SemesterEnum(int i, String s) {
        code=i;
        name=s;
    }
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String name;
}
