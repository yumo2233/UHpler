package com.allspark.uhelper.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.allspark.uhelper.common.form.GraduateInfoForm;
import com.allspark.uhelper.common.form.GraduateTargetForm;
import com.allspark.uhelper.common.resp.GradeAndProfessionalResp;
import com.allspark.uhelper.common.util.CommonResp;
import com.allspark.uhelper.db.mapper.GraduateInfoMapper;
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
import java.util.List;
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
//@SaCheckLogin
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

    @Operation(summary = "根据年级专业返回毕业要求的名称和个数")
    @PostMapping("selectGraduateInfoByGP")
    public CommonResp<ArrayList> selectGraduateInfoByGradeAndProfessional(@RequestBody GraduateInfoForm graduateInfoForm) {
        GraduateInfo graduateInfo = graduateInfoService.transfer(graduateInfoForm);
        ArrayList<HashMap> list = graduateInfoService.selectGraduateInfoByGradeAndProfessional(graduateInfo);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(list);
        return commonResp;
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
    public CommonResp insertInfoTarget(@RequestBody GraduateInfoForm targetForm) {
        GraduateInfo graduateTargetInfo = graduateInfoService.transfer(targetForm);
        CommonResp resp = new CommonResp<>();
        graduateInfoService.insertGraduateInfo(graduateTargetInfo);
        if (resp.getSuccess()) {
            resp.setMessage("插入成功");
        } else {
            resp.setMessage("false");
        }
        return resp;
    }

    @Operation(summary = "根据Id删除毕业要求")
    @GetMapping("/deleteGraduateInfo/{Id}")
    public CommonResp deleteGraduateInfo(@PathVariable Long Id) {
        // GraduateInfo graduateInfo = graduateInfoService.transfer(graduateInfoForm);
        CommonResp commonResp = new CommonResp();
//        if (graduateInfo.getName() == null) {
//            commonResp.setMessage("毕业要求名称为空");
//            return commonResp;
//        }
        boolean b = graduateInfoService.deleteGraduateInfo(Id);
        if (b) {
            commonResp.setMessage("删除成功");
        } else {
            commonResp.setContent("false");
        }
        return commonResp;
    }

    @Operation(summary = "更新毕业要求名称")
    @PostMapping("/updateGraduationName")
    public CommonResp updateGraduationName(@RequestBody GraduateInfoForm graduateInfoForm) {
        GraduateInfo graduateInfo = graduateInfoService.transfer(graduateInfoForm);
        boolean b = graduateInfoService.updateGraduationName(graduateInfo);
        CommonResp commonResp = new CommonResp();
        if (b) {
            commonResp.setMessage("更新成功");
        } else {
            commonResp.setContent("false");
        }
        return commonResp;
    }

    @Operation(summary = "删除毕业指标点")
    @GetMapping("/deleteTarget/{Id}")
    public CommonResp deleteTarget(@PathVariable Long Id) {
        try {
            // GraduateTargetInfo graduateTargetInfo = graduateTargetInfoService.transferTarget(targetForm);
            boolean flag = graduateTargetInfoService.deleteTarget(Id);
            CommonResp commonResp = new CommonResp();
            if (flag) {
                commonResp.setMessage("删除成功");
            } else {
                commonResp.setMessage("false");
                commonResp.setMessage("没有相关指标点，删除失败。多次出现请联系管理员");
            }
            return commonResp;
        } catch (Exception e) {
            CommonResp commonResp = new CommonResp();
            commonResp.setSuccess(false);
            commonResp.setContent("班级或专业或指标点名称有误");
            commonResp.setMessage("系统出现异常，请联系管理员！");
            return commonResp;
        }
    }

    @Operation(summary = "添加单个毕业指标点")
    @PostMapping("/insertTarget")
    public CommonResp insertTarget(@RequestBody GraduateTargetForm targetForm) {
        try {
            GraduateTargetInfo graduateTargetInfo = graduateTargetInfoService.transferTarget(targetForm);
            int i = graduateTargetInfoService.insertTarget(graduateTargetInfo);
            CommonResp commonResp = new CommonResp();
            commonResp.setMessage("有" + i + "个指标点");
            return commonResp;
        } catch (Exception e) {
            CommonResp commonResp = new CommonResp();
            commonResp.setSuccess(false);
            commonResp.setContent("插入的班级不存在");
            commonResp.setMessage("系统出现异常，请联系管理员！");
            return commonResp;
        }

    }

    @Operation(summary = "批量添加毕业指标点")
    @PostMapping("/insertTargetBatch")
    public CommonResp insertTargetBatch(@RequestBody ArrayList<GraduateTargetForm> list) {
        ArrayList<GraduateTargetInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GraduateTargetForm graduateTargetForm = list.get(i);
            GraduateTargetInfo graduateTargetInfo = graduateTargetInfoService.transferTarget(graduateTargetForm);
            arrayList.add(graduateTargetInfo);
        }
        int batch = graduateTargetInfoService.insertTargetBatch(arrayList);
        CommonResp commonResp = new CommonResp();
        if (commonResp.getSuccess()) {
            commonResp.setMessage("有" + batch + "个指标点");
        } else {
            commonResp.setMessage("false");
        }
        return commonResp;
    }

    @Operation(summary = "批量插入毕业要求")
    @PostMapping("/insertGraduateInfoBatch")
    public CommonResp insertGraduateInfoBatch(@RequestBody ArrayList<GraduateInfoForm> list) {
        try {
            ArrayList<GraduateInfo> arrayList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                GraduateInfoForm targetForm = list.get(i);
                GraduateInfo graduateTargetInfo = graduateInfoService.transfer(targetForm);
                arrayList.add(graduateTargetInfo);
            }
            graduateInfoService.insertGraduateInfoBatch(arrayList);
            CommonResp commonResp = new CommonResp();
            if (commonResp.getSuccess()) {
                commonResp.setMessage("插入成功");
            }
            return commonResp;
        } catch (Exception e) {
            CommonResp commonResp = new CommonResp();
            commonResp.setSuccess(false);
            commonResp.setContent("请检查输入字数是否超过限制");
            commonResp.setMessage("系统出现异常，请联系管理员！");
            return commonResp;
        }
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
        graduateTargetInfoService.updateTargetBatch(arrayList);
        CommonResp commonResp = new CommonResp();
