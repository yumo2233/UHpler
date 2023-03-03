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

    public static String[] select() {
        Date date = DateUtil.date();
        int year = DateUtil.year(date);
        String[] semester = new String[8];
        semester[0] = (year - 1) + "~" + year + "_01";
        semester[1] = (year - 1) + "~" + year + "_02";
        semester[2] = year + "~" + (year + 1) +"_01";
        semester[3] = year + "~" + (year + 1) +"_02";
        semester[4] = (year + 1) + "~" + (year + 2) + "_01";
        semester[5] = (year + 1) + "~" + (year + 2) + "_02";
        semester[6] = (year + 2) + "~" + (year + 3) + "_01";
        semester[7] = (year + 2) + "~" + (year + 3) + "_02";
        return semester;
    }

    public static Integer[] selectInt() {
        Date date = DateUtil.date();
        int year = DateUtil.year(date);
        Integer[] sem = new Integer[8];
        sem[0] = (year - 1) * 10 + 1;
        sem[1] = (year - 1) * 10 + 2;
        sem[2] = year * 10 + 1;
        sem[3] = year * 10 + 2;
        sem[4] = (year + 1) * 10 + 1;
        sem[5] = (year + 1) * 10 + 2;
        sem[6] = (year + 2) * 10 + 1;
        sem[7] = (year + 2) * 10 + 2;
        return sem;
    }
}
