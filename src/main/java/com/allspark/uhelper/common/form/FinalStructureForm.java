package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName FinalStructureForm
 * @Description TODO
 * @Author 86159
 * @Date 2023/2/27 21:06
 * @Version 1.0
 **/
@Data
@Schema(description = "期末成绩构成(新)")
public class FinalStructureForm {

    @Schema(description = "课程id")
    private Long courseId;

    HashMap<Integer, String>[] first1;

    HashMap<Integer, String>[] first2;

    HashMap<Integer, String>[] first3;

    HashMap<Integer, String>[] first4;
}
