package com.allspark.uhelper.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.allspark.uhelper.common.form.*;
import com.allspark.uhelper.common.resp.*;
import com.allspark.uhelper.db.mapper.*;
import com.allspark.uhelper.db.pojo.*;
import com.allspark.uhelper.utils.CopyUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.service.CourseInfoService;
import com.spire.doc.*;
import com.spire.doc.fields.TextRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
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
        implements CourseInfoService {

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
    private StudentScoreInfoMapper studentScoreInfoMapper;

    @Resource
    private ClassInfoMapper classInfoMapper;

    @Resource
    private FkCheckTargetMapper fkCheckTargetMapper;

    @Resource
    private FkTargetFinalMapper fkTargetFinalMapper;

    @Resource
    private FkTargetGarduateMapper fkTargetGarduateMapper;

    @Resource
    private GraduateTargetInfoMapper graduateTargetInfoMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Resource
    private StudentUsualScoreMapper studentUsualScoreMapper;


    public List<CourseInfoResp> listCourseInfoResp(List<CourseInfo> course) {
        List<CourseInfoResp> courseInfoResps = new ArrayList<>();
        for (CourseInfo courseInfo : course) {
            CourseInfoResp courseInfoResp = CopyUtil.copy(courseInfo, CourseInfoResp.class);
            courseInfoResp.setId(courseInfo.getId());
            List<Long> fkClassCourses = fkClassCourseMapper.selectClassIdByCourseId(courseInfo.getId());
            List<Long> fkPres = fkPreMapper.selectPreidById(courseInfo.getId());
            List<CheckInfo> checkInfoList = checkInfoMapper.selectAllByCourseId(courseInfo.getId());
            List<TargetInfo> targetInfoList = targetInfoMapper.selectAllByCourseId(courseInfo.getId());
            List<CheckInfoResp> checkInfoResp = CopyUtil.copyList(checkInfoList, CheckInfoResp.class);
            List<TargetInfoResp> targetInfoResp = CopyUtil.copyList(targetInfoList, TargetInfoResp.class);
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

    public List<ListAllCourseResp> listAllCourseResp(List<CourseInfo> course) {
        List<ListAllCourseResp> listAllCourseRespList = CopyUtil.copyList(course, ListAllCourseResp.class);
        return listAllCourseRespList;
    }

    public HashMap<String, Object> extractPost(CourseInfoForm courseInfoForm) {
        HashMap<String, Object> map = new HashMap();
        BigDecimal count0 = courseInfoForm.getFinalRatio().add(courseInfoForm.getUsualRatio());
        int flag1, flag0;
        BigDecimal bigDecimal0 = new BigDecimal("0");
        BigDecimal bigDecimal1 = new BigDecimal("1");
        flag1 = count0.compareTo(bigDecimal1);
        flag0 = count0.compareTo(bigDecimal0);
        if (flag1 == 0 || flag0 == 0) {

        } else {
            String s = new String("期末平时占比不归一");
            map.put("message", s);
            return map;
        }
//        SnowFlake10 snowFlake = new SnowFlake10();
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
        } else {
            FkClassCourse fkClassCourse = new FkClassCourse();
            fkClassCourse.setCourseId(courseId);
            fkClassCourse.setClassId(-1L);
            classList1.add(fkClassCourse);
        }
        BigDecimal count1 = new BigDecimal("0");
        if (CollectionUtils.isEmpty(checkInfoList)) {
            CheckInfo checkInfo = new CheckInfo();
            checkInfo.setCourseId(courseId);
            checkInfo.setId(0L);
            checkInfo.setRatio(new BigDecimal(0));
            checkInfoList.add(checkInfo);
        } else {
            for (CheckInfo checkInfo : checkInfoList) {
                checkInfo.setId(0L);
                checkInfo.setCourseId(courseId);
                count1 = count1.add(checkInfo.getRatio());
            }
        }
        flag1 = count1.compareTo(bigDecimal1);
        flag0 = count1.compareTo(bigDecimal0);
        if (flag1 == 0 || flag0 == 0) {

        } else {
            String s = new String("考核方式占比不归一");
            map.put("message", s);
            return map;
        }

        System.out.println(count1);
        if (CollectionUtils.isEmpty(targetInfoList)) {
            TargetInfo targetInfo = new TargetInfo();
            targetInfo.setId(0L);
            targetInfo.setName("");
            targetInfo.setNumber("");
            targetInfo.setContent("");
            targetInfo.setCourseId(courseId);
            targetInfo.setGraduateId(-1L);
            targetInfoList.add(targetInfo);
        } else {
            for (TargetInfo targetInfo : targetInfoList) {
                targetInfo.setCourseId(courseId);
                targetInfo.setId(0L);
            }
        }
        map.put("courseInfo", courseInfo);
        map.put("preList1", preList1);
        map.put("classList1", classList1);
        map.put("checkInfoList", checkInfoList);
        map.put("targetInfoList", targetInfoList);
        return map;
    }

    public HashMap<String, Object> modifyOneCourseInfo(CourseInfoForm courseInfoForm) {
        boolean flag;
        HashMap<String, Object> map = extractPost(courseInfoForm);
        HashMap<String, Object> result = new HashMap<>();
        if (map.containsKey("message")) {
            result.put("message", map.get("message"));
            result.put("flag", false);
            return result;
        }

        List<FkPre> preList1 = (ArrayList<FkPre>) map.get("preList1");
        List<FkClassCourse> classList1 = (ArrayList<FkClassCourse>) map.get("classList1");
        List<CheckInfo> checkInfoList = (ArrayList<CheckInfo>) map.get("checkInfoList");
        List<TargetInfo> targetInfoList = (ArrayList<TargetInfo>) map.get("targetInfoList");
        Long courseId = courseInfoForm.getId();
        CourseInfo courseInfo = (CourseInfo) map.get("courseInfo");

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

    public HashMap<String, Object> addOneCourseInfo(CourseInfoForm courseInfoForm) {
        boolean flag;
//        SnowFlake10 snowFlake = new SnowFlake10();
        courseInfoForm.setId(0L);
        HashMap<String, Object> map = extractPost(courseInfoForm);
        HashMap<String, Object> result = new HashMap<>();
        if (map.containsKey("message")) {
            result.put("message", map.get("message"));
            result.put("flag", false);
            return result;
        }
        List<FkPre> preList1 = (ArrayList<FkPre>) map.get("preList1");
        List<FkClassCourse> classList1 = (ArrayList<FkClassCourse>) map.get("classList1");
        List<CheckInfo> checkInfoList = (ArrayList<CheckInfo>) map.get("checkInfoList");
        List<TargetInfo> targetInfoList = (ArrayList<TargetInfo>) map.get("targetInfoList");
        CourseInfo courseInfo = (CourseInfo) map.get("courseInfo");

        flag = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            courseInfoMapper.insertAll(courseInfo);
            Long courseId = courseInfo.getId();
            preList1.forEach(item -> item.setId(courseId));
            classList1.forEach(item -> item.setCourseId(courseId));
            checkInfoList.forEach(item -> item.setCourseId(courseId));
            targetInfoList.forEach(item -> item.setCourseId(courseId));
            fkPreMapper.insertBatch(preList1);
            fkClassCourseMapper.insertBatch(classList1);
            checkInfoMapper.insertBatch(checkInfoList);
            targetInfoMapper.insertBatch(targetInfoList);
            return true;
        }));
        result.put("flag", flag);
        return result;

    }

    /**
     * @return java.util.List<com.allspark.uhelper.common.resp.StudentAndScoreResp>
     * @author 86159
     * @Description 返回成绩列表按照学生信息加成绩数组
     * @Date 22:11 2023/1/30
     * @Param [courseId]
     **/

    public List<StudentAndScoreResp> listAllStudent(Long courseId) {
        List<Long> ids = fkClassCourseMapper.selectClassIdByCourseId(courseId);
        List<StudentAndScoreResp> studentAndScoreRespList;
        List<StudentInfo> studentInfoList = studentInfoMapper.listAllByClassIdIn(ids);
        List<StudentScoreInfo> studentScoreInfoList = studentScoreInfoMapper.selectAllByCourseId(courseId);
        StudentAndScoreResp studentAndScoreResp1 = new StudentAndScoreResp();
        studentAndScoreResp1.setId(0L);
        studentAndScoreResp1.setClassId(0L);
        studentAndScoreResp1.setClassName("示例");
        studentAndScoreResp1.setName("示例");
        studentAndScoreResp1.setNumber("示例");
//        List<CheckInfo> checkInfoList = checkInfoMapper.selectAllByCourseId(courseId);
//        List<Integer> countList = fkCheckTargetMapper.selectTargetCountByCheckIdIn(checkInfoList.stream().map(CheckInfo::UuidUtils).collect(Collectors.toList()));
//        checkCount = countList.stream().reduce(Integer::sum).orElse(0);
        int usualScoreCount = 0;
        int finalCount = 0;
        int first1 = 0;
        int first2 = 0;
        int first3 = 0;
        int first4 = 0;
        List<TargetInfo> targetInfoList = targetInfoMapper.selectAllByCourseId(courseId);
        for (TargetInfo targetInfo : targetInfoList) {
            int i = 1;
//            HashMap<Integer,Integer[]> firstMap = new HashMap<>();
            List<FkTargetFinal> fkTargetFinals = fkTargetFinalMapper.selectAllByTargetId(targetInfo.getId());
            for (FkTargetFinal fkTargetFinal : fkTargetFinals) {
//                firstMap.put(fkTargetFinal.getFirst(), JSONUtil.parse(fkTargetFinal.getSecond()).toBean(Integer[].class));
                int firstCount = JSONUtil.parse(fkTargetFinal.getSecond()).toBean(Integer[].class).length;
                finalCount += firstCount;
                if (i == 1) {
                    first1 += firstCount;
                } else if (i == 2) {
                    first2 += firstCount;
                } else if (i == 3) {
                    first3 += firstCount;
                } else if (i == 4) {
                    first4 += firstCount;
                }
                i++;

            }
            for (HashMap hashMap : fkCheckTargetMapper.selectAllByTargetId(targetInfo.getId())) {
                int count = (Integer) hashMap.get("targetCount");
                usualScoreCount += count;
            }
        }
        Integer[] fullScore = new Integer[usualScoreCount];
        Integer[] emptyScore = new Integer[usualScoreCount];
        for (int i = 0; i < usualScoreCount; i++) {
            fullScore[i] = 100;
            emptyScore[i] = 0;
        }

        Integer[] fullScoreFinal = new Integer[finalCount];
        Integer[] emptyScoreFinal = new Integer[finalCount];
        for (int i = 0; i < finalCount; i++) {
            fullScoreFinal[i] = 100;
            emptyScoreFinal[i] = 0;
        }
        Integer[] full1 = new Integer[first1];
        Integer[] full2 = new Integer[first2];
        Integer[] full3 = new Integer[first3];
        Integer[] full4 = new Integer[first4];
        Arrays.fill(full1, 100);
        Arrays.fill(full2, 100);
        Arrays.fill(full3, 100);
        Arrays.fill(full4, 100);
        studentAndScoreResp1.setUsualScore(fullScore);
        studentAndScoreResp1.setFinalScore1(full1);
        studentAndScoreResp1.setFinalScore2(full2);
        studentAndScoreResp1.setFinalScore3(full3);
        studentAndScoreResp1.setFinalScore4(full4);
        studentAndScoreRespList = CopyUtil.copyList(studentInfoList, StudentAndScoreResp.class);
        studentAndScoreRespList.add(0, studentAndScoreResp1);
        HashMap<Long, StudentScoreInfo> map = new HashMap<>();
        for (StudentScoreInfo studentScoreInfo : studentScoreInfoList) {
            map.put(studentScoreInfo.getId(), studentScoreInfo);
        }
        int i = 0;
        for (StudentAndScoreResp studentAndScoreResp : studentAndScoreRespList) {
            if (i == 0) {
                i = 1;
                continue;
            }
            StudentScoreInfo studentScoreInfo = new StudentScoreInfo();
            if (!map.containsKey(studentAndScoreResp.getId())) {
                studentScoreInfo.setUsualScore(emptyScore);
                studentScoreInfo.setFinalScore(emptyScoreFinal);
                studentScoreInfo.setId(studentAndScoreResp.getId());
                studentScoreInfo.setCourseId(courseId);
            } else {
                studentScoreInfo = map.get(studentAndScoreResp.getId());
            }
            studentAndScoreResp.setUsualScore(JSONUtil.parse(studentScoreInfo.getUsualScore()).toBean(Integer[].class));
            Integer[] integers = JSONUtil.parse(studentScoreInfo.getFinalScore()).toBean(Integer[].class);
            Integer[] finalScore1 = Arrays.copyOfRange(integers, 0, first1);
            Integer[] finalScore2 = Arrays.copyOfRange(integers, first1, first1 + first2);
            Integer[] finalScore3 = Arrays.copyOfRange(integers, first1 + first2, first1 + first2 + first3);
            Integer[] finalScore4 = Arrays.copyOfRange(integers, first1 + first2 + first3, first1 + first2 + first3 + first4);
            studentAndScoreResp.setFinalScore1(finalScore1);
            studentAndScoreResp.setFinalScore1(finalScore2);
            studentAndScoreResp.setFinalScore1(finalScore3);
            studentAndScoreResp.setFinalScore1(finalScore4);
            studentAndScoreResp.setClassName(classInfoMapper.selectNameById(studentAndScoreResp.getClassId()));
        }
        return studentAndScoreRespList;
    }

    /**
     * @return java.util.List<com.allspark.uhelper.common.resp.StudentUsualScoreResp>
     * @author 86159
     * @Description 查询学生平时成绩列表到每个考核方式
     * @Date 16:59 2023/1/29
     * @Param [courseId]
     **/

