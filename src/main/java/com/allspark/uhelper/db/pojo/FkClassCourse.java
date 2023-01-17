package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 班级和课程的关联表
 * @TableName fk_class_course
 */
@TableName(value ="fk_class_course")
@Data
public class FkClassCourse implements Serializable {
    /**
     * 班级id
     */

    private Long classId;

    /**
     * 课程id
     */

    private Long courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}