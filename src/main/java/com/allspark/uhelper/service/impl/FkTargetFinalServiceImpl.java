package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.FkTargetFinal;
import com.allspark.uhelper.service.FkTargetFinalService;
import com.allspark.uhelper.db.mapper.FkTargetFinalMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【fk_target_final】的数据库操作Service实现
* @createDate 2023-01-25 22:53:55
*/
@Service
@DS("u_courseinfo")
public class FkTargetFinalServiceImpl extends ServiceImpl<FkTargetFinalMapper, FkTargetFinal>
    implements FkTargetFinalService{

}




