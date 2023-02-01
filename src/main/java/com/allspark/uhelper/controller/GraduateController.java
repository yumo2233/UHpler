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
    @PostMapping("/insertStudent")
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

    @Operation(summary = "根据id返回指标点和指标内容")
    @PostMapping("/searchTarget")
    public CommonResp<Map> searchTargetById(@RequestBody GraduateTargetForm targetForm) {
        GraduateTargetInfo graduateTargetInfo = graduateTargetInfoService.searchTargetById(targetForm.getId());
        CommonResp resp = new CommonResp<>();
        resp.setContent(graduateTargetInfo);
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


    @Operation(summary = "添加毕业要求")
    @PostMapping("/insertInfoTarget")
    public CommonResp insertInfoTarget(@RequestBody GraduateTargetInfoForm targetForm) {
        GraduateInfo graduateTargetInfo = graduateInfoService.transfer(targetForm);
        CommonResp resp = new CommonResp<>();
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

    @Operation(summary = "添加毕业指标点")
    @PostMapping("/insertTarget")
    public CommonResp insertTarget(@RequestBody GraduateTargetForm targetForm) {
        GraduateTargetInfo graduateTargetInfo = graduateTargetInfoService.transferTarget(targetForm);
        CommonResp commonResp = new CommonResp();
        if (commonResp.getSuccess()) {
            commonResp.setMessage("插入成功");
        } else {
            commonResp.setMessage("false");
        }
        return commonResp;
    }

    @Operation(summary = "批量插入毕业要求")
    @PostMapping("/insertGraduateInfoBatch")
    public CommonResp insertGraduateInfoBatch(@RequestBody ArrayList<GraduateTargetInfoForm> list) {
        ArrayList<GraduateInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            GraduateTargetInfoForm targetForm = list.get(i);
            GraduateInfo graduateTargetInfo = graduateInfoService.transfer(targetForm);
            arrayList.add(graduateTargetInfo);
        }
        graduateInfoService.insertGraduateInfoBatch(arrayList);
        CommonResp commonResp = new CommonResp();
        if (commonResp.getSuccess()) {
            commonResp.setMessage("插入成功");
        } else {
            commonResp.setMessage("false");
        }
        return commonResp;
    }

    @Operation(summary = "更新毕业指标点")
    @PostMapping("/updateTarget")
    public CommonResp updateTarget(@RequestBody GraduateTargetForm targetForm) {
        GraduateTargetInfo graduateTargetInfo = graduateTargetInfoService.transferTarget(targetForm);
        boolean b = graduateTargetInfoService.updateTarget(graduateTargetInfo);
        CommonResp commonResp = new CommonResp();
        if (b) {
            commonResp.setMessage("更新成功");
        } else {
            commonResp.setMessage("false");
        }
        return commonResp;
    }

    @Operation(summary = "批量更新毕业指标点")
    @PostMapping("/updateTargetBatch")
    public CommonResp updateTargetBatch(@RequestBody ArrayList<GraduateTargetForm> list) {
        ArrayList<GraduateTargetInfo> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); ++i) {
            GraduateTargetInfo graduateTargetInfo = graduateTargetInfoService.transferTarget(list.get(i));
            arrayList.add(graduateTargetInfo);
        }
        boolean b = graduateTargetInfoService.updateTargetBatch(arrayList);
        CommonResp commonResp = new CommonResp();
        if (b) {
            commonResp.setMessage("更改成功");
        } else {
            commonResp.setMessage("false");
        }
        return commonResp;
    }

    //要根据id返回所有指标点，待改动
    @Operation(summary = "返回所有毕业指标点")
    @GetMapping("/searchTargetBatch")
    public CommonResp searchTargetBatch() {
        ArrayList<HashMap> list = graduateTargetInfoService.searchTargetBatch();
        CommonResp resp = new CommonResp();
        resp.setContent(list);
        return resp;
    }
}