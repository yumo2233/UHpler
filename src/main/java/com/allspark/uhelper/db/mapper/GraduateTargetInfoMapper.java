package com.allspark.uhelper.db.mapper;
import org.apache.ibatis.annotations.Param;

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

    public GraduateTargetInfo searchTargetById(long id);

    public void insertTarget(GraduateTargetInfo graduateTargetInfo);

    public void insertTargetBatch(List<GraduateTargetInfo> list);

    public boolean deleteTarget(GraduateTargetInfo graduateTargetInfo);

    public boolean updateTarget(GraduateTargetInfo graduateTargetInfo);

    public boolean updateTargetBatch(ArrayList<GraduateTargetInfo> arrayList);

    public ArrayList<HashMap> searchTargetBatch(long graduate_id);

    public int selectGraduateTargetCount(long graduate_id);

    List<GraduateTargetInfo> selectAll();

    GraduateTargetInfo selectById(@Param("id") Long id);
}




