package com.allspark.uhelper.utils;/**
  *@ClassName SnowFlake10
  *@Description TODO
  *@Author 86159
  *@Date 2023/1/25 21:23
  *@Version 1.0
  **/
public class SnowFlake10 {
    //生成id
    public Long nextId(){
        long id = SnowflakeIdWorker.generateId();
        //得到id 很长且高位很长部分是一样的数
        StringBuilder sb=new StringBuilder(id+"");
        StringBuilder reverse = sb.reverse();
        //将id翻转：我们发现id很长，且高位很长部分是一样的数
        id=new Long(reverse.toString())/1000;
        //切去部分长度
        while(id>1999999999){
            //1999999999以内的10位或9位或8位id;....
            id/=10;
        }
        Integer _id_ = Integer.parseInt(id+"");
        return _id_.longValue();
    }

}
