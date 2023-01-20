package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 毕业要求的信息表
 * @TableName graduate_info
 */
@TableName(value ="graduate_info")
@Data
public class GraduateInfo implements Serializable {
    /**
     * 毕业要求id
     */
    @TableId
    private Long id;

    /**
     * 学院（枚举）
     */
    private Integer college;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 专业
     */
    private String professional;

    /**
     * 专业对应的毕业要求个数
     */
    private Integer graduateCount;

    /**
     * 毕业要求对应的指标点个数
     */
    private Integer graduateTargetCount;

    /**
     * 毕业要求的名字
     */
    private String name;

    /**
     * 创建该毕业要求的用户id
     */
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}