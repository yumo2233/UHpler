package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.FkPre;
import com.allspark.uhelper.service.FkPreService;
import com.allspark.uhelper.db.mapper.FkPreMapper;
import org.springframework.stereotype.Service;

/**
* @author 86159
* @description 针对表【fk_pre(课程与先修课程的关系表)】的数据库操作Service实现
* @createDate 2023-01-17 17:12:39
*/
@Service
@DS("u_courseinfo")
public class FkPreServiceImpl extends ServiceImpl<FkPreMapper, FkPre>
    implements FkPreService{

}




