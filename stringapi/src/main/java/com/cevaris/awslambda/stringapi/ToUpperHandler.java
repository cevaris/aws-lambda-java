package com.cevaris.awslambda.stringapi;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.cevaris.awslambda.models.ApiHttpRequest;
import com.cevaris.awslambda.models.ApiHttpResponse;
import com.cevaris.awslambda.utils.AwsHandler;
import com.google.common.base.Preconditions;

import org.apache.http.HttpStatus;


public class ToUpperHandler extends AwsHandler {

  @Override
  protected ApiHttpResponse handleEvent(ApiHttpRequest request, Context context) {
    Preconditions.checkNotNull(request);

    LambdaLogger logger = context.getLogger();

    logger.log("invoking function\n");

    Map<String, String> params = request.getQueryStringParameters();

    ToUpperHandlerResponse toUpperResponse = new ToUpperHandlerResponse();
    if (params.containsKey("value")) {
      String value = params.get("value").toUpperCase();
      toUpperResponse.setValue(value);
    }

    ApiHttpResponse response = ApiHttpResponse.builder()
        .body(toUpperResponse.toJson())
        .statusCode(HttpStatus.SC_OK)
        .build();

    return response;
  }

}
