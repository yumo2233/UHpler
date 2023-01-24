package com.allspark.uhelper.common.form;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 考核方式表
 * @TableName check_info
 */
@Schema(description = "前端提交的考核方式表单")
@Data
@EqualsAndHashCode
public class CheckInfoForm implements Serializable {
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

}