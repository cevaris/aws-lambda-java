package com.cevaris.awslambda.stringapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.cevaris.awslambda.stringapi.utils.AwsHandler;
import com.cevaris.awslambda.stringapi.utils.JsonUtils;
import com.cevaris.awslambda.stringapi.utils.StreamUtils;
import com.google.common.collect.Maps;

import org.junit.Assert;
import org.junit.Test;

public class ToUpperHandlerIntegrationTest {

  @Test
  public void testToUpperHandler() throws IOException {
    String funcName = "toUpperHandler";
    AwsHandler handler = new ToUpperHandler();
    Context context = new TestContext(funcName);


    Map body = Maps.newHashMap();
    body.put("value", "test");

    Map<String, Object> testData = Maps.newHashMap();
    testData.put("queryStringParameters", body);
    String requestString = JsonUtils.toJson(testData);

    InputStream input = StreamUtils.toInputStream(requestString);
    OutputStream output = new ByteArrayOutputStream();

    handler.handleRequest(input, output, context);

    String responseString = StreamUtils.fromOuputStream(output);
    Map actualJson = JsonUtils.fromJson(responseString);

    Map expectedJson = Maps.newHashMap();
    expectedJson.put("body", "{\"value\":\"TEST\"}");
    expectedJson.put("statusCode", 200);
    expectedJson.put("headers", Maps.newHashMap());

    Assert.assertEquals(expectedJson, actualJson);
  }

}