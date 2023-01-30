package com.allspark.uhelper.db.mapper;
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

    String selectNameById(@Param("id") Long id);

    public void insertStudent(@Param("studentInfoCollection") Map param);

}




