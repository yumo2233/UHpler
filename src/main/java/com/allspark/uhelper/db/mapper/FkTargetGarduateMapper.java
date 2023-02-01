package com.allspark.uhelper.db.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.FkTargetGarduate;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【fk_target_garduate(课程目标关联的毕业要求的指标点的关系表)】的数据库操作Mapper
* @createDate 2023-01-31 23:32:42
* @Entity com.allspark.uhelper.db.pojo.FkTargetGarduate
*/
@DS("u_courseinfo")
public interface FkTargetGarduateMapper extends BaseMapper<FkTargetGarduate> {

    List<FkTargetGarduate> selectAllByTargetId(@Param("targetId") Long targetId);

}




