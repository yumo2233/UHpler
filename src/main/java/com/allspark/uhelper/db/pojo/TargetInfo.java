package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程目标表
 * @TableName target_info
 */
@TableName(value ="target_info")
@Data
public class TargetInfo implements Serializable {
    /**
     * 课程指标点id
     */
    @TableId
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String number;

    /**
     * 内容
     */
    private String content;

    /**
     * 对应的课程
     */
    private Long courseId;

    /**
     * 对应的毕业要求的指标点
     */
    private Long graduateId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}