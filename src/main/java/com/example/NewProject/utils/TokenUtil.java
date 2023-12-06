package com.example.NewProject.utils;

import com.example.NewProject.exception.ExpiredTokenException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TokenUtil {

  public static void validateTokenTime(LocalDateTime createDate, Long tokenValidityInMinutes) {
    if (LocalDateTime.now().isAfter(createDate.plusMinutes(tokenValidityInMinutes))) {
      throw new ExpiredTokenException("Token expired");
    }
  }
}
