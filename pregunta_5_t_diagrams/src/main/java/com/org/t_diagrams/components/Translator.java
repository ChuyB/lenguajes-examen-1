package com.org.t_diagrams.components;

public class Translator {
  private final String baseLanguage;
  private final String fromLanguage;
  private final String toLanguage;

  public Translator(String baseLanguage, String fromLanguage, String toLanguage) {
    this.baseLanguage = baseLanguage;
    this.fromLanguage = fromLanguage;
    this.toLanguage = toLanguage;
  }

  public String getBaseLanguage() {
    return baseLanguage;
  }

  public String getFromLanguage() {
    return fromLanguage;
  }

  public String getToLanguage() {
    return toLanguage;
  }
}
