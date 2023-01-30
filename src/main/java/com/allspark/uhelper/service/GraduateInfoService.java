package com.allspark.uhelper.service;

import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author 86159
 * @description 针对表【graduate_info(毕业要求的信息表)】的数据库操作Service
 * @createDate 2023-01-20 21:05:54
 */
public interface GraduateInfoService extends IService<GraduateInfo> {

    public void insertStudent(@Param("studentInfoCollection") Map param);

}
