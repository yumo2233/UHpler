package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName student_usual_score
 */
@TableName(value ="student_usual_score")
@Data
public class StudentUsualScore implements Serializable {
    /**
     * 学生id
     */
    private Long id;

    /**
     * 
     */
    private Long targetId;

    /**
     * 
     */
    private Long checkId;

    /**
     * 
     */
    private Object usualScore;

    /**
     * 
     */
    private Long courseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}