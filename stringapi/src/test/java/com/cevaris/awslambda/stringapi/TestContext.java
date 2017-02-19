package com.cevaris.awslambda.stringapi;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TestContext implements Context {
  private String functionName;
  private String awsRequestId;

  TestContext(String functionName) {
    this.functionName = functionName;
    this.awsRequestId = UUID.randomUUID().toString();
  }

  public String getAwsRequestId() {
    return awsRequestId;
  }

  public String getLogGroupName() {
    throw new NotImplementedException();
  }

  public String getLogStreamName() {
    throw new NotImplementedException();
  }

  public String getFunctionName() {
    return functionName;
  }

  public String getFunctionVersion() {
    throw new NotImplementedException();
  }

  public String getInvokedFunctionArn() {
    throw new NotImplementedException();
  }

  public CognitoIdentity getIdentity() {
    throw new NotImplementedException();
  }

  public ClientContext getClientContext() {
    throw new NotImplementedException();
  }

  public int getRemainingTimeInMillis() {
    throw new NotImplementedException();
  }

  public int getMemoryLimitInMB() {
    throw new NotImplementedException();
  }

  public LambdaLogger getLogger() {
    return new LambdaLogger() {
      public void log(String string) {
        System.out.println(string);
      }
    };
  }
}
