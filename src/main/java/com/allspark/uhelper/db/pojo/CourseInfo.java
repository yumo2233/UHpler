package com.allspark.uhelper.db.pojo;

import com.allspark.uhelper.myenum.CollegeEnum;
import com.allspark.uhelper.myenum.SelectEnum;
import com.allspark.uhelper.myenum.SemesterEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 基本的课程信息表
 * @TableName course_info
 */
@TableName(value ="course_info")
@Data
public class CourseInfo implements Serializable {
    /**
     * 课程id
     */
    @TableId
    private Long id;

    /**
     * 所属院系（枚举存储）
     */
    private CollegeEnum college;

    /**
     * 开设单位
     */
    private String unit;

    /**
     * 任课老师姓名
     */
    private String teacher;

    /**
     * 名称
     */
    private String name;

    /**
     * 性质（枚举存储）
     */
    private SelectEnum nature;

    /**
     * 课程编号
     */
    private String number;

    /**
     * 总学时
     */
    private Integer allPeriod;

    /**
     * 理论学时
     */
    private Integer theoryPeriod;

    /**
     * 实验学时
     */
    private Integer runPeriod;

    /**
     * 学分
     */
    private Integer score;

    /**
     * 其他先修课程（其他先修课程的信息)字符串
     */
    private String preElse;


    /**
     * 学生人数
     */
    private Integer studentNum;

    /**
     * 执行学期（枚举值）
     */
    private Integer semester;

    /**
     * 平时成绩占比
     */
    private BigDecimal usualRatio;

    /**
     * 期末成绩占比
     */
    private BigDecimal finalRatio;

    /**
     * 对应的期末考试id
     */
    private Long finalId;

    /**
     * 对应的用户id
     */
    private Long userId;

    /**
     * 对应的用户名称
     */
    private String userName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}