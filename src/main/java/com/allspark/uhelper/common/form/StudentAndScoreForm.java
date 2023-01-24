package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName StudentAndUsualResp
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/24 14:23
 * @Version 1.0
 **/
@Schema(description = "前端提交的该课程下每个学生的信息包含其平时成绩")
@Data
public class StudentAndScoreForm {

    /**
     * 学生id
     */
    private Long id;

    /**
     * 所属班级，外键连接班级
     */
    private Long classId;

    /**
     * 所属的班级名称
     */
    private String className;

    /**
     * 学号
     */
    private String number;

    /**
     * 姓名
     */
    private String name;

    /**
     * 班内序号
     */
    private Integer index;

    /**
     * 平时成绩列表
     */

    private Integer[] usualScore;

    /**
     * 期末成绩列表
     */

    private Integer[] finalScore;
}
