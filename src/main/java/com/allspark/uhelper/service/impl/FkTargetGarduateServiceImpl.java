package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.FkTargetGarduate;
import com.allspark.uhelper.service.FkTargetGarduateService;
import com.allspark.uhelper.db.mapper.FkTargetGarduateMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【fk_target_garduate(课程目标关联的毕业要求的指标点的关系表)】的数据库操作Service实现
* @createDate 2023-01-31 23:32:42
*/
@Service
@DS("u_courseinfo")
public class FkTargetGarduateServiceImpl extends ServiceImpl<FkTargetGarduateMapper, FkTargetGarduate>
    implements FkTargetGarduateService{

}