//        if (b) {
//            commonResp.setMessage("更改成功");
//        } else {
//            commonResp.setMessage("false");
//        }
        return commonResp;
    }


    @Operation(summary = "返回所有毕业指标点")
    @GetMapping("/searchTargetBatch")
    public CommonResp searchTargetBatch(@RequestBody GraduateTargetForm graduateTargetForm) {
        GraduateTargetInfo graduateTargetInfo = graduateTargetInfoService.transferTarget(graduateTargetForm);
        ArrayList<HashMap> list = graduateTargetInfoService.searchTargetBatch(graduateTargetInfo);
        CommonResp resp = new CommonResp();
        resp.setContent(list);
        return resp;
    }

    @Operation(summary = "返回所有年级")
    @GetMapping("selectGrade")
    public CommonResp selectGrade() {
        List<HashMap> list = graduateInfoService.selectGrade();
        CommonResp commonResp = new CommonResp();
        if (list == null) {
            commonResp.setMessage("未查询到相关数据");
        }
        commonResp.setContent(list);
        return commonResp;
    }

    @Operation(summary = "返回所有专业")
    @GetMapping("selectProfessional")
    public CommonResp selectProfessional() {
        List<HashMap> list = graduateInfoService.selectProfessional();
        CommonResp commonResp = new CommonResp();
        if (list == null) {
            commonResp.setMessage("未查询到相关数据");
            return commonResp;
        }
        commonResp.setContent(list);
        return commonResp;
    }

    @Operation(summary = "返回毕业表中存在的年级班级")
    @GetMapping("selectGP")
    public CommonResp<ArrayList> selectGradeAndProfessional() {
        ArrayList<GradeAndProfessionalResp> resps = graduateInfoService.selectGradeAndProfessional();
        CommonResp commonResp = new CommonResp();
        if (resps == null) {
            commonResp.setMessage("未查询到相关数据");
            return commonResp;
        }
        commonResp.setContent(resps);
        return commonResp;
    }

    @Operation(summary = "分别返回毕业表中存在的年级班级")
    @GetMapping("selectGAP")
    public CommonResp<HashMap> selectGAndP() {
        List<HashMap> list1 = graduateInfoService.selectProfessional();
        List<HashMap> list2 = graduateInfoService.selectGrade();
        HashMap map = new HashMap();
        CommonResp commonResp = new CommonResp();
        if (list1 == null || list2 == null) {
            commonResp.setMessage("未查询到相关数据");
            return commonResp;
        }
        map.put("grade", list2);
        map.put("professional", list1);
        commonResp.setContent(map);
        return commonResp;
    }

    @Operation(summary = "返回毕业要求页面")
    @GetMapping("selectGraduatePage/{graduateId}")
    public CommonResp selectall(@PathVariable Long graduateId) {
        ArrayList<HashMap> selectall = graduateInfoService.selectall(graduateId);
        CommonResp commonResp = new CommonResp();
        commonResp.setContent(selectall);
        return commonResp;
    }

}