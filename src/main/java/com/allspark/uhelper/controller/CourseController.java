package com.allspark.uhelper.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.excel.EasyExcel;
import com.allspark.uhelper.common.form.*;
import com.allspark.uhelper.common.resp.*;
import com.allspark.uhelper.common.resp.classTree.NAryTree;
import com.allspark.uhelper.common.util.CommonResp;
import com.allspark.uhelper.db.pojo.CourseInfo;
import com.allspark.uhelper.service.impl.ClassInfoServiceImpl;
import com.allspark.uhelper.service.impl.CourseInfoServiceImpl;
import com.allspark.uhelper.service.impl.GraduateTargetInfoServiceImpl;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName CourseController
 * @Description 课程管理接口
 * @Author 86159
 * @Date 2023/1/17 2:17
 * @Version 1.0
 **/
@Tag(name = "CourseController", description = "课程接口")
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

    @Autowired
    private ServletContext servletContext;

    @Operation(summary = "返回所有课程信息")

    @GetMapping("/listAllCourseInfo")
    public CommonResp listAllCourseInfo() {
        List<CourseInfo> courseInfos = courseInfoService.list();
        CommonResp resp = new CommonResp<>();
        if (courseInfos == null) {
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
    public CommonResp listAllCourse() {
        List<CourseInfo> courseInfos = courseInfoService.list();
        CommonResp resp = new CommonResp<>();
        if (courseInfos == null) {
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
    public CommonResp listOne(@PathVariable Long courseId) {
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
    public CommonResp listOne(@Valid @RequestBody CourseInfoForm course) {
        CommonResp resp = new CommonResp<>();
        HashMap<String, Object> result = courseInfoService.modifyOneCourseInfo(course);
        if ((Boolean) result.get("flag")) {
            resp.setMessage("修改成功！");
        } else if (result.containsKey("message")) {
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
    public CommonResp addOne(@Valid @RequestBody CourseInfoForm course) {
        CommonResp resp = new CommonResp<>();
        HashMap<String, Object> result = courseInfoService.addOneCourseInfo(course);
        if ((Boolean) result.get("flag")) {
            resp.setMessage("增加成功！");
        } else if (result.containsKey("message")) {
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
    public CommonResp listAllClass() {
        CommonResp resp = new CommonResp<>();
        List<NAryTree> collegeTrees = classInfoService.listAll3();
        resp.setContent(collegeTrees);
        resp.setMessage("返回所有的班级");
        return resp;
    }

    @Operation(summary = "显示毕业指标点列表(二级显示)")
    @GetMapping("/listAllGraduate")
    public CommonResp listAllGraduate() {
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
    public CommonResp modifyAllStudent(@RequestBody StudentAndScoreListForm form) {
        CommonResp resp = new CommonResp<>();
        boolean flag = courseInfoService.modifyAllStudent(form);
        if (flag) {
            resp.setMessage("修改成功");
        } else {
            resp.setMessage("修改失败");
            resp.setSuccess(false);
        }
        return resp;
    }


    @Operation(summary = "上传该课程下的所有学生的期末成绩")
    @PostMapping("/uploadFinalScore/{courseId}")
    public CommonResp uploadFinalScore(HttpServletRequest request, @PathVariable Long courseId, @RequestBody MultipartFile file) throws IOException {
        CommonResp resp = new CommonResp<>();
        boolean flag;
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        flag = courseInfoService.uploadFinalModel(reader, courseId);

        if (flag) {
            resp.setMessage("上传成功");
        } else {
            resp.setMessage("上传失败");
            resp.setSuccess(false);
        }
        return resp;
    }

    @Operation(summary = "显示该课程的平时成绩构成")
    @GetMapping("/listUsual/{courseId}")
    public CommonResp listUsual(@PathVariable Long courseId) {
        CommonResp resp = new CommonResp<>();
        UsualScoreResp usualScoreResp = courseInfoService.listUsual(courseId);
        resp.setContent(usualScoreResp);
        resp.setMessage("显示该课程的平时成绩构成");
        return resp;
    }

    @Operation(summary = "修改该课程下的平时成绩构成")
    @PostMapping("/modifyUsual")
    public CommonResp modifyUsual(@RequestBody UsualScoreForm form) {
        CommonResp resp = new CommonResp<>();
        boolean flag = courseInfoService.modifyUsual(form);
        if (flag) {
            resp.setMessage("修改成功");
        } else {
            resp.setMessage("修改失败");
            resp.setSuccess(false);
        }
        return resp;
    }


    @Operation(summary = "显示该课程的期末成绩构成(复选框)")
    @GetMapping("/listFinalStructure/{courseId}")
    public CommonResp listFinalStructure(@PathVariable Long courseId) {
        CommonResp resp = new CommonResp<>();
        FinalScoreResp finalScoreResp = courseInfoService.listFinal(courseId);
        resp.setContent(finalScoreResp);
        resp.setMessage("显示该课程的期末成绩构成");
        return resp;
    }

    @Operation(summary = "修改该课程下的期末成绩构成(复选框)")
    @PostMapping("/modifyFinalStructure")
    public CommonResp modifyFinalStructure(@RequestBody FinalScoreForm form) {
        CommonResp resp = new CommonResp<>();
        boolean flag = courseInfoService.modifyFinal(form);
        if (flag) {
            resp.setMessage("修改成功");
        } else {
            resp.setMessage("修改失败");
            resp.setSuccess(false);
        }
        return resp;
    }

//课程目标名称部分是编号后续需要修改 TODO
    @Operation(summary = "显示该课程的期末成绩构成")
    @GetMapping("/listFinal/{courseId}")
    public CommonResp listFinal(@PathVariable Long courseId) {
        CommonResp resp = new CommonResp<>();
        FinalStructureForm finalScoreResp = courseInfoService.listFinal1(courseId);
        resp.setContent(finalScoreResp);
        resp.setMessage("显示该课程的期末成绩构成");
        return resp;
    }

    @Operation(summary = "修改该课程下的期末成绩构成")
    @PostMapping("/modifyFinal")
    public CommonResp modifyFinal(@RequestBody FinalStructureForm form) {
        CommonResp resp = new CommonResp<>();
        boolean flag = courseInfoService.modifyFinal1(form);
        if (flag) {
            resp.setMessage("修改成功");
        } else {
            resp.setMessage("修改失败");
            resp.setSuccess(false);
        }
        return resp;
    }

    @Operation(summary = "下载平时成绩表格")
    @GetMapping("/downloadUsual/{courseId}")
    public void downloadUsual(@PathVariable Long courseId, HttpServletResponse response) throws IOException {
        ExcelWriter writer = courseInfoService.downLoadUsual(courseId);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + courseInfoService.getById(courseId).getName() + "__usualScore" + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    @Operation(summary = "下载期末成绩表格")
    @GetMapping("/downloadFinal/{courseId}")
    public void downloadFinal(@PathVariable Long courseId, HttpServletResponse response) throws IOException {
        ExcelWriter writer = courseInfoService.downloadFinal(courseId);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + courseInfoService.getById(courseId).getName() + "__finalScore" + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    @Operation(summary = "下载课程报告")
    @GetMapping("/downloadReport/{courseId}")
    public void downloadReport(@PathVariable Long courseId, HttpServletResponse response) throws IOException {
        Document document = courseInfoService.downloadReport(courseId);
        response.setContentType("application/msword;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + courseInfoService.getById(courseId).getName() + "__report" + ".doc");
        ServletOutputStream out = response.getOutputStream();
        document.saveToStream(out, FileFormat.Doc);
    }


}
