package com.allspark.uhelper.controller;

import com.allspark.uhelper.common.util.CommonResp;
import com.allspark.uhelper.service.GraduateInfoService;
import com.allspark.uhelper.service.impl.GraduateTargetInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
