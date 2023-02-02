package com.allspark.uhelper.utils;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Pzr
 * @create 2023/2/2 - 16:46
 */
public class UuidUtils {
    private static AtomicLong id;


    /**
     * 生成Long 类型唯一ID
     */
    public synchronized static Long getUuId() {


        String nanoRandom = System.nanoTime() + "" + new Random().nextInt(99999);
        int hash = Math.abs(UUID.randomUUID().hashCode());
        int needAdd = 19 - String.valueOf(hash).length();
        return Long.valueOf(hash + "" + nanoRandom.substring(0, needAdd));
    }

}
