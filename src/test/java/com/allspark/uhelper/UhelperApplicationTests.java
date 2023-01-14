package com.allspark.uhelper;

import com.allspark.uhelper.domain.Test1;
import com.allspark.uhelper.mapper.Test1Mapper;
import com.allspark.uhelper.myenum.CollegeEnum;
import com.allspark.uhelper.service.impl.Test1ServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UhelperApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    public Test1Mapper test1Mapper;
    @Test
    public void testMyEnum() {
        //Test1ServiceImpl test1Service = new Test1ServiceImpl();
        Test1 test1 = new Test1();
        test1.setId(4);
        test1.setName(CollegeEnum.TONGGONG);
        int a = test1Mapper.insertAll(test1);
        //test1Service.add(test1);

    }
    @Test
    public void test1(){
        test1Mapper.test();
    }

}
