package com.allspark.uhelper.common.resp;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName StudentUsualScoreResp
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/29 16:44
 * @Version 1.0
 **/
@Data
public class StudentUsualScoreResp {
    /**
     * 学生id
     */
    private Long id;

    /**
     * 所属班级，外键连接班级
     */
    private Long classId;

    /**
     * 所属的班级名称
     */
    private String className;

    /**
     * 学号
     */
    private String number;

    /**
     * 姓名
     */
    private String name;

    /**
     * 班内序号
     */
    private Integer index;

    /**
     * 平时成绩列表
     */
    private Map usual;
}
