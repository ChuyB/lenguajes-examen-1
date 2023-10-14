package com.org.t_diagrams.components;

public class Interpreter {
  private final String baseLanguage;
  private final String language;

  public Interpreter(String baseLanguage, String language) {
    this.baseLanguage = baseLanguage;
    this.language = language;
  }

  public String getBaseLanguage() {
    return baseLanguage;
  }

  public String getLanguage() {
    return language;
  }
}
