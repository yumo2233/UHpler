package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.form.CourseInfoForm;
import com.allspark.uhelper.common.resp.CourseInfoResp;
import com.allspark.uhelper.db.mapper.CheckInfoMapper;
import com.allspark.uhelper.db.mapper.FkClassCourseMapper;
import com.allspark.uhelper.db.mapper.FkPreMapper;
import com.allspark.uhelper.db.pojo.CheckInfo;
import com.allspark.uhelper.db.pojo.FkClassCourse;
import com.allspark.uhelper.db.pojo.FkPre;
import com.allspark.uhelper.utils.CopyUtil;
import com.allspark.uhelper.utils.SnowFlake;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.CourseInfo;
import com.allspark.uhelper.service.CourseInfoService;
import com.allspark.uhelper.db.mapper.CourseInfoMapper;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
            courseInfoResp.setClassList(fkClassCourses);
            courseInfoResp.setPreList(fkPres);
            courseInfoResp.setCheckList(checkInfoList);
            courseInfoResps.add(courseInfoResp);
        }

        return courseInfoResps;
    }

    public boolean modifyOneCourseInfo(CourseInfoForm courseInfoForm){
        boolean flag;
        CourseInfo courseInfo = CopyUtil.copy(courseInfoForm, CourseInfo.class);
        courseInfo.setId(courseInfoForm.getId());
        List<Long> classList = courseInfoForm.getClassList();
        List<Long> preList = courseInfoForm.getPreList();
        List<FkPre> preList1 = new ArrayList<>();
        List<FkClassCourse> classList1 = new ArrayList<>();
        Long courseId = courseInfo.getId();
        if (!CollectionUtils.isEmpty(preList)) {
            for (Long aLong : preList) {
                FkPre fkPre = new FkPre();
                fkPre.setId(courseId);
                fkPre.setPreId(aLong);
                preList1.add(fkPre);
            }
        }
        if (!CollectionUtils.isEmpty(classList)) {
            for (Long aLong : classList) {
                FkClassCourse fkClassCourse = new FkClassCourse();
                fkClassCourse.setClassId(aLong);
                fkClassCourse.setCourseId(courseId);
                classList1.add(fkClassCourse);
            }
        }

        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            save(courseInfo);
            fkPreMapper.delById(courseId);
            fkClassCourseMapper.delByCourseId(courseId);
            fkPreMapper.insertBatch(preList1);
            fkClassCourseMapper.insertBatch(classList1);

            return true;
        }));

        return flag;

    }

    public boolean addOneCourseInfo(CourseInfoForm courseInfoForm){
        boolean flag;
        SnowFlake snowFlake = new SnowFlake();
        CourseInfo courseInfo = CopyUtil.copy(courseInfoForm, CourseInfo.class);
        courseInfo.setId(snowFlake.nextId());
        List<Long> classList = courseInfoForm.getClassList();
        List<Long> preList = courseInfoForm.getPreList();
        List<FkPre> preList1 = new ArrayList<>();
        List<FkClassCourse> classList1 = new ArrayList<>();
        Long courseId = courseInfo.getId();
        if (!CollectionUtils.isEmpty(preList)) {
            for (Long aLong : preList) {
                FkPre fkPre = new FkPre();
                fkPre.setId(courseId);
                fkPre.setPreId(aLong);
                preList1.add(fkPre);
            }
        }
        if (!CollectionUtils.isEmpty(classList)) {
            for (Long aLong : classList) {
                FkClassCourse fkClassCourse = new FkClassCourse();
                fkClassCourse.setClassId(aLong);
                fkClassCourse.setCourseId(courseId);
                classList1.add(fkClassCourse);
            }
        }


        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            save(courseInfo);
            fkPreMapper.insertBatch(preList1);
            fkClassCourseMapper.insertBatch(classList1);
            return true;
        }));

        return flag;

    }



}




