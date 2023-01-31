package com.allspark.uhelper.common.form;

import com.allspark.uhelper.myenum.CollegeEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Pzr
 * @create 2023/1/30 - 21:20
 */
@Data
@Schema(description = "前端返回的毕业信息")
public class GraduateTargetInfoForm implements Serializable {
    /**
     * 毕业要求id
     */
    @TableId
    private Long id;

    /**
     * 所属院系（枚举存储）
     */
    private CollegeEnum college;

    /**
     * 年级
     */
    private int grade;

    /**
     * 专业
     */
    private String professional;

    /**
     * 专业对应的毕业要求个数
     */
    private int graduate_count;

    /**
     * 毕业要求对应的指标点个数
     */
    private int graduate_target_count;

    /**
     * 毕业要求的名字
     */
    private String name;

    /**
     * 创建该毕业要求的用户id
     */
    private String user_id;


}
