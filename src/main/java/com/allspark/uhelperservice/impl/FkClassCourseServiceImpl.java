package com.allspark.uhelperservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.FkClassCourse;
import com.allspark.uhelper.service.FkClassCourseService;
import com.allspark.uhelper.db.mapper.FkClassCourseMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【fk_class_course(班级和课程的关联表)】的数据库操作Service实现
* @createDate 2023-01-17 17:00:21
*/
@Service
public class FkClassCourseServiceImpl extends ServiceImpl<FkClassCourseMapper, FkClassCourse>
    implements FkClassCourseService{

}




