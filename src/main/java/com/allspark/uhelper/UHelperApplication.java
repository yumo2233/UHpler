package com.allspark.uhelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication()
public class UHelperApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(UHelperApplication.class, args);
    }

}
