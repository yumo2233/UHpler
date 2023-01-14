package com.allspark.uhelper.mapper;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.domain.Test1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86159
* @description 针对表【test1】的数据库操作Mapper
* @createDate 2023-01-12 18:29:21
* @Entity com.allspark.uhelper.domain.Test1
*/
@Mapper
public interface Test1Mapper extends BaseMapper<Test1> {

    int insertAll(Test1 test1);

    void test();
}




