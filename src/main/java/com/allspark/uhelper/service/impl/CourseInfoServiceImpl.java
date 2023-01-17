package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.form.CourseInfoForm;
import com.allspark.uhelper.common.resp.CourseInfoResp;
import com.allspark.uhelper.db.mapper.FkClassCourseMapper;
import com.allspark.uhelper.db.mapper.FkPreMapper;
import com.allspark.uhelper.db.pojo.FkClassCourse;
import com.allspark.uhelper.db.pojo.FkPre;
import com.allspark.uhelper.utils.CopyUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.CourseInfo;
import com.allspark.uhelper.service.CourseInfoService;
import com.allspark.uhelper.db.mapper.CourseInfoMapper;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

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

    @Autowired
    private TransactionTemplate transactionTemplate;

    public List<CourseInfoResp> listCourseInfoResp(List<CourseInfo> course){

        List<CourseInfoResp> courseInfoResps = new ArrayList<>();
        for (CourseInfo courseInfo : course) {
            CourseInfoResp courseInfoResp = CopyUtil.copy(courseInfo, CourseInfoResp.class);
            List<FkClassCourse> fkClassCourses = fkClassCourseMapper.selectClassIdByCourseId(courseInfo.getId());
            List<Long> classIdList = new ArrayList<>();
            for (FkClassCourse fkClassCourse : fkClassCourses) {
                classIdList.add(fkClassCourse.getClassId());
            }
            List<FkPre> fkPres = fkPreMapper.selectPreidById(courseInfo.getId());
            List<Long> preIdList = new ArrayList<>();
            for (FkPre fkPre : fkPres) {
                preIdList.add(fkPre.getPreId());
            }
            courseInfoResp.setClassList(classIdList);
            courseInfoResp.setPreList(preIdList);
            courseInfoResps.add(courseInfoResp);
        }

        return courseInfoResps;
    }

    public boolean modifyOneCourseInfo(CourseInfoForm courseInfoForm){
        boolean flag;
        CourseInfo courseInfo = CopyUtil.copy(courseInfoForm, CourseInfo.class);
        List<Long> classList = courseInfoForm.getClassList();
        List<Long> preList = courseInfoForm.getPreList();
        List<FkPre> preList1 = new ArrayList<>();
        List<FkClassCourse> classList1 = new ArrayList<>();

        Long courseId = courseInfoForm.getId();
        for (Long aLong : preList) {
            FkPre fkPre = new FkPre();
            fkPre.setId(courseId);
            fkPre.setPreId(aLong);
            preList1.add(fkPre);
        }
        for (Long aLong : classList) {
            FkClassCourse fkClassCourse = new FkClassCourse();
            fkClassCourse.setClassId(aLong);
            fkClassCourse.setCourseId(courseId);
            classList1.add(fkClassCourse);
        }

        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            updateById(courseInfo);
            fkPreMapper.delById(courseId);
            fkClassCourseMapper.delByCourseId(courseId);

            for (FkPre fkPre : preList1) {
                fkPreMapper.insertAll(fkPre);
            }
            for (FkClassCourse fkClassCourse : classList1) {
                fkClassCourseMapper.insertAll(fkClassCourse);
            }

            return true;
        }));

        return flag;

    }



}




