package com.allspark.uhelper.service;

import com.allspark.uhelper.common.form.GraduateTargetForm;
import com.allspark.uhelper.db.pojo.GraduateTargetInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * @author 86159
 * @description 针对表【graduate_target_info(指标点信息表)】的数据库操作Service
 * @createDate 2023-01-20 19:07:15
 */
public interface GraduateTargetInfoService extends IService<GraduateTargetInfo> {

    public HashMap searchTargetById(int id);

    public int insertTarget(GraduateTargetInfo graduateTargetInfo);
}
