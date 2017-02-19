package com.cevaris.awslambda.stringapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.cevaris.awslambda.models.ApiHttpRequest;
import com.cevaris.awslambda.models.ApiHttpResponse;
import com.cevaris.awslambda.utils.AwsHandler;
import com.cevaris.awslambda.utils.JsonUtils;
import com.cevaris.awslambda.utils.StreamUtils;

import org.apache.http.HttpStatus;
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

    String requestString = JsonUtils.toJson(request);
    InputStream input = StreamUtils.toInputStream(requestString);
    OutputStream output = new ByteArrayOutputStream();
    handler.handleRequest(input, output, context);

    String responseString = StreamUtils.fromOuputStream(output);
    ApiHttpResponse actualJson = JsonUtils.fromJson(responseString, ApiHttpResponse.class);

    ApiHttpResponse expectedJson = ApiHttpResponse.builder()
        .body("{\"value\":\"TEST\"}")
        .statusCode(200)
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

    String responseString = StreamUtils.fromOuputStream(output);
    ApiHttpResponse actualJson = JsonUtils.fromJson(responseString, ApiHttpResponse.class);

    ApiHttpResponse expectedJson = ApiHttpResponse.builder()
        .statusCode(HttpStatus.SC_BAD_REQUEST)
        .exception(new RuntimeException("missing value parameter"))
        .build();

    Assert.assertEquals(expectedJson.getStatusCode(), actualJson.getStatusCode());
    Assert.assertNotNull(actualJson.getStatusCode());
    Assert.assertNull(actualJson.getBody());
  }

}