package com.allspark.uhelper.db.pojo;

import com.allspark.uhelper.myenum.CollegeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 指标点信息表
 * @TableName graduate_target_info
 */
@TableName(value = "graduate_target_info")
@Data
public class GraduateTargetInfo implements Serializable {
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
     * 外键连接的毕业要求
     */
    private Long graduate_id;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}


