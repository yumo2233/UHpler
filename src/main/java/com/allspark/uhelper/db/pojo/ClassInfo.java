package com.allspark.uhelper.db.pojo;

import com.allspark.uhelper.myenum.CollegeEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 班级的基本信息表
 * @TableName class_info
 */
@TableName(value ="class_info")
@Data
public class ClassInfo implements Serializable {
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}