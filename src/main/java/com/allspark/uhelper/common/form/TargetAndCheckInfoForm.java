package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName TargetAndCheckInfoForm
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/24 17:39
 * @Version 1.0
 **/
@Data
@Schema(description = "对应课程的课程目标下对应的单个考核方式的表单")
public class TargetAndCheckInfoForm {

    @Schema(description = "考核id")
    private Long checkId;

    @Schema(description = "考核名称")
    private String checkName;

    @Schema(description = "占同种考核方式的权重")
    private BigDecimal targetRatio;

    @Schema(description = "测试次数")
    private Integer targetCount;
}
