package com.allspark.uhelper.service;

import com.allspark.uhelper.common.form.ClassInfoForm;
import com.allspark.uhelper.common.form.StudentInfoForm;
import com.allspark.uhelper.db.pojo.ClassInfo;
import com.allspark.uhelper.db.pojo.StudentDbInfo;
import com.allspark.uhelper.db.pojo.StudentInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 86159
 * @description 针对表【class_info(班级的基本信息表)】的数据库操作Service
 * @createDate 2023-01-17 02:28:46
 */
@DS("u_classinfo")
public interface ClassInfoService extends IService<ClassInfo> {

    StudentDbInfo transferStudentDb(StudentInfoForm studentInfoForm);

    StudentInfo transferStudent(StudentInfoForm studentInfoForm);

    ClassInfo transferClass(ClassInfoForm classInfoForm);

    void insetClass(ClassInfo classInfo);

    boolean deleteClass(Long id);

    void updateClass(ClassInfo classInfo);

    ArrayList<HashMap> selectClass();

    void insertStudent(StudentInfo studentInfo);

    HashMap selectClassById(long id);
}
