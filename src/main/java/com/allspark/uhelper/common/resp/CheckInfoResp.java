package com.allspark.uhelper.common.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 考核方式表
 * @TableName check_info
 */
@Schema(description = "返回给前端的考核方式类")
@Data
public class CheckInfoResp implements Serializable {
    /**
     * 考核方式id
     */
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