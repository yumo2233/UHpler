package com.allspark.uhelper.db.mapper;
import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import java.util.Collection;

import com.allspark.uhelper.db.pojo.TargetInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【target_info(课程目标表)】的数据库操作Mapper
* @createDate 2023-01-20 19:27:40
* @Entity com.allspark.uhelper.db.pojo.TargetInfo
*/
@DS("u_courseinfo")
public interface TargetInfoMapper extends BaseMapper<TargetInfo> {

    int insertBatch(@Param("targetInfoCollection") Collection<TargetInfo> targetInfoCollection);

    int delByCourseId(@Param("courseId") Long courseId);

    List<TargetInfo> selectAllByCourseId(@Param("courseId") Long courseId);

}




