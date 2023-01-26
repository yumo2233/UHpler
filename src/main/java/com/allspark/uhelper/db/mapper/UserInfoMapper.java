package com.allspark.uhelper.db.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.allspark.uhelper.db.pojo.UserInfo;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86159
* @description 针对表【user_info(用户表)】的数据库操作Mapper
* @createDate 2023-01-26 19:51:13
* @Entity com.allspark.uhelper.db.pojo.UserInfo
*/
@DS("u_classinfo")
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    Long login(String number,String password);

    Integer update(String number, String password);

    Integer selectIsFirstByNumber(@Param("number") String number);



}




