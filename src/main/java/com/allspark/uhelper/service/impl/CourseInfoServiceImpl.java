package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.form.CheckInfoForm;
import com.allspark.uhelper.common.form.CourseInfoForm;
import com.allspark.uhelper.common.form.TargetInfoForm;
import com.allspark.uhelper.common.resp.CheckInfoResp;
import com.allspark.uhelper.common.resp.CourseInfoResp;
import com.allspark.uhelper.common.resp.TargetInfoResp;
import com.allspark.uhelper.db.mapper.*;
import com.allspark.uhelper.db.pojo.*;
import com.allspark.uhelper.utils.CopyUtil;
import com.allspark.uhelper.utils.SnowFlake;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author 86159
* @description 针对表【course_info(基本的课程信息表)】的数据库操作Service实现
* @createDate 2023-01-17 15:35:24
*/
@Service
@DS("u_courseinfo")
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo>
    implements CourseInfoService{

    @Resource
    private CourseInfoMapper courseInfoMapper;
    @Resource
    private FkClassCourseMapper fkClassCourseMapper;

    @Resource
    private FkPreMapper fkPreMapper;

    @Resource
    private CheckInfoMapper checkInfoMapper;

    @Resource
    private TargetInfoMapper targetInfoMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public List<CourseInfoResp> listCourseInfoResp(List<CourseInfo> course){

        List<CourseInfoResp> courseInfoResps = new ArrayList<>();
        for (CourseInfo courseInfo : course) {
            CourseInfoResp courseInfoResp = CopyUtil.copy(courseInfo, CourseInfoResp.class);
            courseInfoResp.setId(courseInfo.getId());
            List<Long> fkClassCourses = fkClassCourseMapper.selectClassIdByCourseId(courseInfo.getId());
            List<Long> fkPres = fkPreMapper.selectPreidById(courseInfo.getId());
            List<CheckInfo> checkInfoList = checkInfoMapper.selectAllByCourseId(courseInfo.getId());
            List<TargetInfo> targetInfoList = targetInfoMapper.selectAllByCourseId(courseInfo.getId());
            List<CheckInfoResp> checkInfoResp = CopyUtil.copyList(checkInfoList, CheckInfoResp.class);
            List<TargetInfoResp> targetInfoResp = CopyUtil.copyList(targetInfoList,TargetInfoResp.class);
            if (CollectionUtils.isEmpty(checkInfoResp)) {
                checkInfoResp.add(new CheckInfoResp());
            }
            if (CollectionUtils.isEmpty(targetInfoResp)) {
                targetInfoResp.add(new TargetInfoResp());
            }
            courseInfoResp.setClassList(fkClassCourses);
            courseInfoResp.setPreList(fkPres);
            courseInfoResp.setCheckList(checkInfoResp);
            courseInfoResp.setTargetList(targetInfoResp);
            courseInfoResps.add(courseInfoResp);
        }
        return courseInfoResps;
    }

    public HashMap<String,Object> extractPost(CourseInfoForm courseInfoForm) {
        SnowFlake snowFlake = new SnowFlake();
        HashMap map = new HashMap();
        CourseInfo courseInfo = CopyUtil.copy(courseInfoForm, CourseInfo.class);
        List<Long> classList = courseInfoForm.getClassList();
        List<Long> preList = courseInfoForm.getPreList();
        List<CheckInfoForm> checkInfoFormList = courseInfoForm.getCheckList();
        List<TargetInfoForm> targetInfoFormList = courseInfoForm.getTargetList();
        List<FkPre> preList1 = new ArrayList<>();
        List<FkClassCourse> classList1 = new ArrayList<>();
        List<CheckInfo> checkInfoList = CopyUtil.copyList(checkInfoFormList, CheckInfo.class);
        List<TargetInfo> targetInfoList = CopyUtil.copyList(targetInfoFormList, TargetInfo.class);
        Long courseId = courseInfoForm.getId();
        if (!CollectionUtils.isEmpty(preList)) {
            for (Long aLong : preList) {
                FkPre fkPre = new FkPre();
                fkPre.setId(courseId);
                fkPre.setPreId(aLong);
                preList1.add(fkPre);
            }
        } else {
            FkPre fkPre = new FkPre();
            fkPre.setId(courseId);
            fkPre.setPreId(-1L);
            preList1.add(fkPre);
        }
        if (!CollectionUtils.isEmpty(classList)) {
            for (Long aLong : classList) {
                FkClassCourse fkClassCourse = new FkClassCourse();
                fkClassCourse.setClassId(aLong);
                fkClassCourse.setCourseId(courseId);
                classList1.add(fkClassCourse);
            }
        }
        else {
            FkClassCourse fkClassCourse = new FkClassCourse();
            fkClassCourse.setCourseId(courseId);
            fkClassCourse.setClassId(-1L);
            classList1.add(fkClassCourse);
        }
        if (CollectionUtils.isEmpty(checkInfoList)) {
            CheckInfo checkInfo = new CheckInfo();
            checkInfo.setCourseId(courseId);
            checkInfo.setId(snowFlake.nextId());
            checkInfoList.add(checkInfo);
        } else {
            for (CheckInfo checkInfo : checkInfoList) {
                checkInfo.setId(snowFlake.nextId());
                checkInfo.setCourseId(courseId);
            }
        }
        if (CollectionUtils.isEmpty(targetInfoList)) {
            TargetInfo targetInfo = new TargetInfo();
            targetInfo.setId(snowFlake.nextId());
            targetInfo.setName("");
            targetInfo.setNumber("");
            targetInfo.setContent("");
            targetInfo.setCourseId(courseId);
            targetInfo.setGraduateId(-1L);
            targetInfoList.add(targetInfo);
        } else {
            for (TargetInfo targetInfo : targetInfoList) {
                targetInfo.setCourseId(courseId);
                targetInfo.setId(snowFlake.nextId());
            }
        }
        map.put("courseInfo",courseInfo);
        map.put("preList1",preList1);
        map.put("classList1",classList1);
        map.put("checkInfoList",checkInfoList);
        map.put("targetInfoList",targetInfoList);
        return map;
    }

    public boolean modifyOneCourseInfo(CourseInfoForm courseInfoForm){
        boolean flag;
        HashMap<String,Object> map = extractPost(courseInfoForm);
        List<FkPre> preList1 = (ArrayList<FkPre>)map.get("preList1");
        List<FkClassCourse> classList1 =(ArrayList<FkClassCourse>)map.get("classList1");
        List<CheckInfo> checkInfoList = (ArrayList<CheckInfo>)map.get("checkInfoList");
        List<TargetInfo> targetInfoList = (ArrayList<TargetInfo>)map.get("targetInfoList");
        Long courseId = courseInfoForm.getId();
        CourseInfo courseInfo = (CourseInfo)map.get("courseInfo");

        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            updateById(courseInfo);
            fkPreMapper.delById(courseId);
            fkClassCourseMapper.delByCourseId(courseId);
            checkInfoMapper.delByCourseId(courseId);
            targetInfoMapper.delByCourseId(courseId);
            fkPreMapper.insertBatch(preList1);
            fkClassCourseMapper.insertBatch(classList1);
            checkInfoMapper.insertBatch(checkInfoList);
            targetInfoMapper.insertBatch(targetInfoList);

            return true;
        }));

        return flag;

    }

    public boolean addOneCourseInfo(CourseInfoForm courseInfoForm){
        boolean flag;
        SnowFlake snowFlake = new SnowFlake();
        courseInfoForm.setId(snowFlake.nextId());
        HashMap<String,Object> map = extractPost(courseInfoForm);
        List<FkPre> preList1 = (ArrayList<FkPre>)map.get("preList1");
        List<FkClassCourse> classList1 =(ArrayList<FkClassCourse>)map.get("classList1");
        List<CheckInfo> checkInfoList = (ArrayList<CheckInfo>)map.get("checkInfoList");
        List<TargetInfo> targetInfoList = (ArrayList<TargetInfo>)map.get("targetInfoList");
        CourseInfo courseInfo = (CourseInfo)map.get("courseInfo");

        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            save(courseInfo);
            fkPreMapper.insertBatch(preList1);
            fkClassCourseMapper.insertBatch(classList1);
            checkInfoMapper.insertBatch(checkInfoList);
            targetInfoMapper.insertBatch(targetInfoList);
            return true;
        }));

        return flag;

    }


}




