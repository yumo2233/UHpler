package com.allspark.uhelper.utils;

/**
 * @ClassName RequestContext
 * @Description TODO
 * @Author 86159
 * @Date 2022/10/24 23:27
 * @Version 1.0
 **/
import java.io.Serializable;

public class RequestContext implements Serializable {

    private static ThreadLocal<String> remoteAddr = new ThreadLocal<>();

    public static String getRemoteAddr() {
        return remoteAddr.get();
    }

    public static void setRemoteAddr(String remoteAddr) {
        RequestContext.remoteAddr.set(remoteAddr);
    }

}

