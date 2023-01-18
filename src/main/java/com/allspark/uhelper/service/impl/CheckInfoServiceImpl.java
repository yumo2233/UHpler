package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.CheckInfo;
import com.allspark.uhelper.service.CheckInfoService;
import com.allspark.uhelper.db.mapper.CheckInfoMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【check_info(考核方式表)】的数据库操作Service实现
* @createDate 2023-01-18 14:50:52
*/
@Service
@DS("u_courseinfo")
public class CheckInfoServiceImpl extends ServiceImpl<CheckInfoMapper, CheckInfo>
    implements CheckInfoService{

}




