package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程目标关联的毕业要求的指标点的关系表
 * @TableName fk_target_garduate
 */
@TableName(value ="fk_target_garduate")
@Data
public class FkTargetGarduate implements Serializable {
    /**
     * 课程目标的id
     */
    @TableId
    private Long targetId;

    /**
     * 毕业要求对应的指标点id
     */
    private Long graduateTargetId;

    /**
     * 毕业要求的id
     */
    private Long graduateId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}