//    public List<StudentUsualScoreResp> listAllStudentUsualScore(Long courseId){
//        List<Long> ids = fkClassCourseMapper.selectClassIdByCourseId(courseId);
//        List<StudentUsualScoreResp>  studentUsualScoreRespList;
//        List<StudentInfo> studentInfoList = studentInfoMapper.listAllByClassIdIn(ids);
//        HashMap<String,Integer[]> map = new HashMap<>();
//        List<StudentUsualScore> studentUsualScoreList = studentUsualScoreMapper.selectAllByCourseId(courseId);
//        for (StudentUsualScore studentUsualScore : studentUsualScoreList) {
//            String flag = studentUsualScore.getTargetId().toString()+studentUsualScore.getCheckId()+studentUsualScore.UuidUtils().toString();
//            map.put(flag,JSONUtil.parse(studentUsualScore.getUsualScore()).toBean(Integer[].class));
//            System.out.println(flag);
//        }
//        studentUsualScoreRespList = CopyUtil.copyList(studentInfoList, StudentUsualScoreResp.class);
//        List<TargetInfo> targetInfoList = targetInfoMapper.selectAllByCourseId(courseId);
//        for (StudentUsualScoreResp studentUsualScoreResp : studentUsualScoreRespList) {
//            HashMap<Long,HashMap<Long,Integer[]>> usualMap = new HashMap();
//            for (TargetInfo targetInfo : targetInfoList) {
//                usualMap.put(targetInfo.UuidUtils(),new HashMap<>());
//                for (Long checkId : fkCheckTargetMapper.selectCheckIdByTargetId(targetInfo.UuidUtils())) {
//                    String flag = targetInfo.UuidUtils().toString() +checkId.toString()+studentUsualScoreResp.UuidUtils();
//                    System.out.println(flag);
//                    usualMap.get(targetInfo.UuidUtils()).put(checkId,map.get(flag));
//                }
//            }
//            studentUsualScoreResp.setUsual(usualMap);
//        }
//        return studentUsualScoreRespList;
//    }
    public boolean modifyAllStudent(StudentAndScoreListForm form) {
        boolean flag;
        Long courseId = form.getCourseID();
        List<StudentAndScoreForm> studentAndScoreFormList = form.getStudentAndScoreFormList();
        List<StudentScoreInfo> studentScoreInfoList = CopyUtil.copyList(studentAndScoreFormList, StudentScoreInfo.class);
        int i = 0;
        for (StudentAndScoreForm studentAndScoreForm : studentAndScoreFormList) {
            Integer[] final1 = studentAndScoreForm.getFinalScore1();
            Integer[] final2 = studentAndScoreForm.getFinalScore2();
            Integer[] final3 = studentAndScoreForm.getFinalScore3();
            Integer[] final4 = studentAndScoreForm.getFinalScore4();
            List<Integer> temp = new ArrayList<>(Arrays.asList(final1));
            temp.addAll(Arrays.asList(final2));
            temp.addAll(Arrays.asList(final3));
            temp.addAll(Arrays.asList(final4));
            Integer[] finalScore = temp.toArray(new Integer[temp.size()]);
            studentScoreInfoList.get(i).setFinalScore(finalScore);
            i++;
        }
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
            HashMap<Integer, Integer[]> firstMap = new HashMap<>();
            List<FkTargetFinal> fkTargetFinals = fkTargetFinalMapper.selectAllByTargetId(targetInfo.getId());
            for (FkTargetFinal fkTargetFinal : fkTargetFinals) {
                firstMap.put(fkTargetFinal.getFirst(), JSONUtil.parse(fkTargetFinal.getSecond()).toBean(Integer[].class));
            }
            targetAndFinalForm.setFirst1(firstMap.get(1));
            targetAndFinalForm.setFirst2(firstMap.get(2));
            targetAndFinalForm.setFirst3(firstMap.get(3));
            targetAndFinalForm.setFirst4(firstMap.get(4));
            targetAndFinalFormList.add(targetAndFinalForm);
        }
        finalScoreResp.setTargetAndFinalFormList(targetAndFinalFormList);
        return finalScoreResp;
    }

    public boolean modifyFinal(FinalScoreForm form) {
        boolean flag;
        Long courseId = form.getCourseId();
        List<TargetAndFinalForm> targetAndFinalFormList = form.getTargetAndFinalFormList();
        List<FkTargetFinal> fkTargetFinalList = new ArrayList<>();
        for (TargetAndFinalForm targetAndFinalForm : targetAndFinalFormList) {
            for (int i = 1; i <= 4; i++) {
                FkTargetFinal fkTargetFinal = new FkTargetFinal();
                if (i == 1) {
                    fkTargetFinal.setTargetId(targetAndFinalForm.getTargetId());
                    fkTargetFinal.setFirst(1);
                    fkTargetFinal.setSecond(JSONUtil.parse(targetAndFinalForm.getFirst1()).toBean(String.class));
                } else if (i == 2) {
                    fkTargetFinal.setFirst(2);
                    fkTargetFinal.setSecond(JSONUtil.parse(targetAndFinalForm.getFirst2()).toBean(String.class));
                } else if (i == 3) {
                    fkTargetFinal.setFirst(3);
                    fkTargetFinal.setSecond(JSONUtil.parse(targetAndFinalForm.getFirst3()).toBean(String.class));
                } else {
                    fkTargetFinal.setFirst(4);
                    fkTargetFinal.setSecond(JSONUtil.parse(targetAndFinalForm.getFirst4()).toBean(String.class));
                }
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

    public ExcelWriter downLoadUsual(Long courseId) {
        double usualRatio = courseInfoMapper.selectAllById(courseId).getUsualRatio().doubleValue();
        List<Long> ids = fkClassCourseMapper.selectClassIdByCourseId(courseId);
        List<StudentAndScoreResp> studentAndScoreRespList;
        List<StudentInfo> studentInfoList = studentInfoMapper.listAllByClassIdIn(ids);
        List<StudentScoreInfo> studentScoreInfoList = studentScoreInfoMapper.selectAllByCourseId(courseId);
        StudentAndScoreResp studentAndScoreResp1 = new StudentAndScoreResp();
        studentAndScoreResp1.setId(0L);
        studentAndScoreResp1.setClassId(0L);
        studentAndScoreResp1.setClassName("示例");
        studentAndScoreResp1.setName("示例");
        studentAndScoreResp1.setNumber("示例");
        List tableHeader1 = new ArrayList();
        int usualScoreCount = 0;
        List<TargetInfo> targetInfoList = targetInfoMapper.selectAllByCourseId(courseId);
        for (TargetInfo targetInfo : targetInfoList) {
            for (HashMap hashMap : fkCheckTargetMapper.selectAllByTargetId(targetInfo.getId())) {
                int count = (Integer) hashMap.get("targetCount");
                usualScoreCount += count;
            }
        }
        Integer[] fullScore = new Integer[usualScoreCount];
        Integer[] emptyScore = new Integer[usualScoreCount];
        for (int i = 0; i < usualScoreCount; i++) {
            fullScore[i] = 100;
            emptyScore[i] = 0;
        }
        studentAndScoreResp1.setUsualScore(fullScore);
        studentAndScoreRespList = CopyUtil.copyList(studentInfoList, StudentAndScoreResp.class);
        StudentAndScoreResp studentAndScoreResp0 = new StudentAndScoreResp();
        studentAndScoreRespList.add(0, studentAndScoreResp0);
        studentAndScoreRespList.add(1, studentAndScoreResp1);
        HashMap<Long, StudentScoreInfo> map = new HashMap<>();
        for (StudentScoreInfo studentScoreInfo : studentScoreInfoList) {
            map.put(studentScoreInfo.getId(), studentScoreInfo);
        }
        int i = 0;
        List usualScoreList = new ArrayList();
        tableHeader1.add("班级");
        tableHeader1.add("学号");
        tableHeader1.add("姓名");
        for (StudentAndScoreResp studentAndScoreResp : studentAndScoreRespList) {
            List usualSore = new ArrayList();
            if (i == 0) {
                usualSore.add("");
                usualSore.add("");
                usualSore.add("");
                int scoreListIndex = 3;
                for (TargetInfo targetInfo : targetInfoList) {
                    tableHeader1.add(targetInfo.getName());
                    for (HashMap map1 : fkCheckTargetMapper.selectAllByTargetId(targetInfo.getId())) {
                        int count = (Integer) map1.get("targetCount");
                        String checkName = (String) map1.get("checkName");
                        for (int j = scoreListIndex, k = 1; j < count + scoreListIndex; j++, k++) {
                            usualSore.add(checkName + k);
                            tableHeader1.add("---");
                        }
                        scoreListIndex += count;
                        usualSore.add("该方式总分");
                        usualSore.add("该方式得分");
                        tableHeader1.add("---");
                        tableHeader1.add("---");
                        scoreListIndex += 2;
                    }
                    usualSore.add("该目标总分");
                    usualSore.add("该目标得分");
                    tableHeader1.add(targetInfo.getName());
                }
                usualSore.add("总分");
                usualSore.add("得分");
                i += 1;
                usualScoreList.add(usualSore);
                tableHeader1.add("平时成绩");
                tableHeader1.add("平时成绩");
                continue;

            }
            StudentScoreInfo studentScoreInfo = new StudentScoreInfo();
            if (i == 1) {
                studentScoreInfo.setFinalScore(fullScore);
                studentScoreInfo.setUsualScore(fullScore);
                studentScoreInfo.setId(studentAndScoreResp.getId());
                studentScoreInfo.setCourseId(courseId);
                i += 1;
            } else if (!map.containsKey(studentAndScoreResp.getId())) {
                studentScoreInfo.setUsualScore(emptyScore);
                studentScoreInfo.setFinalScore(emptyScore);
                studentScoreInfo.setId(studentAndScoreResp.getId());
                studentScoreInfo.setCourseId(courseId);
            } else {
                studentScoreInfo = map.get(studentAndScoreResp.getId());
            }
            Integer[] integers = JSONUtil.parse(studentScoreInfo.getUsualScore()).toBean(Integer[].class);
            Collections.addAll(usualSore, integers);
            int scoreListIndex = 0;
            double usualAllScore = 0;
            double usualAllReScore = 0;
            for (TargetInfo targetInfo : targetInfoList) {
                double targetAllScore = 0;
                double targetAllReScore = 0;
                for (HashMap map1 : fkCheckTargetMapper.selectAllByTargetId(targetInfo.getId())) {
                    int count = (Integer) map1.get("targetCount");
                    BigDecimal checkRatio = (BigDecimal) checkInfoMapper.selectRatioById((Long) map1.get("checkId"));
                    BigDecimal thisRatio = (BigDecimal) map1.get("targetRatio");
                    int sum = 0;
                    for (int j = scoreListIndex; j < count + scoreListIndex; j++) {
                        sum += (int) usualSore.get(j);
                    }
                    scoreListIndex += count;
                    double avg = sum / count;
                    double allScore = checkRatio.doubleValue() * thisRatio.doubleValue() * avg;
                    double reallyScore = allScore * usualRatio;
                    usualSore.add(scoreListIndex, allScore);
                    usualSore.add(scoreListIndex + 1, reallyScore);
                    scoreListIndex += 2;
                    targetAllScore += allScore;
                    targetAllReScore += reallyScore;
                }
                usualSore.add(scoreListIndex, targetAllScore);
                usualSore.add(scoreListIndex + 1, targetAllReScore);
                usualAllReScore += targetAllReScore;
                usualAllScore += targetAllScore;
                scoreListIndex += 2;
            }
            usualSore.add(usualAllScore);
            usualSore.add(usualAllReScore);
            usualSore.add(0, classInfoMapper.selectNameById(studentAndScoreResp.getClassId()));
            usualSore.add(1, studentAndScoreResp.getNumber());
            usualSore.add(2, studentAndScoreResp.getName());
            usualScoreList.add(usualSore);
        }
        usualScoreList.add(0, tableHeader1);
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 合并单元格后的标题行，使用默认标题样式
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(usualScoreList, true);
        return writer;
    }

    public ExcelWriter downloadFinal(Long courseId) {
        List<Long> ids = fkClassCourseMapper.selectClassIdByCourseId(courseId);
        List<StudentAndScoreResp> studentAndScoreRespList;
        List<StudentInfo> studentInfoList = studentInfoMapper.listAllByClassIdIn(ids);
        List<StudentScoreInfo> studentScoreInfoList = studentScoreInfoMapper.selectAllByCourseId(courseId);
        StudentAndScoreResp studentAndScoreResp1 = new StudentAndScoreResp();
        studentAndScoreResp1.setId(0L);
        studentAndScoreResp1.setClassId(0L);
        studentAndScoreResp1.setClassName("示例");
        studentAndScoreResp1.setName("示例");
        studentAndScoreResp1.setNumber("示例");

        List<List> finalScoreList = new ArrayList<>();
        int finalCount = 0;
        int first1 = 0;
        int first2 = 0;
        int first3 = 0;
        int first4 = 0;
        List finalScore1 = new ArrayList();
        finalScore1.add("");
        finalScore1.add("");
        finalScore1.add("");
        List finalScore3 = new ArrayList();
        finalScore3.add("班级");
        finalScore3.add("学号");
        finalScore3.add("姓名");
        List<TargetInfo> targetInfoList = targetInfoMapper.selectAllByCourseId(courseId);
        for (TargetInfo targetInfo : targetInfoList) {
            int i = 1;
            List<FkTargetFinal> fkTargetFinals = fkTargetFinalMapper.selectAllByTargetId(targetInfo.getId());
            for (FkTargetFinal fkTargetFinal : fkTargetFinals) {
                int firstCount = JSONUtil.parse(fkTargetFinal.getSecond()).toBean(Integer[].class).length;
                finalCount += firstCount;
                if (i == 1) {
                    first1 += firstCount;
                } else if (i == 2) {
                    first2 += firstCount;
                } else if (i == 3) {
                    first3 += firstCount;
                } else if (i == 4) {
                    first4 += firstCount;
                }
                i++;

            }
        }
        String[] targetAndFinal = new String[finalCount + 3];
        targetAndFinal[0] = "";
        targetAndFinal[1] = "";
        targetAndFinal[2] = "";
        for (TargetInfo targetInfo : targetInfoList) {
            int i = 1;
            for (FkTargetFinal fkTargetFinal : fkTargetFinalMapper.selectAllByTargetId(targetInfo.getId())) {
                Integer[] integers = JSONUtil.parse(fkTargetFinal.getSecond()).toBean(Integer[].class);
                for (Integer integer : integers) {
                    if (i == 1) {
                        targetAndFinal[integer + 2] = targetInfo.getNumber();
                    } else if (i == 2) {
                        targetAndFinal[integer + 2 + first1] = targetInfo.getNumber();
                    } else if (i == 3) {
                        targetAndFinal[integer + 2 + first2 + first1] = targetInfo.getNumber();
                    } else if (i == 4) {
                        targetAndFinal[integer + 2 + first1 + first2 + first3] = targetInfo.getNumber();
                    }
                }
                i++;
            }
        }
        List finalScore2 = new ArrayList(Arrays.asList(targetAndFinal));
        for (int i = 1; i <= finalCount; i++) {
            if (i == 1) {
                finalScore1.add("第一题");
                finalScore3.add(1);
                continue;
            } else if (i <= first1 && i > 1) {

                finalScore3.add(i);
                finalScore1.add("");
            } else if (i <= first1 + first2 && i > first1) {
                if (i == first1 + 1) {
                    finalScore1.add("第二题");
                    finalScore3.add(1);
                    continue;
                }

                finalScore3.add(i - first1);
                finalScore1.add("");
            } else if (i <= first1 + first2 + first3 && i > first1 + first2) {
                if (i == first1 + first2 + 1) {
                    finalScore1.add("第三题");
                    finalScore3.add(1);
                    continue;
                }
                finalScore3.add(i - first1 - first2);
                finalScore1.add("");
            } else {
                if (i == first1 + first2 + first3 + 1) {
                    finalScore1.add("第四题");
                    finalScore3.add(1);
                    continue;
                }
                finalScore3.add(i - first1 - first2 - first3);
            }
        }
        finalScoreList.add(finalScore1);
        finalScoreList.add(finalScore2);
        finalScoreList.add(finalScore3);
        Integer[] fullScoreFinal = new Integer[finalCount];
        Integer[] emptyScoreFinal = new Integer[finalCount];
        for (int i = 0; i < finalCount; i++) {
            fullScoreFinal[i] = 100;
            emptyScoreFinal[i] = 0;
        }
        studentAndScoreRespList = CopyUtil.copyList(studentInfoList, StudentAndScoreResp.class);
        studentAndScoreRespList.add(0, studentAndScoreResp1);
        HashMap<Long, StudentScoreInfo> map = new HashMap<>();
        for (StudentScoreInfo studentScoreInfo : studentScoreInfoList) {
            map.put(studentScoreInfo.getId(), studentScoreInfo);
        }
        int i = 0;
        for (StudentAndScoreResp studentAndScoreResp : studentAndScoreRespList) {
            StudentScoreInfo studentScoreInfo = new StudentScoreInfo();
            if (i == 0) {
                studentScoreInfo.setFinalScore(fullScoreFinal);
                i = 1;
            } else if (!map.containsKey(studentAndScoreResp.getId())) {
                studentScoreInfo.setFinalScore(emptyScoreFinal);
                studentScoreInfo.setId(studentAndScoreResp.getId());
                studentScoreInfo.setCourseId(courseId);
            } else {
                studentScoreInfo = map.get(studentAndScoreResp.getId());
            }
            Integer[] integers = JSONUtil.parse(studentScoreInfo.getFinalScore()).toBean(Integer[].class);
            List finalScore = new ArrayList(Arrays.asList(integers));
            finalScore.add(0, classInfoMapper.selectNameById(studentAndScoreResp.getClassId()));
            finalScore.add(1, studentAndScoreResp.getNumber());
            finalScore.add(2, studentAndScoreResp.getName());
            finalScoreList.add(finalScore);
        }
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 合并单元格后的标题行，使用默认标题样式
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(finalScoreList, true);
        return writer;
    }

    public Document downloadReport(Long courseId) {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(courseId);
        List<CourseInfo> courseInfos = listByIds(ids);
        List<CourseInfoResp> courseInfoRespList = listCourseInfoResp(courseInfos);
        CourseInfoResp courseInfo = courseInfoRespList.get(0);
        Document document = new Document("D:\\uhelperTest\\" + "模板" + ".docx");
        Map<String, String> docMap = new HashMap<>();
        docMap.put("${courseName}", courseInfo.getName());
        docMap.put("${college}", courseInfo.getCollege().getName());
        docMap.put("${unit}", courseInfo.getUnit());
        docMap.put("${teacher}", courseInfo.getTeacher());
        docMap.put("${name}", courseInfo.getName());
        docMap.put("${nature}", courseInfo.getNature().getName());
        docMap.put("${number}", courseInfo.getNumber());
        docMap.put("${allPeriod}", courseInfo.getAllPeriod().toString());
        docMap.put("${theoryPeriod}", courseInfo.getTheoryPeriod().toString());
        docMap.put("${runPeriod}", courseInfo.getRunPeriod().toString());
        docMap.put("${score}", courseInfo.getScore().toString());
        List<CourseInfo> preCourseInfo = courseInfoMapper.selectAllByIdIn(courseInfo.getPreList());
        String preList = new String();
        for (int i = 0; i < preCourseInfo.size(); i++) {
            if (i == 0) {
                preList = preCourseInfo.get(i).getName();
            } else {
                preList = preList + "，" + preCourseInfo.get(i).getName();
            }
        }
        docMap.put("${preList}", preList);
        String classList = new String();
        Integer studentCount = 0;
        List<Long> longs = fkClassCourseMapper.selectClassIdByCourseId(courseId);
        int i = 0;
        for (ClassInfo selectBatchId : classInfoMapper.selectBatchIds(longs)) {
            studentCount += selectBatchId.getHeadcount();
            if (i == 0) {
                i = 1;
                classList = selectBatchId.getProfessional() + selectBatchId.getName();
            } else {
                classList = classList + "，" + selectBatchId.getProfessional() + selectBatchId.getName();
            }
        }
        docMap.put("${classList}", classList);
        docMap.put("${studentNum}", studentCount.toString());
        docMap.put("${semester}", courseInfo.getSemester().getName());
        int j = 1;
        int targetCount = 0;
        String courseTarget = new String("");
        List<Map<String, String>> targetTableList = new ArrayList<>();
        for (TargetInfo targetInfo : targetInfoMapper.selectAllByCourseId(courseId)) {
            String name = targetInfo.getName();
            String number = targetInfo.getNumber();
            String content = targetInfo.getContent();
            GraduateTargetInfo graduateTargetInfo = graduateTargetInfoMapper.selectById(targetInfo.getGraduateId());
            if (graduateTargetInfo == null) {
                graduateTargetInfo = new GraduateTargetInfo();
                graduateTargetInfo.setName("无");
                graduateTargetInfo.setContent("无");
            }
            String graduateName = graduateTargetInfo.getName();
            courseTarget = courseTarget + j + "." + name + "（" + number + "）" + "：" + content + "（" + "支撑毕业要求" + graduateName + "）" + "\n\t ";
            HashMap<String, String> targetHashMap = new HashMap<>();
            targetHashMap.put(name, graduateName + graduateTargetInfo.getContent());
            targetTableList.add(targetHashMap);
            j++;
            targetCount++;
        }
        HashMap<String, String> targetTableHeadHashMap = new HashMap<>();
        targetTableHeadHashMap.put("课程目标", "支撑的毕业要求指标点");
        targetTableList.add(0, targetTableHeadHashMap);
        docMap.put("${courseTarget}", courseTarget);
        docMap.put("${usualRatio}", courseInfo.getUsualRatio().doubleValue() * 100 + "");
        String checkList = new String();
        int c = 0;
        for (CheckInfo checkInfo : checkInfoMapper.selectAllByCourseId(courseId)) {
            if (c == 0) {
                checkList = checkInfo.getName() + "*" + checkInfo.getRatio().doubleValue() * 100 + "%";
                c = 1;
            } else {
                checkList = checkList + "+" + checkInfo.getName() + "*" + checkInfo.getRatio().doubleValue() * 100 + "%";
            }

        }
        docMap.put("${checkList}", checkList);
        docMap.put("${finalRatio}", courseInfo.getFinalRatio().doubleValue() * 100 + "");
        docMap.forEach((k, v) -> {
            document.replace(k, v, true, false);
        });
        //table
        Section section = document.getSections().get(0);
        Table targetTable = section.addTable(true);
        targetTable.resetCells(targetCount + 1, 2);
        int i1 = 0;
        for (Map<String, String> stringStringMap : targetTableList) {
            Set<Map.Entry<String, String>> entries = stringStringMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                TableRow tableRow = targetTable.getRows().get(i1);
                TextRange range1 = tableRow.getCells().get(0).addParagraph().appendText(entry.getKey());
                range1.getCharacterFormat().setFontName("华文楷体");
                TextRange range2 = tableRow.getCells().get(1).addParagraph().appendText(entry.getValue());
                range2.getCharacterFormat().setFontName("华文楷体");
                i1++;
            }
        }
        return document;
    }
}




