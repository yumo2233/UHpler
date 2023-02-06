package com.allspark.uhelper.db.mapper;

import com.allspark.uhelper.db.pojo.GraduateTargetInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 86159
 * @description 针对表【graduate_target_info(指标点信息表)】的数据库操作Mapper
 * @createDate 2023-01-20 19:07:15
 * @Entity com.allspark.uhelper.db.pojo.GraduateTargetInfo
 */
@DS("u_graduateinfo")
public interface GraduateTargetInfoMapper extends BaseMapper<GraduateTargetInfo> {

     GraduateTargetInfo searchTargetById(long id);

    void insertTarget(GraduateTargetInfo graduateTargetInfo);

    void insertTargetBatch(List<GraduateTargetInfo> list);

    boolean deleteTarget(GraduateTargetInfo graduateTargetInfo);

    boolean updateTarget(GraduateTargetInfo graduateTargetInfo);

    boolean updateTargetBatch(ArrayList<GraduateTargetInfo> arrayList);

    ArrayList<HashMap> searchTargetBatch(long graduate_id);

    int selectGraduateTargetCount(long graduate_id);

    ArrayList<HashMap> selectGraduateTarget(long graduate_id);
}




