package com.allspark.uhelper.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

/**
 * @ClassName SwaagerConfig
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/15 0:55
 * @Version 1.0
 **/
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI createRestApi(Environment environment){
        // 设置环境范围
        Profiles profiles = Profiles.of("dev","test");
        // 如果在该环境返回内则返回：true，反之返回 false
        boolean flag = environment.acceptsProfiles(profiles);

        return new OpenAPI()
                .info(new Info().title("UHelper")
                        .description("UHelper API ")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/macrozheng/mall-learning")))
                .externalDocs(new ExternalDocumentation()
                        .description("uhelper前后端沟通")
                        .url("http://www.macrozheng.com"));



    }
}
