package com.allspark.uhelper.db.mapper;
import java.util.Collection;
import java.util.List;

import com.allspark.uhelper.db.pojo.StudentDbInfo;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.StudentInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【student_info(学生的基本信息表)】的数据库操作Mapper
* @createDate 2023-01-24 14:25:54
* @Entity com.allspark.uhelper.db.pojo.StudentInfo
*/
@DS("u_classinfo")
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {

    List<StudentInfo> selectAllByClassId(@Param("classId") Long... classId);

    List<StudentInfo> listAllByClassIdIn(@Param("classIdList") List<Long> classIdList);

    void insertStudent(StudentInfo studentInfo);
}




