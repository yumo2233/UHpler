package com.allspark.uhelper.db.mapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.ClassInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 86159
 * @description 针对表【class_info(班级的基本信息表)】的数据库操作Mapper
 * @createDate 2023-01-17 02:28:46
 * @Entity com.allspark.uhelper.pojo.ClassInfo
 */
@DS("u_classinfo")
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

    String selectNameById(@Param("id") Long id);

    void insetClass(ClassInfo classInfo);

    boolean deleteClass(Long id);

    void updateClass(ClassInfo classInfo);

    ArrayList<HashMap> selectClass();

    int selectCourse(long class_id);

    HashMap selectClassById(long id);

    List<ClassInfo> selectBatchIds(List<Long> longs);

    List<ClassInfo> listAll();



}




