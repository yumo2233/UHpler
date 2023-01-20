package com.allspark.uhelper.common.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 课程目标表
 * @TableName target_info
 */
@Schema(description = "返回给前端的课程目标类")
@Data
@EqualsAndHashCode
public class TargetInfoResp implements Serializable {
    /**
     * 课程指标点id
     */
    @TableId
    private Long id;

    /**
     * 对应的课程
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