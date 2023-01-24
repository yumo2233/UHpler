package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName UsualScoreForm
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/24 17:16
 * @Version 1.0
 **/
@Schema(description = "前端提交的平时成绩构成的表单")
@Data
public class UsualScoreForm {

    @Schema(description = "课程id")
    private Long courseId;

    @Schema(description = "每一个课程目标和考核方式之间关系的列表")
    private List<TargetAndCheckForm> targetAndCheckFormList;
}
