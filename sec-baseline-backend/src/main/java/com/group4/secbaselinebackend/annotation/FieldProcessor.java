package com.group4.secbaselinebackend.annotation;


public interface FieldProcessor {
    Object process(Object o, Object value, String fieldName);
}
