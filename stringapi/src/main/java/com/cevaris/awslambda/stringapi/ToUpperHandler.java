package com.cevaris.awslambda.stringapi;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.cevaris.awslambda.models.ApiHttpException;
import com.cevaris.awslambda.models.ApiHttpRequest;
import com.cevaris.awslambda.models.ApiHttpResponse;
import com.cevaris.awslambda.utils.AwsHandler;
import com.google.common.base.Preconditions;

import org.apache.commons.httpclient.HttpStatus;

public class ToUpperHandler extends AwsHandler {

  @Override
  protected ApiHttpResponse handleEvent(ApiHttpRequest request, Context context) {
    Preconditions.checkNotNull(request);

    LambdaLogger logger = context.getLogger();

    logger.log("invoking function\n");

    Map<String, String> params = request.getQueryStringParameters();

    ApiHttpResponse response = new ApiHttpResponse();

    if (params != null && params.containsKey("value")) {
      String value = params.get("value").toUpperCase();
      ToUpperHandlerResponse body = new ToUpperHandlerResponse(value);
      response.setBody(body.toJson());
      response.setStatusCode(HttpStatus.SC_OK);
    } else {
      ApiHttpException exception = new ApiHttpException();
      exception.setMessage("missing value query parameter");
      exception.setRequestId(context.getAwsRequestId());
      response.setBody(exception.toJson());
      response.setStatusCode(HttpStatus.SC_BAD_REQUEST);
    }
    return response;
  }

}
