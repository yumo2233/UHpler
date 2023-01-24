package com.allspark.uhelper.service.impl;

import cn.hutool.json.JSONBeanParser;
import cn.hutool.json.JSONUtil;
import com.allspark.uhelper.common.form.*;
import com.allspark.uhelper.common.resp.CheckInfoResp;
import com.allspark.uhelper.common.resp.CourseInfoResp;
import com.allspark.uhelper.common.resp.StudentAndScoreResp;
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

    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Resource
    private  StudentScoreInfoMapper studentScoreInfoMapper;

    @Resource
    private ClassInfoMapper classInfoMapper;

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
        HashMap <String,Object> map = new HashMap();
        BigDecimal count0 = courseInfoForm.getFinalRatio().add(courseInfoForm.getUsualRatio());
        int flag1,flag0;
        BigDecimal bigDecimal0 = new BigDecimal("0");
        BigDecimal bigDecimal1 = new BigDecimal("1");
        flag1 = count0.compareTo(bigDecimal1);
        flag0 = count0.compareTo(bigDecimal0);
        if (flag1==0||flag0==0) {

        } else {
            String s = new String("期末平时占比不归一");
            map.put("message", s);
            return map;
        }
        SnowFlake snowFlake = new SnowFlake();
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
        BigDecimal count1 = new BigDecimal("0");
        if (CollectionUtils.isEmpty(checkInfoList)) {
            CheckInfo checkInfo = new CheckInfo();
            checkInfo.setCourseId(courseId);
            checkInfo.setId(snowFlake.nextId());
            checkInfo.setRatio(new BigDecimal(0));
            checkInfoList.add(checkInfo);
        } else {
            for (CheckInfo checkInfo : checkInfoList) {
                checkInfo.setId(snowFlake.nextId());
                checkInfo.setCourseId(courseId);
                count1 = count1.add(checkInfo.getRatio());
            }
        }
        flag1 = count1.compareTo(bigDecimal1);
        flag0 = count1.compareTo(bigDecimal0);
        if (flag1==0||flag0==0) {

        } else {
            String s = new String("考核方式占比不归一");
            map.put("message", s);
            return map;
        }

        System.out.println(count1);
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

    public HashMap<String,Object> modifyOneCourseInfo(CourseInfoForm courseInfoForm){
        boolean flag;
        HashMap<String,Object> map = extractPost(courseInfoForm);
        HashMap<String,Object> result = new HashMap<>();
        if (map.containsKey("message")) {
            result.put("message", map.get("message"));
            result.put("flag", false);
            return result;
        }

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
        result.put("flag", flag);
        return result;

    }

    public HashMap<String,Object> addOneCourseInfo(CourseInfoForm courseInfoForm){
        boolean flag;
        SnowFlake snowFlake = new SnowFlake();
        courseInfoForm.setId(snowFlake.nextId());
        HashMap<String,Object> map = extractPost(courseInfoForm);
        HashMap<String,Object> result = new HashMap<>();
        if (map.containsKey("message")) {
            result.put("message", map.get("message"));
            result.put("flag", false);
            return result;
        }
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
        result.put("flag", flag);
        return result;

    }

    public List<StudentAndScoreResp> listAllStudent(ListStudentForCourseIdForm form){
        List<StudentAndScoreResp>  studentAndScoreRespList;
        List<StudentInfo> studentInfoList = studentInfoMapper.listAllByClassIdIn(form.getIds());
        List<StudentScoreInfo> studentScoreInfoList = studentScoreInfoMapper.selectAllByCourseId(form.getCourseId());
        studentAndScoreRespList = CopyUtil.copyList(studentInfoList, StudentAndScoreResp.class);
        HashMap<Long,StudentScoreInfo> map = new HashMap<>();
        for (StudentScoreInfo studentScoreInfo : studentScoreInfoList) {
            map.put(studentScoreInfo.getId(), studentScoreInfo);
        }
        for (StudentAndScoreResp studentAndScoreResp : studentAndScoreRespList) {
            StudentScoreInfo studentScoreInfo = new StudentScoreInfo();
            if (!map.containsKey(studentAndScoreResp.getId())) {
             studentScoreInfo.setUsualScore(new String("[0]"));
             studentScoreInfo.setFinalScore(new String("[0]"));
             studentScoreInfo.setId(studentAndScoreResp.getId());
             studentScoreInfo.setCourseId(form.getCourseId());
            } else {
                studentScoreInfo=map.get(studentAndScoreResp.getId());
            }
            studentAndScoreResp.setUsualScore(JSONUtil.parse(studentScoreInfo.getUsualScore()).toBean(Integer[].class));
            studentAndScoreResp.setFinalScore(JSONUtil.parse(studentScoreInfo.getFinalScore()).toBean(Integer[].class));
            studentAndScoreResp.setClassName(classInfoMapper.selectNameById(studentAndScoreResp.getClassId()));
        }
        return studentAndScoreRespList;
    }


    public List<StudentAndScoreResp> modifyAllStudent(StudentAndScoreListForm form) {

        List<StudentAndScoreResp> studentAndScoreRespList = new ArrayList<>();
        return studentAndScoreRespList;
    }
}




