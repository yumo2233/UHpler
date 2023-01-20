package com.allspark.uhelper.common.resp;

import com.allspark.uhelper.myenum.CollegeEnum;
import com.allspark.uhelper.myenum.SelectEnum;
import com.allspark.uhelper.myenum.SemesterEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName CourseResp
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/17 16:31
 * @Version 1.0
 **/
@Schema(description = "返回给前端的课程类")
@Data
@EqualsAndHashCode
public class CourseInfoResp {
    /**
     * 课程id
     */
    private Long id;

    /**
     * 所属院系（枚举存储）
     */
    private CollegeEnum college;

    /**
     * 开设单位
     */
    private String unit;

    /**
     * 任课老师姓名
     */
    private String teacher;

    /**
     * 名称
     */
    private String name;

    /**
     * 性质（枚举存储）
     */
    private SelectEnum nature;

    /**
     * 课程编号
     */
    private String number;

    /**
     * 总学时
     */
    private Integer allPeriod;

    /**
     * 理论学时
     */
    private Integer theoryPeriod;

    /**
     * 实验学时
     */
    private Integer runPeriod;

    /**
     * 学分
     */
    private Integer score;

    /**
     * 先修课程的列表
     */
    private List<Long> preList;

    /**
     * 其他先修课程（其他先修课程的信息)字符串
     */
    private String preElse;

    /**
     * 考核对象班级的列表
     */
    private List<Long> classList;

    /**
     * 学生人数
     */
    private Integer studentNum;

    /**
     * 执行学期（枚举值）
     */
    private SemesterEnum semester;

    /**
     * 平时成绩占比
     */
    private BigDecimal usualRatio;

    /**
     * 期末成绩占比
     */
    private BigDecimal finalRatio;

    /**
     * 对应的期末考试id
     */
    private Long finalId;

    /**
     * 对应的用户id
     */
    private Long userId;

    /**
     * 对应的用户名称
     */
    private String userName;

    /**
     * 考核方式的列表
     */
    private List<CheckInfoResp> checkList;

    /**
     * 课程目标的列表
     */
    private List<TargetInfoResp> targetList;

}
