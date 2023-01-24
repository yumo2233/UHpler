package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName StudentAndScoreListForm
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/24 17:01
 * @Version 1.0
 **/
@Schema(description = "前端提交的该课程下每个学生的信息包含其平时成绩的列表及课程id")
@Data
public class StudentAndScoreListForm {

    private Long courseID;

    private List<StudentAndScoreForm> studentAndScoreFormList;
}
