package com.allspark.uhelper.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.allspark.uhelper.common.form.UserLoginForm;
import com.allspark.uhelper.common.form.UserUpdatePasswordForm;
import com.allspark.uhelper.common.util.CommonResp;
import com.allspark.uhelper.service.impl.UserInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/17 18:44
 * @Version 1.0
 **/
@Tag(name = "UserController",description = "用户web接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Operation(description = "用户登录")
    @PostMapping("/login")
    public CommonResp login(@RequestBody @Valid UserLoginForm form){
        HashMap map = userInfoService.login(form);
        Long userId = (Long) map.get("userId");
        Integer isFirst = (Integer) map.get("isFirst");
        HashMap param = new HashMap();
        param.put("isFirst", isFirst);
        param.put("number",form.getNumber());
        param.put("userName", map.get("userName"));
        CommonResp resp = new CommonResp();
        resp.setSuccess(userId!=null?true:false);
        if (userId!=null) {
            StpUtil.login(userId);
            resp.setMessage("登陆成功");
            param.put("token", StpUtil.getTokenValue());
        } else {
            resp.setMessage("登陆失败");
        }
        resp.setContent(param);
        return resp;
    }

    @Operation(description = "用户登出")
    @PostMapping("/logout")
    public CommonResp logout(){
        StpUtil.logout();
        CommonResp resp = new CommonResp();
        resp.setMessage("登出成功");
        return resp;
    }

    @Operation(description = "修改密码")
    @PostMapping("/updatePassword")
    @SaCheckLogin
    public CommonResp updatePassword(@RequestBody @Valid UserUpdatePasswordForm form){
        int rows = userInfoService.updatePassword(form);
        CommonResp resp = new CommonResp();
        if(rows==0) {
            resp.setSuccess(false);
            resp.setMessage("修改失败");
        } else{
            resp.setMessage("修改成功");
        }
        return resp;
    }

}
