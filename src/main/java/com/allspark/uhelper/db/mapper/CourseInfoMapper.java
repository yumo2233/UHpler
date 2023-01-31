package com.allspark.uhelper.db.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.CourseInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【course_info(基本的课程信息表)】的数据库操作Mapper
* @createDate 2023-01-17 15:35:24
* @Entity com.allspark.uhelper.db.pojo.CourseInfo
*/
@DS("u_courseinfo")
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

    CourseInfo selectAllById(@Param("id") Long id);

}




