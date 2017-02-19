package com.cevaris.awslambda.stringapi;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.cevaris.awslambda.stringapi.utils.AwsHandler;
import com.cevaris.awslambda.stringapi.utils.JsonUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import org.apache.http.HttpStatus;


public class ToUpperHandler extends AwsHandler {

  @Override
  protected String handleEvent(Map request, Context context) {
    Preconditions.checkNotNull(request);

    LambdaLogger logger = context.getLogger();

    logger.log("invoking function\n");

    Map params = (Map) request.getOrDefault("queryStringParameters", Maps.newHashMap());

    String value = "empty";
    if (params.containsKey("value")) {
      value = (String) params.get("value");
      value = value.toUpperCase();
    }

    Map<String, Object> resposneBody = Maps.newHashMap();
    resposneBody.put("value", value);

    Map<String, Object> resposne = Maps.newHashMap();
    resposne.put("statusCode", HttpStatus.SC_OK);
    resposne.put("body", JsonUtils.toJson(resposneBody));
    resposne.put("headers", Maps.newHashMap());
    String json = JsonUtils.toJson(resposne);
    logger.log(String.format("response $%s\n", json));
    return json;
  }

}
