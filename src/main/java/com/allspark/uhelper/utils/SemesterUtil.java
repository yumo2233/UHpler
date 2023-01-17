package com.allspark.uhelper.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @ClassName SemesterUtil
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/17 3:14
 * @Version 1.0
 **/
public class SemesterUtil {

    public static String[] select(){
        Date date = DateUtil.date();
        int year = DateUtil.year(date);
        String[] semester = new String[8];
        semester[0] = (year-1) + "_09--" + (year-1) +"_12";
        semester[1] = year + "_02--" + year + "_6";
        semester[2] = year + "_09--" + year + "_12";
        semester[3] = (year+1) + "_02--" + (year+1) + "_6";
        semester[4] = (year+1) + "_09--" + (year+1) +"_12";
        semester[5] = (year+2) + "_02--" + (year+2) + "_6";
        semester[6] = (year+2) + "_09--" + (year+2) + "_12";
        semester[7] = (year+3) + "_02--" + (year+3) + "_6";
        return semester;
    }
}
