package com.allspark.uhelper.mapper;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.domain.TClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【t_class(班级的基本信息表)】的数据库操作Mapper
* @createDate 2023-01-14 23:42:33
* @Entity com.allspark.uhelper.domain.TClass
*/
public interface TClassMapper extends BaseMapper<TClass> {
    int insertAll(TClass tClass);

}




