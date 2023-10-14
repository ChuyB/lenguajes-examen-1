package com.org.t_diagrams.components;

public class Program {
  private final String name;
  private final String language;

  public Program(String baseLanguage, String language) {
    this.name = baseLanguage;
    this.language = language;
  }

  public String getName() {
    return name;
  }

  public String getLanguage() {
    return language;
  }
}
