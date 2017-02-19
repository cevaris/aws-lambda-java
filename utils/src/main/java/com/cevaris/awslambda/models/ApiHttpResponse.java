package com.cevaris.awslambda.models;

import java.util.Map;

import com.cevaris.awslambda.utils.JsonSerializable;
import com.cevaris.awslambda.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {
 * "body": "A JSON string of the request payload."
 * "headers": {Incoming request headers}
 * "statusCode": "HTTP status code"
 * }
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApiHttpResponse implements JsonSerializable {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Exception exception;
  private Integer statusCode;
  private Map<String, String> headers;
  private String body;

  @Override
  public String toString() {
    return toJson();
  }

  @Override
  public String toJson() {
    return JsonUtils.toJson(this);
  }

}
