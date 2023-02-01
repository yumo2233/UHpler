package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.form.GraduateTargetInfoForm;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.allspark.uhelper.service.GraduateInfoService;
import com.allspark.uhelper.db.mapper.GraduateInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86159
 * @description 针对表【graduate_info(毕业要求的信息表)】的数据库操作Service实现
 * @createDate 2023-01-20 21:05:54
 */
@Service
@DS("u_graduateinfo")
public class GraduateInfoServiceImpl extends ServiceImpl<GraduateInfoMapper, GraduateInfo>
        implements GraduateInfoService {

    @Resource
    private GraduateInfoMapper graduateInfoMapper;

    public GraduateInfo transfer(GraduateTargetInfoForm targetForm) {
        GraduateInfo graduateTargetInfo = new GraduateInfo();
        graduateTargetInfo.setId(targetForm.getId());
        graduateTargetInfo.setCollege(targetForm.getCollege());
        graduateTargetInfo.setGrade(targetForm.getGrade());
        graduateTargetInfo.setProfessional(targetForm.getProfessional());
        graduateTargetInfo.setGraduate_count(targetForm.getGraduate_count());
        graduateTargetInfo.setGraduate_target_count(targetForm.getGraduate_target_count());
        graduateTargetInfo.setName(targetForm.getName());
        graduateTargetInfo.setUser_id(targetForm.getUser_id());
        return graduateTargetInfo;
    }

    @Override
    public void insertStudent(Map param) {
        graduateInfoMapper.insertStudent(param);

    }

    @Override
    public ArrayList<HashMap> selectCollegeAndGrade() {
        ArrayList<HashMap> list = graduateInfoMapper.selectCollegeAndGrade();
        return list;
    }

    @Override
    public int insertInfoTarget(GraduateInfo graduateTargetInfo) {

        graduateInfoMapper.insertGraduateInfo(graduateTargetInfo);
        return 1;
    }

    @Override
    public void insertGraduateInfoBatch(List<GraduateInfo> graduateInfoList) {
        graduateInfoMapper.insertGraduateInfoBatch(graduateInfoList);
    }
}




