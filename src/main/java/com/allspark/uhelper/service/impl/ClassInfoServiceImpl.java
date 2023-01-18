package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.resp.classTree.ClassTree;
import com.allspark.uhelper.common.resp.classTree.CollegeTree;
import com.allspark.uhelper.common.resp.classTree.ProfessionalTree;
import com.allspark.uhelper.myenum.CollegeEnum;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.ClassInfo;
import com.allspark.uhelper.service.ClassInfoService;
import com.allspark.uhelper.db.mapper.ClassInfoMapper;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author 86159
* @description 针对表【class_info(班级的基本信息表)】的数据库操作Service实现
* @createDate 2023-01-17 02:28:46
*/
@Service
@DS("u_classinfo")
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo>
    implements ClassInfoService{


    public Set<CollegeTree> listAllClass(){
        Set<CollegeTree> collegeTrees = new HashSet<>();
        List<ClassInfo> list = list();
        Set<CollegeEnum> flag=  new HashSet<>();
        Set<String> flag1 = new HashSet<>();
        for (ClassInfo classInfo : list) {
            CollegeEnum collegeEnum =  classInfo.getCollege();
            if (!flag.contains(collegeEnum)) {
                flag.add(collegeEnum);
                CollegeTree collegeTree = new CollegeTree();
                collegeTree.setCollegeEnum(collegeEnum);
                Set<ProfessionalTree> professionalTrees = new HashSet<>();
                collegeTree.setProfessionalTree(professionalTrees);
                collegeTrees.add(collegeTree);
            }
        }
        for (CollegeTree collegeTree : collegeTrees) {
            for (ClassInfo classInfo : list) {
                if (collegeTree.getCollegeEnum().equals(classInfo.getCollege())) {
                    String proName = classInfo.getProfessional();
                    if (!flag1.contains(proName)) {
                        flag1.add(proName);
                        ProfessionalTree professionalTree = new ProfessionalTree();
                        professionalTree.setProfessionalName(proName);
                        Set<ClassTree> classTrees = new HashSet<>();
                        professionalTree.setClassTrees(classTrees);
                        collegeTree.getProfessionalTree().add(professionalTree);
                    }
                }
            }
        }
        for (CollegeTree collegeTree : collegeTrees) {
            for (ProfessionalTree professionalTree : collegeTree.getProfessionalTree()) {
                for (ClassInfo classInfo : list) {
                    if (collegeTree.getCollegeEnum().equals(classInfo.getCollege())&&professionalTree.getProfessionalName().equals(classInfo.getProfessional())) {
                        ClassTree classTree = new ClassTree();
                        classTree.setClassId(classInfo.getId());
                        classTree.setClassName(classInfo.getName());
                        professionalTree.getClassTrees().add(classTree);
                    }
                }
            }
        }

        return  collegeTrees;
    }
}




