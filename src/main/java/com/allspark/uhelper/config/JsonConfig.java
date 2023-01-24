package com.allspark.uhelper.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Configuration
public class JsonConfig {
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory()
                .withSerializerModifier(new MyBeanSerializerModifier()));
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializerProvider.setNullValueSerializer(new CustomizeNullJsonSerializer
                .NullObjectJsonSerializer());
        return objectMapper;
    }

    /**
     * 自定义null值序列化处理器
     */
    public static class CustomizeNullJsonSerializer {

        /**
         * 处理数组集合类型的null值
         */
        public static class NullArrayJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            }
        }

        /**
         * 处理字符串类型的null值
         */
        public static class NullStringJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString("");
            }
        }

        /**
         * 处理数值类型的null值
         */
        public static class NullNumberJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeNumber(0);
            }
        }

        /**
         * 处理boolean类型的null值
         */
        public static class NullBooleanJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeBoolean(false);
            }
        }

        /**
         * 处理实体对象类型的null值
         */
        public static class NullObjectJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeEndObject();
            }
        }

    }

    /**
     * <pre>
     * 此modifier主要做的事情为：
     * 1.当序列化类型为数组集合时，当值为null时，序列化成[]
     * 2.String类型值序列化为""
     *
     * </pre>
     */
    public static class MyBeanSerializerModifier extends BeanSerializerModifier {

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                         BeanDescription beanDesc,
                                                         List<BeanPropertyWriter> beanProperties) {
            // 循环所有的beanPropertyWriter
            for (BeanPropertyWriter writer : beanProperties) {
                // 判断字段的类型，如果是数组或集合则注册nullSerializer
                if (isArrayType(writer)) {
                    // 给writer注册一个自己的nullSerializer
                    writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullArrayJsonSerializer());
                }
                if (isStringType(writer)) {
                    writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullStringJsonSerializer());
                }
                if (isNumberType(writer)) {
                    writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullNumberJsonSerializer());
                }
                if (isBooleanType(writer)) {
                    writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullBooleanJsonSerializer());
                }
            }
            return beanProperties;
        }

        /**
         * 是否是数组
         */
        private boolean isArrayType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是String
         */
        private boolean isStringType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是数值类型
         */
        private boolean isNumberType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return Number.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是boolean
         */
        private boolean isBooleanType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.equals(Boolean.class);
        }

    }


}

