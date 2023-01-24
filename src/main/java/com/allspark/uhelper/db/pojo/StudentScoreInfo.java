package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName student_score_info
 */
@TableName(value ="student_score_info")
@Data
public class StudentScoreInfo implements Serializable {
    /**
     * 学生的id
     */
    @TableId
    private Long id;

    /**
     * 学生对应的课程id
     */
    private Long courseId;

    /**
     * 对应的平时成绩列表
     */
    private Object usualScore;

    /**
     * 对应的期末成绩列表
     */
    private Object finalScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}