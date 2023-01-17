package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.db.mapper.FkClassCourseMapper;
import com.allspark.uhelper.db.pojo.FkClassCourse;
import com.allspark.uhelper.service.FkClassCourseService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName FkClassCourseServiceImpl
 * @Description 课程和班级关系表的ServiceImpl
 * @Author 86159
 * @Date 2023/1/17 17:04
 * @Version 1.0
 **/
@Service
@DS("u_classinfo")
public class FkClassCourseServiceImpl extends ServiceImpl<FkClassCourseMapper, FkClassCourse> {
}
