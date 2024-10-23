package com.example.demo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Objects;


/**
 *
 * 不需要序列化Long的属性上加上注解
 *
 * 示例
 * @JsonSerialize(using = LongJsonSerializer.class)
 * private long total;
 */
public class JsonLongSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.nonNull(value)) {
            jsonGenerator.writeNumber(value);
        }
    }
}
