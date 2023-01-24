package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName ListStudentForCourseId
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/24 14:18
 * @Version 1.0
 **/
@Schema(description = "根据课程id返回学生信息表单")
@Data
public class ListStudentForCourseIdForm {

    @Schema(description = "前端传入的对应课程所包含的班级的id列表")
    private Long[] ids;

    @Schema(description = "课程id")
    private Long courseId;
}
