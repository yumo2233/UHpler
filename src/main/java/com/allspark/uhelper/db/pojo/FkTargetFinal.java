package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName fk_target_final
 */
@TableName(value ="fk_target_final")
@Data
public class FkTargetFinal implements Serializable {
    /**
     * 目标id
     */
    private Long targetId;

    /**
     * 大题题号
     */
    private Integer first;

    /**
     * 小题列表
     */
    private Object second;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}