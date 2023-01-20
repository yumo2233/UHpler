package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.TargetInfo;
import com.allspark.uhelper.service.TargetInfoService;
import com.allspark.uhelper.db.mapper.TargetInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【target_info(课程目标表)】的数据库操作Service实现
* @createDate 2023-01-20 19:27:40
*/
@Service
@DS("u_courseinfo")
public class TargetInfoServiceImpl extends ServiceImpl<TargetInfoMapper, TargetInfo>
    implements TargetInfoService{

}




