package com.allspark.uhelper.service;

import com.allspark.uhelper.db.pojo.ClassInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86159
* @description 针对表【class_info(班级的基本信息表)】的数据库操作Service
* @createDate 2023-01-17 02:28:46
*/
@DS("u_classinfo")
public interface ClassInfoService extends IService<ClassInfo> {

}
