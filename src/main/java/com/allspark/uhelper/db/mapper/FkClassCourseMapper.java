package com.allspark.uhelper.db.mapper;
import java.util.Collection;
import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.FkClassCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【fk_class_course(班级和课程的关联表)】的数据库操作Mapper
* @createDate 2023-01-17 17:00:21
* @Entity com.allspark.uhelper.db.pojo.FkClassCourse
*/
@DS("u_classinfo")
public interface FkClassCourseMapper extends BaseMapper<FkClassCourse> {
    List<Long> selectClassIdByCourseId(@Param("courseId") Long courseId);

    int updateSelective(FkClassCourse fkClassCourse);

    int delByCourseId(@Param("courseId") Long courseId);

    int insertAll(FkClassCourse fkClassCourse);

    int insertBatch(@Param("fkClassCourseCollection") Collection<FkClassCourse> fkClassCourseCollection);
}




