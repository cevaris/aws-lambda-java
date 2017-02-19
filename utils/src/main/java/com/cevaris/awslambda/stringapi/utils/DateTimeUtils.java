package com.cevaris.awslambda.stringapi.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeUtils extends org.joda.time.DateTimeUtils {

  public static DateTime now() {
    return org.joda.time.DateTime.now().withZone(DateTimeZone.UTC);
  }

}
