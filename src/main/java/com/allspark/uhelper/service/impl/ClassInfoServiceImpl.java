package com.allspark.uhelper.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.allspark.uhelper.common.form.ClassAndStudentForm;
import com.allspark.uhelper.common.form.ClassInfoForm;
import com.allspark.uhelper.common.form.StudentInfoForm;
import com.allspark.uhelper.common.resp.classTree.NAryTree;
import com.allspark.uhelper.db.mapper.StudentInfoMapper;
import com.allspark.uhelper.db.pojo.StudentDbInfo;
import com.allspark.uhelper.db.pojo.StudentInfo;
import com.allspark.uhelper.myenum.CollegeEnum;
import com.allspark.uhelper.utils.UuidUtils;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.ClassInfo;
import com.allspark.uhelper.service.ClassInfoService;
import com.allspark.uhelper.db.mapper.ClassInfoMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 86159
 * @description 针对表【class_info(班级的基本信息表)】的数据库操作Service实现
 * @createDate 2023-01-17 02:28:46
 */
@Service
@DS("u_classinfo")
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo>
        implements ClassInfoService {

    @Resource
    ClassInfoMapper classInfoMapper;

    @Resource
    StudentInfoMapper studentInfoMapper;

//    public List<NAryTree> listAllClass(){
//        List<NAryTree> nAryTrees = new LinkedList<>();
//        List<ClassInfo> list = list();
//        Set<CollegeEnum> flag=  new HashSet<>();
//        Set<String> flag1 = new HashSet<>();
//        for (ClassInfo classInfo : list) {
//            CollegeEnum collegeEnum =  classInfo.getCollege();
//            if (!flag.contains(collegeEnum)) {
//                flag.add(collegeEnum);
//                NAryTree nAryTree = new NAryTree();
//                nAryTree.setValue(collegeEnum.getCode().longValue());
//                nAryTree.setLabel(collegeEnum.getName());
//                LinkedList<NAryTree> nAryChildTrees = new LinkedList<>();
//                nAryTree.setChildren(nAryChildTrees);
//                nAryTrees.add(nAryTree);
//            }
//        }
//        long i=1;
//        for (NAryTree nAryTree : nAryTrees) {
//            for (ClassInfo classInfo : list) {
//                if (nAryTree.getLabel().equals(classInfo.getCollege().getName())) {
//                    String proName = classInfo.getProfessional();
//                    if (!flag1.contains(proName)) {
//                        flag1.add(proName);
//                        NAryTree nAryTree1 = new NAryTree();
//                        nAryTree1.setValue(i);
//                        nAryTree1.setLabel(proName);
//                        LinkedList<NAryTree> nAryChildTrees = new LinkedList<>();
//                        nAryTree1.setChildren(nAryChildTrees);
//                        nAryTree.getChildren().add(nAryTree1);
//                        i++;
//                    }
//                }
//            }
//        }
//        for (NAryTree collegeTree : nAryTrees) {
//            for (NAryTree professionalTree : collegeTree.getChildren()) {
//                for (ClassInfo classInfo : list) {
//                    if (collegeTree.getLabel().equals(classInfo.getCollege().getName())&&professionalTree.getLabel().equals(classInfo.getProfessional())) {
//                        NAryTree classTree = new NAryTree();
//                        classTree.setValue(classInfo.UuidUtils());
//                        classTree.setLabel(classInfo.getName().toString());
//                        professionalTree.getChildren().add(classTree);
//                    }
//                }
//            }
//        }
//
//        return  nAryTrees;
//    }

    public List<NAryTree> listAll3(){
        List<NAryTree> collegeTrees = new LinkedList<>();
        HashMap<CollegeEnum,NAryTree> collegeHash = new HashMap<>();
        HashMap<String,NAryTree> proHash = new HashMap<>();
        List<ClassInfo> classInfoList = list();
        int i=0;
        for (ClassInfo classInfo : classInfoList) {
            i++;
            CollegeEnum collegeEnum =  classInfo.getCollege();
            if (!collegeHash.containsKey(collegeEnum)) {
                NAryTree collegeTree = new NAryTree();
                collegeTree.setLabel(collegeEnum.getName());
                collegeTree.setValue(collegeEnum.getCode().longValue());
                ArrayList<NAryTree> collegeTreeChild = new ArrayList<>();
                NAryTree proTree = new NAryTree();
                proTree.setLabel(classInfo.getProfessional());
                proTree.setValue((long)i);
                ArrayList<NAryTree> proTreeChild = new ArrayList<>();
                NAryTree classTree = new NAryTree();
                classTree.setValue(classInfo.getId());
                classTree.setLabel(classInfo.getName().toString());
                proTreeChild.add(classTree);
                proTree.setChildren(proTreeChild);
                collegeTreeChild.add(proTree);
                collegeTree.setChildren(collegeTreeChild);
                collegeTrees.add(collegeTree);
                collegeHash.put(collegeEnum, collegeTree);
                proHash.put(classInfo.getProfessional(), proTree);
            } else {
                if (!proHash.containsKey(classInfo.getProfessional())) {
                    NAryTree collegeTree = collegeHash.get(collegeEnum);
                    NAryTree proTree = new NAryTree();
                    proTree.setLabel(classInfo.getProfessional());
                    proTree.setValue((long)i);
                    ArrayList<NAryTree> proTreeChild = new ArrayList<>();
                    NAryTree classTree = new NAryTree();
                    classTree.setValue(classInfo.getId());
                    classTree.setLabel(classInfo.getName().toString());
                    proTreeChild.add(classTree);
                    proTree.setChildren(proTreeChild);
                    collegeTree.getChildren().add(proTree);
                    proHash.put(classInfo.getProfessional(), proTree);
                } else {
                    NAryTree proTree = proHash.get(classInfo.getProfessional());
                    NAryTree classTree = new NAryTree();
                    classTree.setValue(classInfo.getId());
                    classTree.setLabel(classInfo.getName().toString());
                    proTree.getChildren().add(classTree);
                }
            }
        }
        return collegeTrees;
    }


    @Override
    public StudentDbInfo transferStudentDb(StudentInfoForm studentInfoForm) {
        StudentDbInfo studentInfo = new StudentDbInfo();
        studentInfo.setName(studentInfoForm.getName());
        studentInfo.setClassId(studentInfoForm.getClassId());
        studentInfo.setNumber(studentInfoForm.getNumber());
        studentInfo.setIndex(studentInfoForm.getIndex());
        return studentInfo;
    }

    @Override
    public StudentInfo transferStudent(StudentInfoForm studentInfoForm) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(studentInfoForm.getId());
        studentInfo.setName(studentInfoForm.getName());
        studentInfo.setClassId(studentInfoForm.getClassId());
        studentInfo.setNumber(studentInfoForm.getNumber());
        studentInfo.setIndex(studentInfoForm.getIndex());
        return studentInfo;
    }

    @Override
    public ClassInfo transferClass(ClassAndStudentForm classAndStudentForm) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setId(classAndStudentForm.getId());
        classInfo.setGrade(classAndStudentForm.getGrade());
        classInfo.setName(classAndStudentForm.getName());
        classInfo.setCollege(classAndStudentForm.getCollege());
        classInfo.setProfessional(classAndStudentForm.getProfessional());
        classInfo.setHeadcount(classAndStudentForm.getHeadcount());
        classInfo.setUserId(classAndStudentForm.getUserId());
        classInfo.setCollegeDB(classAndStudentForm.getCollege().getCode());
        return classInfo;
    }

    @Override
    public ClassInfo transferClass(ClassInfoForm classInfoForm) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setId(classInfoForm.getId());
        classInfo.setGrade(classInfoForm.getGrade());
        classInfo.setName(classInfoForm.getName());
        classInfo.setCollege(classInfoForm.getCollege());
        classInfo.setProfessional(classInfoForm.getProfessional());
        classInfo.setHeadcount(classInfoForm.getHeadcount());
        classInfo.setUserId(classInfoForm.getUserId());
        classInfo.setCollegeDB(classInfoForm.getCollege().getCode());
        return classInfo;
    }

    @Override
    public void insetClass(ClassInfo classInfo) {
        classInfo.setId(UuidUtils.getUuId());
        classInfoMapper.insetClass(classInfo);
    }

    @Override
    public boolean deleteClass(Long id) {
        boolean b = classInfoMapper.deleteClass(id);
        return b;
    }

    @Override
    public void updateClass(ClassInfo classInfo) {
        classInfoMapper.updateClass(classInfo);
    }

    @Override
    public ArrayList<HashMap> selectClass() {
        ArrayList<HashMap> arrayList = classInfoMapper.selectClass();
        ArrayList<HashMap> list = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); ++i) {
            HashMap hashMap = arrayList.get(i);
            int course = classInfoMapper.selectCourse((long) hashMap.get("id"));
            String college = CollegeEnum.getEnumType((Integer) hashMap.get("college"));
            hashMap.put("userId", hashMap.get("user_id"));
            hashMap.remove("user_id");
            hashMap.put("college", college);
            hashMap.put("courseCount", course);
            list.add(hashMap);
        }
        return list;
    }

    @Override
    public void insertStudent(StudentInfo studentInfo) {
        studentInfoMapper.insertStudent(studentInfo);
    }

    @Override
    public HashMap selectClassById(long id) {
        HashMap hashMap = classInfoMapper.selectClassById(id);
        return hashMap;
    }

    @Override
    public ArrayList<StudentInfo> uploadExcel(MultipartFile uploadFile) throws IOException {
        final ArrayList<StudentInfo>[] arrayList = new ArrayList[]{new ArrayList<>()};
        if (!uploadFile.isEmpty()) {

            InputStream inputStream = uploadFile.getInputStream();
            EasyExcel.read(inputStream, StudentInfo.class, new PageReadListener<StudentInfo>(dataList -> {
                for (StudentInfo studentInfo : dataList) {
                    //studentInfo.setClassId(classId);
                    // System.out.println(studentInfo);
                    // studentInfoMapper.insertStudent(studentInfo);
                    arrayList[0] = (ArrayList<StudentInfo>) dataList;
                }

            })).sheet().doRead();
        }
        return arrayList[0];
    }


}