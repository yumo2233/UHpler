package com.allspark.uhelper.common.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Pzr
 * @create 2023/2/4 - 21:38
 */
@Schema(description = "返回给前端毕业要求表中已经存在的年级专业")
@Data
public class GradeAndProfessionalResp {

    //专业
    private String professional;

    //年级
    private Integer grade;

}
