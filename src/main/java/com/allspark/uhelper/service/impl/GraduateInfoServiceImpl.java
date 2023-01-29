package com.allspark.uhelper.service.impl;

import cn.hutool.core.map.MapUtil;
import com.allspark.uhelper.common.form.Class_info;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.allspark.uhelper.service.GraduateInfoService;
import com.allspark.uhelper.db.mapper.GraduateInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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

    @Override
    public void insertStudent(Map param) {
        graduateInfoMapper.insertStudent(param);

    }

    @Override
    public HashMap selectCollegeAndGrade() {
        Class_info class_info = graduateInfoMapper.selectCollegeAndGrade();
        HashMap map = new HashMap();
        map.put("id", class_info.getId());
        map.put("college", class_info.getCollege());
        map.put("professional", class_info.getProfessional());
        map.put("grade", class_info.getGrade());
        return map;
    }
}




