package com.cevaris.awslambda.stringapi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

public class JsonUtils {

  private static ObjectMapper mapper = new ObjectMapper();

  public static String toJson(Object obj) {
    String json;
    try {
      json = mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      Map<String, Object> error = Maps.newHashMap();
      error.put("message", e.getMessage());
      json = JsonUtils.toJson(error);
    }
    return json;
  }

  public static Map fromJson(InputStream input) throws IOException {
    return mapper.readValue(input, Map.class);
  }

  public static Map fromJson(String str) throws IOException {
    return mapper.readValue(str, Map.class);
  }

  public static <T> T fromJson(String str, Class<T> clazz) throws IOException {
    return mapper.readValue(str, clazz);
  }

}
