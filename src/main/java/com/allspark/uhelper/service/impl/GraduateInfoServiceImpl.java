package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.form.GraduateInfoForm;
import com.allspark.uhelper.db.mapper.GraduateTargetInfoMapper;
import com.allspark.uhelper.utils.UuidUtils;
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

    @Resource
    GraduateTargetInfoMapper graduateTargetInfoMapper;

    public GraduateInfo transfer(GraduateInfoForm targetForm) {
        GraduateInfo graduateInfo = new GraduateInfo();
        graduateInfo.setId(targetForm.getId());
        graduateInfo.setCollege(targetForm.getCollege());
        graduateInfo.setGrade(targetForm.getGrade());
        graduateInfo.setProfessional(targetForm.getProfessional());
        graduateInfo.setGraduate_count(targetForm.getGraduate_count());
        graduateInfo.setGraduate_target_count(targetForm.getGraduate_target_count());
        graduateInfo.setName(targetForm.getName());
        graduateInfo.setUser_id(targetForm.getUser_id());

        return graduateInfo;
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
    public int insertGraduateInfo(GraduateInfo graduateTargetInfo) {
        Long uuId = UuidUtils.getUuId();
        graduateTargetInfo.setId(uuId);
        int count = graduateTargetInfoMapper.selectGraduateTargetCount(graduateTargetInfo.getId());
        graduateTargetInfo.setGraduate_target_count(count);
        graduateInfoMapper.insertGraduateInfo(graduateTargetInfo);
        return 1;
    }

    @Override
    public void insertGraduateInfoBatch(List<GraduateInfo> graduateInfoList) {
        for (int i = 0; i < graduateInfoList.size(); ++i) {
            GraduateInfo graduateInfo = graduateInfoList.get(i);
            this.insertGraduateInfo(graduateInfo);
        }
    }

    @Override
    public boolean deleteGraduateInfo(GraduateInfo graduateInfo) {
        boolean b = graduateInfoMapper.deleteGraduateInfo(graduateInfo);
        return b;
    }

    @Override
    public boolean updateGraduationName(GraduateInfo graduateInfo) {
        boolean b = graduateInfoMapper.updateGraduationName(graduateInfo);
        return b;
    }

    @Override
    public ArrayList<HashMap> selectGraduateInfoByGradeAndProfessional(GraduateInfo graduateInfo) {
        ArrayList<HashMap> list = graduateInfoMapper.selectGraduateInfoByGradeAndProfessional(graduateInfo);
        return list;
    }
}




