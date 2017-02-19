package com.cevaris.awslambda.stringapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.cevaris.awslambda.models.ApiHttpException;
import com.cevaris.awslambda.models.ApiHttpRequest;
import com.cevaris.awslambda.models.ApiHttpResponse;
import com.cevaris.awslambda.utils.AwsHandler;
import com.cevaris.awslambda.utils.JsonUtils;
import com.cevaris.awslambda.utils.StreamUtils;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class ToUpperHandlerIntegrationTest {
  private String funcName = "toUpperHandler";

  @Test
  public void testToUpperHandler() throws IOException {
    AwsHandler handler = new ToUpperHandler();
    Context context = new TestContext(funcName);

    ApiHttpRequest request = new ApiHttpRequest();
    request.addQueryParameters("value", "test");

    String requestString = request.toJson();
    InputStream input = StreamUtils.toInputStream(requestString);
    OutputStream output = new ByteArrayOutputStream();
    handler.handleRequest(input, output, context);

    String responseString = StreamUtils.fromOutputStream(output);
    ApiHttpResponse actualJson = JsonUtils.fromJson(responseString, ApiHttpResponse.class);

    ApiHttpResponse expectedJson = ApiHttpResponse.builder()
        .body("{\"value\":\"TEST\"}")
        .statusCode(HttpStatus.SC_OK)
        .build();

    Assert.assertEquals(expectedJson, actualJson);
  }

  @Test
  public void testToUpperHandlerMissingParam() throws IOException {
    AwsHandler handler = new ToUpperHandler();
    Context context = new TestContext(funcName);

    ApiHttpRequest request = new ApiHttpRequest();
    String requestString = JsonUtils.toJson(request);
    InputStream input = StreamUtils.toInputStream(requestString);
    OutputStream output = new ByteArrayOutputStream();

    handler.handleRequest(input, output, context);

    String responseString = StreamUtils.fromOutputStream(output);
    ApiHttpResponse actualJson = JsonUtils.fromJson(responseString, ApiHttpResponse.class);

    ApiHttpException exception = new ApiHttpException();
    exception.setMessage("missing value query parameter");
    exception.setRequestId(context.getAwsRequestId());

    ApiHttpResponse expectedJson = ApiHttpResponse.builder()
        .body(exception.toJson())
        .statusCode(HttpStatus.SC_BAD_REQUEST)
        .build();

    Assert.assertEquals(expectedJson, actualJson);

  }

}