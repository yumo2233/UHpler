package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.FkCheckTarget;
import com.allspark.uhelper.service.FkCheckTargetService;
import com.allspark.uhelper.db.mapper.FkCheckTargetMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【fk_check_target(课程目标和考核方式的关系表)】的数据库操作Service实现
* @createDate 2023-01-25 19:45:32
*/
@Service
@DS("u_courseinfo")
public class FkCheckTargetServiceImpl extends ServiceImpl<FkCheckTargetMapper, FkCheckTarget>
    implements FkCheckTargetService{

}




