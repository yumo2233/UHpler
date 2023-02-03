package com.allspark.uhelper.common.form;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 课程目标表
 * @TableName target_info
 */
@Schema(description = "前端返回的课程目标类")
@Data
@EqualsAndHashCode
public class TargetInfoForm implements Serializable {
    /**
     * 课程指标点id
     */
    @TableId
    private Long id;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 内容
     */
    private String content;

    /**
     * 编码
     */
    private String number;

    /**
     * 名称
     */
    private String name;

    /**
     * 关联的毕业要求对应的指标点的id
     */
    private Long graduateId;

}