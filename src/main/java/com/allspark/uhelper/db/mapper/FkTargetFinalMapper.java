package com.allspark.uhelper.db.mapper;
import java.util.Collection;
import java.util.List;

import com.allspark.uhelper.common.form.TargetAndFinalForm;
import com.allspark.uhelper.db.pojo.TargetInfo;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.FkTargetFinal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【fk_target_final】的数据库操作Mapper
* @createDate 2023-01-25 22:53:55
* @Entity com.allspark.uhelper.db.pojo.FkTargetFinal
*/
public interface FkTargetFinalMapper extends BaseMapper<FkTargetFinal> {

    List<FkTargetFinal> selectAllByTargetId(@Param("targetId") Long targetId);

    int delByTargetId(List<TargetAndFinalForm> item);

    int insertBatch(@Param("fkTargetFinalCollection") Collection<FkTargetFinal> fkTargetFinalCollection);

}




