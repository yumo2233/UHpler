package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Pzr
 * @create 2023/1/29 - 21:48
 * @Version 1.0 待修改
 */
@Schema(description = "班级学院表单")
@Data
@EqualsAndHashCode
public class Class_info {
    //班级id
    private Long id;

    //学院
    private int college;

    //专业
    private String professional;

    //年级
    private int grade;
}
