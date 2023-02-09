package com.allspark.uhelper.myenum;

import com.allspark.uhelper.utils.UuidUtils;
import org.junit.Test;

/**
 * @author Pzr
 * @create 2023/1/31 - 18:00
 */

public class test {
    @Test
    public void test() {
        Long uuId = UuidUtils.getUuId();
        System.out.println(uuId);
    }
}


