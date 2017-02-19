package com.cevaris.awslambda.models;

import java.util.Map;

import com.cevaris.awslambda.utils.JsonSerializable;
import com.cevaris.awslambda.utils.JsonUtils;
import com.google.common.collect.Maps;

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
 * "httpMethod": "Incoming request's method name"
 * "isBase64Encoded": "A boolean flag to indicate if the applicable request payload is Base64-encode"
 * "path": "Path parameter",
 * "pathParameters":  {path parameters}
 * "queryStringParameters": {query string parameters }
 * "requestContext": {Request context, including authorizer-returned key-value pairs}
 * "resource": "Resource path",
 * "stageVariables": {Applicable stage variables}
 * }
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApiHttpRequest implements JsonSerializable {

  private Map<String, String> headers;
  private Map<String, String> pathParameters;
  private Map<String, String> queryStringParameters;
  private Map<String, Object> requestContext;
  private Map<String, String> stageVariables;

  private String body;
  private String httpMethod;
  private String isBase64Encoded;
  private String path;
  private String resource;

  public String addQueryParameters(String name, String value) {
    if (queryStringParameters == null) {
      queryStringParameters = Maps.newHashMap();
    }
    return queryStringParameters.put(name, value);
  }

  @Override
  public String toString() {
    return toJson();
  }

  @Override
  public String toJson() {
    return JsonUtils.toJson(this);
  }
}
