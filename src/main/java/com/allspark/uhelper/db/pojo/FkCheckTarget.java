package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 课程目标和考核方式的关系表
 * @TableName fk_check_target
 */
@TableName(value ="fk_check_target")
@Data
public class FkCheckTarget implements Serializable {
    /**
     * 考核方式id
     */
    private Long checkId;

    /**
     * 课程目标id
     */
    private Long targetId;

    /**
     * 占该种考核的比率(同一课程下)
     */
    private BigDecimal targetRatio;

    /**
     * 该课程目标下的测试次数
     */
    private Integer targetCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}