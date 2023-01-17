package com.allspark.uhelper.db.mapper;

import com.allspark.uhelper.db.pojo.ClassInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【class_info(班级的基本信息表)】的数据库操作Mapper
* @createDate 2023-01-17 02:28:46
* @Entity com.allspark.uhelper.pojo.ClassInfo
*/
@DS("u_classinfo")
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

}




