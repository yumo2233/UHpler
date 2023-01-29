//package com.allspark.uhelper.config;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author ChangLu
// * @date 2021/08/30 11:29
// **/
//@Component
//public class CorsFilter extends OncePerRequestFilter {
//    //跨域白名单
//    private static List<String> whiteList = new ArrayList<>();
//    static {
//        whiteList.add("http://127.0.0.1:3000");
//        whiteList.add("http://192.168.0.106");
////        whiteList.add("http://yao-shop.jd.com");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        //请求的地址
//        String originUrl = request.getHeader("origin");
//        //查看是否在白名单内
//        boolean allow = whiteList.contains(originUrl);
//        if(allow){
//            response.setHeader("Access-Control-Allow-Origin", originUrl);
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//            //30分钟
//            response.setHeader("Access-Control-Max-Age", "1800");
//            response.setHeader("Access-Control-Allow-Headers", "x-requested-with, content-type");
//            filterChain.doFilter(request, response);
//        }else{
//            //对于非白名单域名的请求，不予进行访问，不然还会进入到controller方法中执行对应的逻辑
//            return;
//        }
//    }
//}
