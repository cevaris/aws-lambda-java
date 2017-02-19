package com.cevaris.awslambda.stringapi.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StreamUtils {

  public static String fromOuputStream(OutputStream outputStream) {
    ByteArrayOutputStream b = (ByteArrayOutputStream) outputStream;
    return new String(b.toByteArray(), StandardCharsets.UTF_8);
  }

  public static InputStream toInputStream(String str) {
    return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
  }

}
