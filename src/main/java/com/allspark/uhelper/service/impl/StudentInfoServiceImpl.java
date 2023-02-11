package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.StudentInfo;
import com.allspark.uhelper.service.StudentInfoService;
import com.allspark.uhelper.db.mapper.StudentInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 86159
 * @description 针对表【student_info(学生的基本信息表)】的数据库操作Service实现
 * @createDate 2023-01-24 14:25:54
 */
@Service
@DS("u_classinfo")
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo>
        implements StudentInfoService {

    @Resource
    StudentInfoMapper studentInfoMapper;

    @Override
    public ArrayList<HashMap> selectStudentByClassId(long class_id) {
        ArrayList<HashMap> arrayList = studentInfoMapper.selectStudentByClassId(class_id);
        return arrayList;

    }
}




