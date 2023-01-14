package com.allspark.uhelper.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.domain.Test1;
import com.allspark.uhelper.service.Test1Service;
import com.allspark.uhelper.mapper.Test1Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 86159
* @description 针对表【test1】的数据库操作Service实现
* @createDate 2023-01-12 18:29:21
*/
@Service
@DS("uhelper")
public class Test1ServiceImpl extends ServiceImpl<Test1Mapper, Test1>
    implements Test1Service{
    @Resource
    private Test1Mapper test1Mapper;

    public void add(Test1 test1){
        test1Mapper.insertAll(test1);
    }

}




