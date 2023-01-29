package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.StudentUsualScore;
import com.allspark.uhelper.service.StudentUsualScoreService;
import com.allspark.uhelper.db.mapper.StudentUsualScoreMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【student_usual_score】的数据库操作Service实现
* @createDate 2023-01-29 17:05:20
*/
@Service
@DS("u_classinfo")
public class StudentUsualScoreServiceImpl extends ServiceImpl<StudentUsualScoreMapper, StudentUsualScore>
    implements StudentUsualScoreService{

}




