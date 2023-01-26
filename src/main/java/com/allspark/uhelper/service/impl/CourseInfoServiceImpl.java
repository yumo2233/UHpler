package com.allspark.uhelper.service.impl;

import cn.hutool.json.JSONUtil;
import com.allspark.uhelper.common.form.*;
import com.allspark.uhelper.common.resp.*;
import com.allspark.uhelper.db.mapper.*;
import com.allspark.uhelper.db.pojo.*;
import com.allspark.uhelper.utils.CopyUtil;
import com.allspark.uhelper.utils.SnowFlake10;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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

    @Resource
    private FkCheckTargetMapper fkCheckTargetMapper;

    @Resource
    private FkTargetFinalMapper fkTargetFinalMapper;

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

    public List<ListAllCourseResp> listAllCourseResp(List<CourseInfo> course){
        List<ListAllCourseResp> listAllCourseRespList = CopyUtil.copyList(course, ListAllCourseResp.class);
        return listAllCourseRespList;
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
        SnowFlake10 snowFlake = new SnowFlake10();
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
        SnowFlake10 snowFlake = new SnowFlake10();
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

    public List<StudentAndScoreResp> listAllStudent(Long courseId){
        List<Long> ids = fkClassCourseMapper.selectClassIdByCourseId(courseId);
        List<StudentAndScoreResp>  studentAndScoreRespList;
        List<StudentInfo> studentInfoList = studentInfoMapper.listAllByClassIdIn(ids);
        List<StudentScoreInfo> studentScoreInfoList = studentScoreInfoMapper.selectAllByCourseId(courseId);
        studentAndScoreRespList = CopyUtil.copyList(studentInfoList, StudentAndScoreResp.class);
        HashMap<Long,StudentScoreInfo> map = new HashMap<>();
        for (StudentScoreInfo studentScoreInfo : studentScoreInfoList) {
            map.put(studentScoreInfo.getId(), studentScoreInfo);
        }
        for (StudentAndScoreResp studentAndScoreResp : studentAndScoreRespList) {
            StudentScoreInfo studentScoreInfo = new StudentScoreInfo();
            if (!map.containsKey(studentAndScoreResp.getId())) {
             studentScoreInfo.setUsualScore("[0]");
             studentScoreInfo.setFinalScore("[0]");
             studentScoreInfo.setId(studentAndScoreResp.getId());
             studentScoreInfo.setCourseId(courseId);
            } else {
                studentScoreInfo=map.get(studentAndScoreResp.getId());
            }
            studentAndScoreResp.setUsualScore(JSONUtil.parse(studentScoreInfo.getUsualScore()).toBean(Integer[].class));
            studentAndScoreResp.setFinalScore(JSONUtil.parse(studentScoreInfo.getFinalScore()).toBean(Integer[].class));
            studentAndScoreResp.setClassName(classInfoMapper.selectNameById(studentAndScoreResp.getClassId()));
        }
        return studentAndScoreRespList;
    }


    public boolean modifyAllStudent(StudentAndScoreListForm form) {
        boolean flag;
        Long courseId = form.getCourseID();
        List<StudentAndScoreForm> studentAndScoreFormList = form.getStudentAndScoreFormList();
        List<StudentScoreInfo> studentScoreInfoList = CopyUtil.copyList(studentAndScoreFormList,StudentScoreInfo.class);
        for (StudentScoreInfo studentScoreInfo : studentScoreInfoList) {
            studentScoreInfo.setCourseId(courseId);
            studentScoreInfo.setUsualScore(JSONUtil.parse(studentScoreInfo.getUsualScore()).toBean(String.class));
            studentScoreInfo.setFinalScore(JSONUtil.parse(studentScoreInfo.getFinalScore()).toBean(String.class));
        }
        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            studentScoreInfoMapper.insertBatch(studentScoreInfoList);
            return true;
        }));
        return flag;
    }

    public UsualScoreResp listUsual(Long courseId) {
        UsualScoreResp usualScoreResp = new UsualScoreResp();
        usualScoreResp.setCourseId(courseId);
        usualScoreResp.setTargetAndCheckFormList(new ArrayList<>());
        List<TargetInfo> targetInfoList = targetInfoMapper.selectAllByCourseId(courseId);
        for (TargetInfo targetInfo : targetInfoList) {
            TargetAndCheckForm targetAndCheckForm = new TargetAndCheckForm();
            targetAndCheckForm.setTargetId(targetInfo.getId());
            targetAndCheckForm.setTargetName(targetInfo.getName());
            List<TargetAndCheckInfoForm> targetAndCheckInfoFormList = new ArrayList<>();
            for (HashMap map : fkCheckTargetMapper.selectAllByTargetId(targetInfo.getId())) {
                TargetAndCheckInfoForm targetAndCheckInfoForm = JSONUtil.parse(map).toBean(TargetAndCheckInfoForm.class);
                targetAndCheckInfoFormList.add(targetAndCheckInfoForm);
            }
            targetAndCheckForm.setTargetAndCheckInfoFormList(targetAndCheckInfoFormList);
            usualScoreResp.getTargetAndCheckFormList().add(targetAndCheckForm);
        }
        return usualScoreResp;
    }

    public boolean modifyUsual(UsualScoreForm form) {
        boolean flag;
        List<TargetAndCheckForm> targetAndCheckFormList = form.getTargetAndCheckFormList();
        List<FkCheckTarget> fkCheckTargetList = new ArrayList<>();
        for (TargetAndCheckForm targetAndCheckForm : targetAndCheckFormList) {
            for (TargetAndCheckInfoForm targetAndCheckInfoForm : targetAndCheckForm.getTargetAndCheckInfoFormList()) {
                Long targetId = targetAndCheckForm.getTargetId();
                Long checkId = targetAndCheckInfoForm.getCheckId();
                BigDecimal targetRatio = targetAndCheckInfoForm.getTargetRatio();
                Integer targetCount = targetAndCheckInfoForm.getTargetCount();
                FkCheckTarget fkCheckTarget = new FkCheckTarget();
                fkCheckTarget.setTargetId(targetId);
                fkCheckTarget.setCheckId(checkId);
                fkCheckTarget.setTargetRatio(targetRatio);
                fkCheckTarget.setTargetCount(targetCount);
                fkCheckTargetList.add(fkCheckTarget);
            }
        }

        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            fkCheckTargetMapper.delByCheckIdAndTargetId(fkCheckTargetList);
            fkCheckTargetMapper.insertBatch(fkCheckTargetList);
            return true;
        }));
        return flag;
    }

    public FinalScoreResp listFinal(Long courseId) {
        FinalScoreResp finalScoreResp = new FinalScoreResp();
        finalScoreResp.setCourseId(courseId);
        List<TargetInfo> targetInfoList = targetInfoMapper.selectAllByCourseId(courseId);
        List<TargetAndFinalForm> targetAndFinalFormList = new ArrayList<>();
        for (TargetInfo targetInfo : targetInfoList) {
            TargetAndFinalForm targetAndFinalForm = new TargetAndFinalForm();
            targetAndFinalForm.setTargetId(targetInfo.getId());
            targetAndFinalForm.setTargetName(targetInfo.getName());
            HashMap<Integer,Integer[]> firstMap = new HashMap<>();
            List<FkTargetFinal> fkTargetFinals = fkTargetFinalMapper.selectAllByTargetId(targetInfo.getId());
            for (FkTargetFinal fkTargetFinal : fkTargetFinals) {
                firstMap.put(fkTargetFinal.getFirst(), JSONUtil.parse(fkTargetFinal.getSecond()).toBean(Integer[].class));
            }
            targetAndFinalForm.setFirst(firstMap);
            targetAndFinalFormList.add(targetAndFinalForm);
        }
        finalScoreResp.setTargetAndFinalFormList(targetAndFinalFormList);
        return finalScoreResp;
    }

    public boolean modifyFinal(FinalScoreForm form) {
        boolean flag = false;
        Long courseId = form.getCourseId();
        List<TargetAndFinalForm> targetAndFinalFormList = form.getTargetAndFinalFormList();
        List<FkTargetFinal> fkTargetFinalList = new ArrayList<>();
        for (TargetAndFinalForm targetAndFinalForm : targetAndFinalFormList) {
            Iterator iterator = targetAndFinalForm.getFirst().entrySet().iterator();
            while (iterator.hasNext()) {
                FkTargetFinal fkTargetFinal = new FkTargetFinal();
                fkTargetFinal.setTargetId(targetAndFinalForm.getTargetId());
                Map.Entry entry = (Map.Entry) iterator.next();
                fkTargetFinal.setFirst((Integer) entry.getKey());
                fkTargetFinal.setSecond(JSONUtil.parse(entry.getValue()).toBean(String.class));
                fkTargetFinalList.add(fkTargetFinal);
            }
        }

        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            fkTargetFinalMapper.delByTargetId(targetAndFinalFormList);
            fkTargetFinalMapper.insertBatch(fkTargetFinalList);
            return true;
        }));

        return flag;
    }
}



