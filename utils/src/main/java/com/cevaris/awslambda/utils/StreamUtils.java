package com.cevaris.awslambda.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.google.common.io.CharStreams;

public class StreamUtils {


  public static String fromInputStream(InputStream inputStream) throws IOException {
    return CharStreams.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
  }

  public static String fromOuputStream(OutputStream outputStream) {
    ByteArrayOutputStream b = (ByteArrayOutputStream) outputStream;
    return new String(b.toByteArray(), StandardCharsets.UTF_8);
  }

  public static InputStream toInputStream(String str) {
    return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
  }

}
