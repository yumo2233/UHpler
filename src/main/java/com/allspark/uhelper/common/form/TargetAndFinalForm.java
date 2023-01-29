package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TargetAndFinalForm
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/24 17:23
 * @Version 1.0
 **/
@Data
@Schema(description = "单个课程目标和期末考试对应题号的关系表单")
public class TargetAndFinalForm {
    @Schema(description = "对应的课程目标id")
    private Long targetId;

    @Schema(description = "对应的课程目标名称")
    private String targetName;

    @Schema(description = "题号列表")
    private HashMap<Integer,Integer[]> first;

}
