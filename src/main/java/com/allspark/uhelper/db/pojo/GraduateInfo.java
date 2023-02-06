package com.allspark.uhelper.db.pojo;

import com.allspark.uhelper.myenum.CollegeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 毕业要求的信息表
 * @TableName graduate_info
 */
@TableName(value ="graduate_info")
@Data
public class GraduateInfo implements Serializable {
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
    private Long user_id;

    /**
     * 外键连接的毕业要求
     */
  //  private Long graduateId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}