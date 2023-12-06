package com.example.NewProject.utils;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

public class DateTimeUtil {

  public static Instant setDateTimeIfNotExists(Instant localDateTime) {
    if (localDateTime != null) {
      return localDateTime;
    }
    return Instant.now();
  }

  public static Date calculateExpiryDate(int expiryDateInMinutes) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(Calendar.MINUTE, expiryDateInMinutes);
    return new Date(cal.getTime().getTime());
  }
}
