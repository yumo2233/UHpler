package com.allspark.uhelper.controller;

import com.allspark.uhelper.common.form.CourseInfoForm;
import com.allspark.uhelper.common.resp.CourseInfoResp;
import com.allspark.uhelper.common.util.CommonResp;
import com.allspark.uhelper.db.pojo.CourseInfo;
import com.allspark.uhelper.service.impl.CourseInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CourseController
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/17 2:17
 * @Version 1.0
 **/
@Tag(name = "CourseController")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseInfoServiceImpl courseInfoService;

    @Operation(summary = "返回所有课程信息")
    @GetMapping("/listAll")
    public CommonResp listAll(){
        List<CourseInfo> courseInfos = courseInfoService.list();
        List<CourseInfoResp> courseInfoRespList = courseInfoService.listCourseInfoResp(courseInfos);
        CommonResp resp = new CommonResp<>();
        resp.setContent(courseInfoRespList);
        resp.setMessage("返回所有的课程");
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
    public CommonResp listOne(@RequestBody CourseInfoForm course){
        CommonResp resp = new CommonResp<>();
        if (courseInfoService.modifyOneCourseInfo(course)) {
            resp.setMessage("修改成功！");
        } else {
            resp.setSuccess(false);
            resp.setMessage("修改失败！");
        }
        return resp;
    }

    @Operation(summary = "增加单个课程信息")
    @PostMapping("/addOne")
    public CommonResp addOne(@RequestBody CourseInfoForm course){
        CommonResp resp = new CommonResp<>();
        if (courseInfoService.addOneCourseInfo(course)) {
            resp.setMessage("增加成功！");
        } else {
            resp.setSuccess(false);
            resp.setMessage("增加失败！");
        }
        return resp;
    }

}
