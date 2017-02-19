package com.cevaris.awslambda.models;

import com.cevaris.awslambda.utils.JsonSerializable;
import com.cevaris.awslambda.utils.JsonUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {
 * "errorType": "BadRequest",
 * "httpStatus": 400,
 * "requestId": "aws-request-id-2342342-2342-34234",
 * "message": "That, was a terrible request"
 * }
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApiHttpException implements JsonSerializable {

  private String requestId = "unknown";
  private String message;

  @Override
  public String toString() {
    return toJson();
  }

  @Override
  public String toJson() {
    return JsonUtils.toJson(this);
  }

}
