package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 考核方式表
 * @TableName check_info
 */
@TableName(value ="check_info")
@Data
public class CheckInfo implements Serializable {
    /**
     * 考核方式id
     */
    @TableId
    private Long id;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 考核比重
     */
    private BigDecimal ratio;

    /**
     * 考核名称
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}