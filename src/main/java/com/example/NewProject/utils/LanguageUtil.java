package com.example.NewProject.utils;


import com.example.NewProject.domain.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class LanguageUtil {

  public static Language getCurrentLanguage() {
    return prepareLanguageFrom(LocaleContextHolder.getLocale());
  }

  public static Language prepareLanguageFrom(Locale locale) {
    return Language.valueOf(locale.getLanguage().toUpperCase());
  }
}
