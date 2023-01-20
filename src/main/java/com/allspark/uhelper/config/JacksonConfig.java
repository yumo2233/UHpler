package com.allspark.uhelper.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName JacksonConfig
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/20 21:38
 * @Version 1.0
 **/
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.getSerializerProvider().setNullValueSerializer(new NullValueSerializer());
        return objectMapper;
    }

}

/**
 * 空值序列化
 */
class NullValueSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String fieldName = gen.getOutputContext().getCurrentName();
        //反射获取字段
        Field field = ReflectUtil.getField(gen.getCurrentValue().getClass(), fieldName);
        if (ObjectUtil.isNotNull(field)){
            // 数字类型Integer、Double、Long等返回null
            if (Number.class.isAssignableFrom(field.getType())){
                gen.writeNull();
                return;
            }
            if (BigDecimal.class.isAssignableFrom(field.getType())) {
                gen.writeNumber(new BigDecimal("0"));
            }
            //String类型返回""
            if (Objects.equals(field.getType(), String.class)) {
                gen.writeString("");
                return;
            }
            //List类型返回[]
            if (Objects.equals(field.getType(), List.class)) {
                gen.writeStartArray();
                gen.writeEndArray();
                return;
            }
            //其他Object默认返回{}
            gen.writeStartObject();
            gen.writeEndObject();
        }
    }
}

