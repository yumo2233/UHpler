package com.allspark.uhelper.controller;

import com.allspark.uhelper.common.form.GraduateTargetForm;
import com.allspark.uhelper.common.form.GraduateTargetInfoForm;
import com.allspark.uhelper.common.util.CommonResp;
import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.allspark.uhelper.db.pojo.GraduateTargetInfo;
import com.allspark.uhelper.service.GraduateInfoService;
import com.allspark.uhelper.service.impl.GraduateTargetInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GraduateController
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/30 16:11
 * @Version 1.0
 **/
@Tag(name = "GraduateController",description = "毕业要求接口")
@RestController
@RequestMapping("/graduate")
public class GraduateController {

    @Autowired
    private GraduateInfoService graduateInfoService;

    @Autowired
    private GraduateTargetInfoServiceImpl graduateTargetInfoService;

    @Operation(summary = "增加单个学生信息")
    @PostMapping("/insertStudnet")
    public CommonResp insertStudent(@RequestBody int id) {
        HashMap map = new HashMap();
        map.put("id", id);
        //map.put("index",index);
        // map.put("name",name);
        //map.put("number",number);
        //map.put("class_id",class_id);
        CommonResp resp = new CommonResp();
        graduateInfoService.insertStudent(map);
        return resp;
    }

    @Operation(summary = "返回指标点和指标内容")
    @PostMapping("/searchTarget")
    public CommonResp<Map> searchTargetById(@RequestBody int id) {
        HashMap map = graduateTargetInfoService.searchTargetById(id);
        CommonResp resp = new CommonResp<>();
        resp.setContent(map);
        return resp;
    }

    @Operation(summary = "返回所有学院和班级")
    @GetMapping("/selectCollegeAndGrade")
    public CommonResp selectCollegeAndGrade() {
        ArrayList<HashMap> list = graduateInfoService.selectCollegeAndGrade();
        CommonResp resp = new CommonResp<>();
        resp.setMessage("一共用" + list.size() + "条数据");
        resp.setContent(list);
        return resp;
    }

    @Operation(summary = "添加毕业要求和指标点")
    @PostMapping("/insertTarget")
    public CommonResp insertTarget(@RequestBody GraduateTargetInfoForm targetForm) {
        GraduateInfo graduateTargetInfo = new GraduateInfo();
        CommonResp resp = new CommonResp<>();
        graduateTargetInfo.setId(targetForm.getId());
        graduateTargetInfo.setCollege(targetForm.getCollege());
        graduateTargetInfo.setGrade(targetForm.getGrade());
        graduateTargetInfo.setProfessional(targetForm.getProfessional());
        graduateTargetInfo.setGraduate_count(targetForm.getGraduate_count());
        graduateTargetInfo.setGraduate_target_count(targetForm.getGraduate_target_count());
        graduateTargetInfo.setName(targetForm.getName());
        graduateTargetInfo.setUser_id(targetForm.getUser_id());
        graduateInfoService.insertInfoTarget(graduateTargetInfo);
        return resp;
    }

    @Operation(summary = "删除毕业指标点")
    @PostMapping("/deleteTarget")
    public CommonResp deleteTarget(@RequestBody GraduateTargetForm targetForm) {
        Long id = targetForm.getId();
        boolean flag = graduateTargetInfoService.deleteTarget(id);
        CommonResp commonResp = new CommonResp();
        if (flag) {
            commonResp.setMessage("删除成功");
        } else {
            commonResp.setMessage("删除失败");
        }
        return commonResp;
    }
}