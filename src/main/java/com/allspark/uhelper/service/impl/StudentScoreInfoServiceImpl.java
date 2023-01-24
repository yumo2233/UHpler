package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.StudentScoreInfo;
import com.allspark.uhelper.service.StudentScoreInfoService;
import com.allspark.uhelper.db.mapper.StudentScoreInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【student_score_info】的数据库操作Service实现
* @createDate 2023-01-24 14:51:16
*/
@Service
@DS("u_classinfo")
public class StudentScoreInfoServiceImpl extends ServiceImpl<StudentScoreInfoMapper, StudentScoreInfo>
    implements StudentScoreInfoService{

}




