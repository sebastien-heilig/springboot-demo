package com.heilig.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author sebastien.heilig
 * @since 1.0.0-SNAPSHOT
 */
public class DateUtils {

  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

  // Hide constructor - Util class
  private DateUtils() {
  }

  public static String format(LocalDate localDate) {

    Objects.requireNonNull(localDate, "The given date cannot be null!");
    return localDate.format(FORMATTER);
  }

  public static LocalDate parse(String localDateString) {

    Objects.requireNonNull(localDateString, "The given String cannot be null!");
    return LocalDate.parse(localDateString);
  }

}
