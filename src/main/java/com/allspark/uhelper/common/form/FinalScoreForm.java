package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName FinalScoreForm
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/24 17:16
 * @Version 1.0
 **/
@Schema(description = "前端提交的期末成绩构成的表单")
@Data
public class FinalScoreForm {


    @Schema(description = "课程id")
    private Long courseId;

    @Schema(description = "对应的课程目标和期末考试成绩构成的关系的列表")
    private List<TargetAndFinalForm> targetAndFinalFormList;
}
