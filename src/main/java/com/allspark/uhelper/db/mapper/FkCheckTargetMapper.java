package com.allspark.uhelper.db.mapper;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.FkCheckTarget;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【fk_check_target(课程目标和考核方式的关系表)】的数据库操作Mapper
* @createDate 2023-01-25 19:45:32
* @Entity com.allspark.uhelper.db.pojo.FkCheckTarget
*/
@DS("u_courseinfo")
public interface FkCheckTargetMapper extends BaseMapper<FkCheckTarget> {

    ArrayList<HashMap> selectAllByTargetId(@Param("targetId") Long targetId);

    int delByCheckIdAndTargetId(List<FkCheckTarget> item);

    int insertBatch(@Param("fkCheckTargetCollection") Collection<FkCheckTarget> fkCheckTargetCollection);

}




