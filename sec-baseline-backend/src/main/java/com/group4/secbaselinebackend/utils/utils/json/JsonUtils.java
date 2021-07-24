package com.group4.secbaselinebackend.utils.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author feng xl
 * @date 2021/7/18 0018 10:32
 */
public class JsonUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final ObjectWriter objectWriter = objectMapper.writer();

    public static String objectToJsonString(Object value) throws JsonProcessingException {
        return objectWriter.writeValueAsString(value);
    }
}
