package com.allspark.uhelper.common.resp.classTree;

import com.allspark.uhelper.myenum.CollegeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @ClassName ClassTreeResp
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/18 20:11
 * @Version 1.0
 **/
@Schema(description = "返回给前端的班级三级树")
@Data
public class CollegeTree {
    CollegeEnum collegeEnum;

    Set<ProfessionalTree> professionalTree;
}
