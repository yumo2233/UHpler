package com.allspark.uhelper.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.allspark.uhelper.common.form.ClassAndStudentForm;
import com.allspark.uhelper.common.form.ClassInfoForm;
import com.allspark.uhelper.common.form.StudentInfoForm;
import com.allspark.uhelper.common.util.CommonResp;
import com.allspark.uhelper.db.pojo.ClassInfo;
import com.allspark.uhelper.db.pojo.StudentDbInfo;
import com.allspark.uhelper.db.pojo.StudentInfo;
import com.allspark.uhelper.service.ClassInfoService;
import com.allspark.uhelper.service.StudentInfoService;
import com.allspark.uhelper.utils.CopyUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName ClassController
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/30 16:10
 * @Version 1.0
 **/
@Tag(name = "ClassController", description = "班级和学生接口")
@RestController
@RequestMapping("/class")
public class ClassController {

    //    @Autowired
//    private UploadDAO uploadDAO;
    @Autowired
    ClassInfoService classInfoService;

    @Autowired
    StudentInfoService studentInfoService;

    @Operation(summary = "插入单个班级信息")
    @PostMapping("/insetClass")
    public CommonResp insetClass(@RequestBody ClassInfoForm classInfoForm) {
        ClassInfo classInfo = classInfoService.transferClass(classInfoForm);
        classInfoService.insetClass(classInfo);
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

    @Operation(summary = "删除单个班级信息")
    @GetMapping("/deleteClassClass/{id}")
    public CommonResp deleteClassClass(@PathVariable Long id) {
        boolean b = classInfoService.deleteClass(id);
        CommonResp commonResp = new CommonResp();
        if (b) {
            commonResp.setMessage("true");
        } else {
            commonResp.setMessage("false");
        }
        return commonResp;
    }

    @Operation(summary = "更新单个班级信息")
    @PostMapping("/updateClass")
    public CommonResp updateClass(@RequestBody ClassInfoForm classInfoForm) {
        ClassInfo classInfo = classInfoService.transferClass(classInfoForm);
        classInfoService.updateClass(classInfo);
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

    @Operation(summary = "返回班级信息")
    @GetMapping("/selectClass")
    public CommonResp selectClass() {
        ArrayList<HashMap> arrayList = classInfoService.selectClass();
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(arrayList);
        return commonResp;
    }

    @Operation(summary = "插入单个学生信息")
    @PostMapping("/insetSingleStudent")
    public CommonResp insetSingleStudent(@RequestBody StudentInfoForm studentInfoForm) {
        StudentInfo studentInfo = classInfoService.transferStudent(studentInfoForm);
        classInfoService.insertStudent(studentInfo);
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

    @Operation(summary = "查询单个班级信息")
    @GetMapping("/selectClass/{id}")
    public CommonResp selectClassById(@PathVariable Long id) {
        HashMap hashMap = classInfoService.selectClassById(id);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(hashMap);
        return commonResp;
    }

    @Operation(summary = "根据班级id查询学生信息")
    @GetMapping("/selectStudent/{class_id}")
    public CommonResp selectStudentByClassId(@PathVariable Long class_id) {
        ArrayList<HashMap> arrayList = studentInfoService.selectStudentByClassId(class_id);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(arrayList);
        return commonResp;
    }

    @Operation(summary = "上传学生的excel表格")
    @PostMapping("/uploadClass")
    @ResponseBody
    public CommonResp upload(MultipartFile file) throws IOException {
        ArrayList<StudentInfo> arrayList = classInfoService.uploadExcel(file);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(arrayList);
        return commonResp;
    }

    @Operation(summary = "同时上传学生和班级")
    @PostMapping("/insertCASBatch")
    @ResponseBody
    public CommonResp insertCASBatch(@RequestBody ClassAndStudentForm classAndStudentForm) {
        ArrayList<StudentInfo> arrayList = classAndStudentForm.getArrayList();
        int size = arrayList.size();
        ClassInfo classInfo = classInfoService.transferClass(classAndStudentForm);
        classInfo.setHeadcount(size);
        classInfoService.insetClass(classInfo);
        for (StudentInfo one : arrayList) {
            one.setClassId(classInfo.getId());
            classInfoService.insertStudent(one);
        }
        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

}
