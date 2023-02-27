package com.allspark.uhelper.db.mapper;
import java.util.Collection;
import java.util.List;

import com.allspark.uhelper.db.pojo.StudentInfo;
import net.sf.jsqlparser.statement.replace.Replace;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.StudentScoreInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【student_score_info】的数据库操作Mapper
* @createDate 2023-01-24 14:51:16
* @Entity com.allspark.uhelper.db.pojo.StudentScoreInfo
*/
@DS("u_classinfo")
public interface StudentScoreInfoMapper extends BaseMapper<StudentScoreInfo> {

    List<StudentScoreInfo> selectAllByCourseId(@Param("courseId") Long courseId);

    int updateBatchByCourseId(List<StudentScoreInfo> list);

    int insertBatch(@Param("studentScoreInfoCollection") List<StudentScoreInfo> studentScoreInfoCollection);

    int insertFinalScoreBatch(@Param("studentScoreInfoCollection") List<StudentScoreInfo> studentScoreInfoCollection);

    List<StudentScoreInfo> selectAllByIdAndCourseId(@Param("studentInfoList")List<StudentInfo> studentInfoList, Long courseId);



}




