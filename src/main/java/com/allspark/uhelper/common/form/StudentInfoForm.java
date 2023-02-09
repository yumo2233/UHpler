package com.allspark.uhelper.common.form;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Pzr
 * @create 2023/2/9 - 20:49
 */
@Schema(description = "前端提交的单个学生表单")
@Data
@EqualsAndHashCode
public class StudentInfoForm {

    /**
     * 学生id
     */
    @TableId
    private Long id;

    /**
     * 所属班级，外键连接班级
     */
    private Long classId;

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
}
