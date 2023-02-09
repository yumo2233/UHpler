package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Pzr
 * @create 2023/2/9 - 22:40
 */
@TableName(value = "student_info")
@Data
public class StudentDbInfo {


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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
