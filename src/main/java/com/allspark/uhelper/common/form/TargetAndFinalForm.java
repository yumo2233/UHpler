package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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

    @Schema(description = "大题题号列表")
    private List<List<Integer>> first;

    @Schema(description = "小题题号列表")
    private List<Integer> second;
}
