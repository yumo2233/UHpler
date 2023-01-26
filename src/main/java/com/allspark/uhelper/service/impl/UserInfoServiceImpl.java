package com.allspark.uhelper.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.allspark.uhelper.common.form.UserLoginForm;
import com.allspark.uhelper.common.form.UserUpdatePasswordForm;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.UserInfo;
import com.allspark.uhelper.service.UserInfoService;
import com.allspark.uhelper.db.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
* @author 86159
* @description 针对表【user_info(用户表)】的数据库操作Service实现
* @createDate 2023-01-26 19:51:13
*/
@Service
@DS("u_classinfo")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    @Resource
    private UserInfoMapper userInfoMapper;

    public HashMap login(UserLoginForm form) {
        String number = form.getNumber();
        String password0 = form.getPassword();
        String password1 = SaSecureUtil.md5BySalt(password0, number);
        Long userId = userInfoMapper.login(number, password1);
        Integer isFirst = userInfoMapper.selectIsFirstByNumber(number);
        HashMap map = new HashMap();
        map.put("isFirst",isFirst);
        map.put("userId",userId);
        return map;
    }

    public int updatePassword(UserUpdatePasswordForm form) {
        String number = form.getNumber();
        String password0 = form.getPassword();
        String password1 = SaSecureUtil.md5BySalt(password0, number);
        int rows = userInfoMapper.update(number, password1);
        return rows;
    }
}




