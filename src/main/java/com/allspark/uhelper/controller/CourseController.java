package com.allspark.uhelper.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.allspark.uhelper.common.form.*;
import com.allspark.uhelper.common.resp.*;
import com.allspark.uhelper.common.resp.classTree.NAryTree;
import com.allspark.uhelper.common.util.CommonResp;
import com.allspark.uhelper.db.pojo.CourseInfo;
import com.allspark.uhelper.service.impl.ClassInfoServiceImpl;
import com.allspark.uhelper.service.impl.CourseInfoServiceImpl;
import com.allspark.uhelper.service.impl.GraduateTargetInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName CourseController
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/17 2:17
 * @Version 1.0
 **/
@Tag(name = "CourseController",description = "课程接口")
@RestController
@RequestMapping("/course")
//@SaCheckLogin
public class CourseController {

    @Autowired
    private CourseInfoServiceImpl courseInfoService;

    @Autowired
    private ClassInfoServiceImpl classInfoService;

    @Autowired
    private GraduateTargetInfoServiceImpl graduateTargetInfoService;

    @Operation(summary = "返回所有课程信息")

    @GetMapping("/listAllCourseInfo")
    public CommonResp listAllCourseInfo(){
        List<CourseInfo> courseInfos = courseInfoService.list();
        CommonResp resp = new CommonResp<>();
        if (courseInfos==null) {
            resp.setMessage("当前没有课程");
        } else {
            List<CourseInfoResp> courseRespInfoList = courseInfoService.listCourseInfoResp(courseInfos);
            resp.setContent(courseRespInfoList);
            resp.setMessage("返回所有的课程信息");
        }
        return resp;
    }

    @Operation(summary = "返回所有课程")
    @GetMapping("/listAllCourse")
    public CommonResp listAllCourse(){
        List<CourseInfo> courseInfos = courseInfoService.list();
        CommonResp resp = new CommonResp<>();
        if (courseInfos==null) {
            resp.setMessage("当前没有课程");
        } else {
            List<ListAllCourseResp> courseRespList = courseInfoService.listAllCourseResp(courseInfos);
            resp.setContent(courseRespList);
            resp.setMessage("返回所有的课程");
        }
        return resp;
    }

    @Operation(summary = "返回单个课程信息")
    @GetMapping("/listOne/{courseId}")
    public CommonResp listOne(@PathVariable Long courseId){
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(courseId);
        List<CourseInfo> courseInfos = courseInfoService.listByIds(ids);
        List<CourseInfoResp> courseInfoRespList = courseInfoService.listCourseInfoResp(courseInfos);
        CourseInfoResp courseInfoResp = courseInfoRespList.get(0);
        CommonResp resp = new CommonResp<>();
        resp.setContent(courseInfoResp);
        resp.setMessage("返回指定course_id的课程");
        return resp;
    }

    @Operation(summary = "修改单个课程信息")
    @PostMapping("/modifyOne")
    public CommonResp listOne(@Valid @RequestBody CourseInfoForm course){
        CommonResp resp = new CommonResp<>();
        HashMap<String,Object> result =courseInfoService.modifyOneCourseInfo(course);
        if ((Boolean) result.get("flag")) {
            resp.setMessage("修改成功！");
        } else if (result.containsKey("message")){
            resp.setSuccess(false);
            resp.setMessage((String) result.get("message"));
        } else {
            resp.setSuccess(false);
            resp.setContent("增加失败！");
        }
        return resp;
    }

    @Operation(summary = "增加单个课程信息")
    @PostMapping("/addOne")
    public CommonResp addOne(@Valid @RequestBody CourseInfoForm course){
        CommonResp resp = new CommonResp<>();
        HashMap<String,Object> result = courseInfoService.addOneCourseInfo(course);
        if ((Boolean) result.get("flag")) {
            resp.setMessage("增加成功！");
        } else if (result.containsKey("message")){
            resp.setSuccess(false);
            resp.setMessage((String) result.get("message"));
        } else {
            resp.setSuccess(false);
            resp.setMessage("增加失败！");
        }
        return resp;
    }

    @Operation(summary = "显示班级列表(三级显示)")
    @GetMapping("/listAllClass")
    public CommonResp listAllClass(){
        CommonResp resp = new CommonResp<>();
        List<NAryTree> collegeTrees = classInfoService.listAll3();
        resp.setContent(collegeTrees);
        resp.setMessage("返回所有的班级");
        return resp;
    }

    @Operation(summary = "显示毕业指标点列表(二级显示)")
    @GetMapping("/listAllGraduate")
    public CommonResp listAllGraduate(){
        CommonResp resp = new CommonResp<>();
        List<NAryTree> collegeTrees = graduateTargetInfoService.listAll2();
        resp.setContent(collegeTrees);
        resp.setMessage("返回所有的毕业指标点");
        return resp;
    }


    @Operation(summary = "显示该课程的学生和成绩")
    @GetMapping("/listAllStudent/{courseId}")
    public CommonResp listAllStudent(@PathVariable Long courseId) {
        CommonResp resp = new CommonResp<>();

        List<StudentAndScoreResp> studentAndScoreRespList = courseInfoService.listAllStudent(courseId);
        resp.setContent(studentAndScoreRespList);
        resp.setMessage("显示该课程下的所有学生的平时和期末成绩");
        return resp;
    }

    @Operation(summary = "修改该课程下的所有学生的平时和期末成绩")
    @PostMapping("/modifyAllStudent")
    public CommonResp modifyAllStudent(@RequestBody StudentAndScoreListForm form){
        CommonResp resp = new CommonResp<>();
        boolean flag = courseInfoService.modifyAllStudent(form);
        if(flag){
            resp.setMessage("修改成功");
        }else {
            resp.setMessage("修改失败");
            resp.setSuccess(false);
        }
        return resp;
    }

    @Operation(summary = "显示该课程的平时成绩构成")
    @GetMapping("/listUsual/{courseId}")
    public CommonResp listUsual(@PathVariable Long courseId){
        CommonResp resp = new CommonResp<>();
        UsualScoreResp usualScoreResp = courseInfoService.listUsual(courseId);
        resp.setContent(usualScoreResp);
        resp.setMessage("显示该课程的平时成绩构成");
        return resp;
    }

    @Operation(summary = "修改该课程下的平时成绩构成")
    @PostMapping("/modifyUsual")
    public CommonResp modifyUsual(@RequestBody UsualScoreForm form){
        CommonResp resp = new CommonResp<>();
        boolean flag = courseInfoService.modifyUsual(form);
        if(flag){
            resp.setMessage("修改成功");
        }else {
            resp.setMessage("修改失败");
            resp.setSuccess(false);
        }
        return resp;
    }

    @Operation(summary = "显示该课程的期末成绩构成")
    @GetMapping("/listFinal/{courseId}")
    public CommonResp listFinal(@PathVariable Long courseId){
        CommonResp resp = new CommonResp<>();
        FinalScoreResp finalScoreResp = courseInfoService.listFinal(courseId);
        resp.setContent(finalScoreResp);
        resp.setMessage("显示该课程的期末成绩构成");
        return resp;
    }

    @Operation(summary = "修改该课程下的期末成绩构成")
    @PostMapping("/modifyFinal")
    public CommonResp modifyFinal(@RequestBody FinalScoreForm form){
        CommonResp resp = new CommonResp<>();
        boolean flag = courseInfoService.modifyFinal(form);
        if(flag){
            resp.setMessage("修改成功");
        }else {
            resp.setMessage("修改失败");
            resp.setSuccess(false);
        }
        return resp;
    }


}
