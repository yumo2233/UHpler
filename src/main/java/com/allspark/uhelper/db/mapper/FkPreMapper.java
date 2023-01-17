package com.allspark.uhelper.db.mapper;
import java.util.List;

import com.allspark.uhelper.db.pojo.FkPre;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 86159
* @description 针对表【fk_pre(课程与先修课程的关系表)】的数据库操作Mapper
* @createDate 2023-01-17 17:12:39
* @Entity com.allspark.uhelper.db.pojo.FkPre
*/
@DS("u_courseinfo")
public interface FkPreMapper extends BaseMapper<FkPre> {

    List<FkPre> selectPreidById(@Param("id") Long id);


    int updateSelective(FkPre fkPre);

    int delById(@Param("id") Long id);

    int insertAll(FkPre fkPre);
}




