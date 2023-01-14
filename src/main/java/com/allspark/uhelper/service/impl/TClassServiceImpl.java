package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.domain.TClass;
import com.allspark.uhelper.service.TClassService;
import com.allspark.uhelper.mapper.TClassMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【t_class(班级的基本信息表)】的数据库操作Service实现
* @createDate 2023-01-14 23:42:33
*/
@DS("u_classinfo")
@Service
public class TClassServiceImpl extends ServiceImpl<TClassMapper, TClass>
    implements TClassService{

}




