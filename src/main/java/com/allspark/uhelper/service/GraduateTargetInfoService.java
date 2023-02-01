package com.allspark.uhelper.service;

import com.allspark.uhelper.common.form.GraduateTargetForm;
import com.allspark.uhelper.common.form.GraduateTargetInfoForm;
import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.allspark.uhelper.db.pojo.GraduateTargetInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 86159
 * @description 针对表【graduate_target_info(指标点信息表)】的数据库操作Service
 * @createDate 2023-01-20 19:07:15
 */
public interface GraduateTargetInfoService extends IService<GraduateTargetInfo> {

    public GraduateTargetInfo transferTarget(GraduateTargetForm targetForm);

    public GraduateTargetInfo searchTargetById(Long id);

    public ArrayList<HashMap> searchTargetBatch();

    public void insertTarget(GraduateTargetInfo graduateTargetInfo);

    public boolean deleteTarget(long id);

    public boolean updateTarget(GraduateTargetInfo graduateTargetInfo);

    public boolean updateTargetBatch(ArrayList<GraduateTargetInfo> arrayList);
}
