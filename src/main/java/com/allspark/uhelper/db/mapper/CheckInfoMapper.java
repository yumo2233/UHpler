package com.allspark.uhelper.db.mapper;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.CheckInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【check_info(考核方式表)】的数据库操作Mapper
* @createDate 2023-01-18 14:50:52
* @Entity com.allspark.uhelper.db.pojo.CheckInfo
*/
@DS("u_courseinfo")
public interface CheckInfoMapper extends BaseMapper<CheckInfo> {

    List<CheckInfo> selectIdByCourseId(@Param("courseId") Long courseId);

    List<CheckInfo> selectAllByCourseId(@Param("courseId") Long courseId);

    int delByCourseId(@Param("courseId") Long courseId);

    int insertBatch(@Param("checkInfoCollection") Collection<CheckInfo> checkInfoCollection);

}




