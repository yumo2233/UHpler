package com.allspark.uhelper.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程与先修课程的关系表
 * @TableName fk_pre
 */
@TableName(value ="fk_pre")
@Data
public class FkPre implements Serializable {
    /**
     * 课程的id
     */

    private Long id;

    /**
     * 此课程先修课程的id
     */
    private Long preId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}