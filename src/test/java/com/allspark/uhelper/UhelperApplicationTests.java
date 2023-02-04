package com.allspark.uhelper;



import cn.hutool.crypto.digest.BCrypt;
import com.allspark.uhelper.myenum.CollegeEnum;
import com.allspark.uhelper.utils.SnowFlake;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UhelperApplicationTests {

    @Test
    void contextLoads() {
//        long index = new SnowFlake().nextId();
//        System.out.println(index);
        System.out.println(BCrypt.hashpw("123456", BCrypt.gensalt()));
    }

//    @Autowired
//    public Test1Mapper test1Mapper;
//    @Test
//    public void testMyEnum() {
//        //Test1ServiceImpl test1Service = new Test1ServiceImpl();
//        Test1 test1 = new Test1();
//        test1.setId(7);
//        test1.setName(CollegeEnum.TONGGONG);
//        int a = test1Mapper.insert(test1);
//        //test1Service.add(test1);
//
//    }
//    @Test
//    public void test1(){
//        test1Mapper.test();
//    }
//
//    @Resource
//    public TClassMapper tClassMapper;
//
//    @Autowired
//    public TClassServiceImpl tClassService;
//    @Test
//    public void test2(){
//
//    }

}
