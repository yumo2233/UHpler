package com.allspark.uhelper.common.form;

import com.allspark.uhelper.db.pojo.StudentInfo;
import com.allspark.uhelper.myenum.CollegeEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * @author Pzr
 * @create 2023/3/8 - 15:10
 */
@Schema(description = "前端提交的班级学生表单")
@Data
@EqualsAndHashCode
public class ClassAndStudentForm {
    /**
     * 班级id
     */
    @TableId
    private Long id;

    /**
     * 学院
     */
    private CollegeEnum college;

    /**
     * 专业
     */
    private String professional;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 班级名称
     */
    private Integer name;

    /**
     * 班级人数
     */
    private Integer headcount;

    /**
     * 外键关联是哪个用户创建的
     */
    private Long userId;
    /**
     * 学生信息
     */
    private ArrayList<StudentInfo> arrayList;
}
