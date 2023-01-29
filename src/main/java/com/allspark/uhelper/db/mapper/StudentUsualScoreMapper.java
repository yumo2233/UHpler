package com.allspark.uhelper.db.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.StudentUsualScore;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【student_usual_score】的数据库操作Mapper
* @createDate 2023-01-29 17:05:20
* @Entity com.allspark.uhelper.db.pojo.StudentUsualScore
*/
@DS("u_classinfo")
public interface StudentUsualScoreMapper extends BaseMapper<StudentUsualScore> {
    List<StudentUsualScore> selectUsualScoreByCourseIdAndTargetIdAndCheckId(@Param("courseId") Long courseId, @Param("targetId") Long targetId, @Param("checkId") Long checkId);

    List<StudentUsualScore> selectUsualScoreByCourseId(@Param("courseId") Long courseId);

    List<StudentUsualScore> selectAllByCourseId(@Param("courseId") Long courseId);
}




