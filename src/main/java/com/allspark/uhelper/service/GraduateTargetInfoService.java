package com.allspark.uhelper.service;

import com.allspark.uhelper.common.form.GraduateTargetForm;
import com.allspark.uhelper.db.pojo.GraduateTargetInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 86159
 * @description 针对表【graduate_target_info(指标点信息表)】的数据库操作Service
 * @createDate 2023-01-20 19:07:15
 */
public interface GraduateTargetInfoService extends IService<GraduateTargetInfo> {


    public GraduateTargetInfo transferTarget(GraduateTargetForm targetForm);

    public GraduateTargetInfo searchTargetById(Long id);

    public ArrayList<HashMap> searchTargetBatch(GraduateTargetInfo graduateTargetInfo);

    public int insertTarget(GraduateTargetInfo graduateTargetInfo);

    public int insertTargetBatch(List<GraduateTargetInfo> list);

    public boolean deleteTarget(long Id);

    public boolean updateTarget(GraduateTargetInfo graduateTargetInfo);

    public void updateTargetBatch(ArrayList<GraduateTargetInfo> arrayList);

    public int selectGraduateTargetCount(long graduate_id);
}
