package com.cevaris.awslambda.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.cevaris.awslambda.models.ApiHttpRequest;
import com.cevaris.awslambda.models.ApiHttpResponse;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;

public abstract class AwsHandler implements RequestStreamHandler {

  private Injector localInjector;

  public final void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    LambdaLogger logger = context.getLogger();

    logger.log(String.format("Context.getFunctionName: %s\n", context.getFunctionName()));
    logger.log(String.format("Context.getAwsRequestId: %s\n", context.getAwsRequestId()));

    String requestRawJson = StreamUtils.fromInputStream(input);
    logger.log(String.format("Request raw json: %s\n", requestRawJson));

    ApiHttpRequest request = JsonUtils.fromJson(requestRawJson, ApiHttpRequest.class);
    logger.log(String.format("Request parsed instance: %s\n", request));

    ApiHttpResponse response = handleEvent(request, context);
    String responseStr = JsonUtils.toJson(response);

    logger.log(String.format("Response: %s\n", responseStr));
    output.write(responseStr.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Entry point into aws lambda handler
   */
  protected abstract ApiHttpResponse handleEvent(ApiHttpRequest request, Context context);

  /**
   * Load up any modules at runtime
   */
  protected List<Module> modules() {
    return Lists.newArrayList();
  }

  /**
   * getInstance wrapper around guice injector
   *
   * @param type class type to be loaded
   * @param <T> class type to be loaded
   * @return instance of class type
   */
  protected <T> T getInstance(Class<T> type) {
    if (localInjector == null) {
      List<Module> handlerModules = modules();
      if (handlerModule() != null) {
        handlerModules.add(handlerModule());
      }
      setInjector(Guice.createInjector(Stage.PRODUCTION, handlerModules));
    }
    return localInjector.getInstance(type);
  }

  /**
   * Swap out runtime injector for test/mocked loaded injector
   */
  @VisibleForTesting
  public void setInjector(Injector injector) {
    this.localInjector = injector;
  }

  /**
   * Handler specific inject module
   *
   * @return com.google.inject.Module
   */
  protected Module handlerModule() {
    return null;
  }

}