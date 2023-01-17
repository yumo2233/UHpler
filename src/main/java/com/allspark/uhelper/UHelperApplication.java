package com.allspark.uhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class UHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(UHelperApplication.class, args);
    }

}
