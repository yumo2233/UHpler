package com.allspark.uhelper.service;

import com.allspark.uhelper.db.pojo.StudentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 86159
 * @description 针对表【student_info(学生的基本信息表)】的数据库操作Service
 * @createDate 2023-01-24 14:25:54
 */
public interface StudentInfoService extends IService<StudentInfo> {


    ArrayList<HashMap> selectStudentByClassId(long class_id);
}
