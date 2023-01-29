package com.allspark.uhelper.common.resp;

import com.allspark.uhelper.myenum.CollegeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName ListAllCourseResp
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/25 17:36
 * @Version 1.0
 **/
@Schema(description = "返回给前端的所有课程的课程类")
@Data
@EqualsAndHashCode
public class ListAllCourseResp {

    /**
     * 课程id
     */
    private Long id;

    /**
     * 所属院系（枚举存储）
     */
    private CollegeEnum college;

    /**
     * 任课老师姓名
     */
    private String teacher;

    /**
     * 名称
     */
    private String name;

    /**
     * 课程编号
     */
    private String number;
}
