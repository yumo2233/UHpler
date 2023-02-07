package com.allspark.uhelper.db.mapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 86159
 * @description 针对表【graduate_info(毕业要求的信息表)】的数据库操作Mapper
 * @createDate 2023-01-20 21:05:54
 * @Entity com.allspark.uhelper.db.pojo.GraduateInfo
 */
@DS("u_graduateinfo")
public interface GraduateInfoMapper extends BaseMapper<GraduateInfo> {

    public String selectNameById(@Param("id") Long id);

    public ArrayList<HashMap> selectCollegeAndGrade();

    public void insertStudent(@Param("studentInfoCollection") Map param);

    public int insertGraduateInfo(GraduateInfo graduateTargetInfo);

    public void insertGraduateInfoBatch(List<GraduateInfo> graduateInfoList);

    public boolean deleteGraduateInfo(long id);

    public boolean updateGraduationName(GraduateInfo graduateInfo);

    public long selectId(GraduateInfo graduateInfo);

    public long selectIdByname(GraduateInfo graduateInfo);

    public int selectGraduate_target_count(GraduateInfo graduateInfo);

    public void updateGraduate_target_count(GraduateInfo graduateInfo);

    public ArrayList<HashMap> selectGraduateInfoByGradeAndProfessional(GraduateInfo graduateInfo);

    public ArrayList<Integer> selectGrade();

    public ArrayList<String> selectProfessional();

    public ArrayList<GraduateInfo> selectGradeAndProfessional();

    public ArrayList<HashMap> selectall(GraduateInfo graduateInfo);

    public GraduateInfo selectGAP(long id);
}




