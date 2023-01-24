package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName TargetAndCheckForm
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/24 17:21
 * @Version 1.0
 **/
@Data
@Schema(description = "单个课程目标和考核方式的关系表单")
public class TargetAndCheckForm {

    @Schema(description = "课程目标的id")
    private Long targetId;

    @Schema(description = "课程目标名称")
    private String targetName;

    @Schema(description = "该课程目标下的每一种考核方式对应的信息的列表")
    private List<TargetAndCheckInfoForm> targetAndCheckInfoFormList;


}
