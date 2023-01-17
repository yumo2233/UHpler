package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.ClassInfo;
import com.allspark.uhelper.service.ClassInfoService;
import com.allspark.uhelper.db.mapper.ClassInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【class_info(班级的基本信息表)】的数据库操作Service实现
* @createDate 2023-01-17 02:28:46
*/
@Service
@DS("u_classinfo")
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo>
    implements ClassInfoService{

}




