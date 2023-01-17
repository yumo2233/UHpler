package com.allspark.uhelper.myenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SelectEnum {
    /**
     * @ClassName SelectEnum
     * @Description 是否选修必修的枚举类
     * @Author 86159
     * @Date 2023/1/14 20:08
     * @Version 1.0
     **/

    BIXIU(1,"专业必修"),
    XUANXIU(2,"专业选修");

    SelectEnum(int i, String s) {
        code=i;
        name=s;
    }
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String name;

}
