package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.allspark.uhelper.service.GraduateInfoService;
import com.allspark.uhelper.db.mapper.GraduateInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【graduate_info(毕业要求的信息表)】的数据库操作Service实现
* @createDate 2023-01-20 21:05:54
*/
@Service
@DS("u_graduateinfo")
public class GraduateInfoServiceImpl extends ServiceImpl<GraduateInfoMapper, GraduateInfo>
    implements GraduateInfoService{

}



