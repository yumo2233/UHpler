package com.allspark.uhelper.common.form;


import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName StudentInfoForm
        * @Description TODO
        * @Author Pzr
        * @Date 2023/1/23 - 18:19
        * @Version 1.0
        **/
//int id,int index,String name,int number, int class_id
@Schema(description = "学生")
@Data
public class StudentInfoForm {
    //学生Id
    @TableId
    private long id;
    //班内序号
    int index;
    //姓名
    String name;
    //学号
    int number;
    //班级
    int class_id;

}
