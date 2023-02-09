package com.allspark.uhelper.common.form;

import com.allspark.uhelper.myenum.CollegeEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Pzr
 * @create 2023/1/31 - 19:03
 */
@Data
@Schema(description = "前端返回的毕业指标点")
public class GraduateTargetForm {
    /**
     * 指标点id
     */
    @TableId
    private Long id;

    /**
     * 指标点名称
     */
    private String name;

    /**
     * 指标点内容
     */
    private String content;

    /**
     * 年级
     */
    private int grade;

    /**
     * 专业
     */
    private String professional;

    /**
     * 外键连接的毕业要求
     */
    private Long graduate_id;

    private Long graduateId;

    /**
     * 毕业要求的名字
     */
    private String graduate_name;
}
