package com.org.t_diagrams.components;

public class Program {
  /**
   * Program language
   */
  private final String name;
  /**
   * Execution language
   */
  private final String language;

  /**
   * Constructor
   * 
   * @param name     Program name
   * @param language Execution language
   */
  public Program(String name, String language) {
    this.name = name;
    this.language = language;
  }

  /**
   * Gets program name
   * 
   * @return program name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets language
   * 
   * @return execution language
   */
  public String getLanguage() {
    return language;
  }
}
