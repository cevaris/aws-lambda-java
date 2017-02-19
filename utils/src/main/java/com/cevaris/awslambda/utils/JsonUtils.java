package com.cevaris.awslambda.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.cevaris.awslambda.models.ApiHttpException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

  private static ObjectMapper mapper = new ObjectMapper();

  public static String toJson(Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      ApiHttpException exception = new ApiHttpException();
      exception.setMessage(e.getMessage());
      throw new RuntimeException(exception.toJson());
    }
  }

  public static Map fromJson(InputStream input) throws IOException {
    return fromJson(input, Map.class);
  }

  public static <T> T fromJson(InputStream input, Class<T> clazz) throws IOException {
    return mapper.readValue(input, clazz);
  }

  public static Map fromJson(String str) throws IOException {
    return mapper.readValue(str, Map.class);
  }

  public static <T> T fromJson(String str, Class<T> clazz) throws IOException {
    return mapper.readValue(str, clazz);
  }

}
