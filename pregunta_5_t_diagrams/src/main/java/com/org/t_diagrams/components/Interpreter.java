package com.org.t_diagrams.components;

/**
 * Interpreter
 */
public class Interpreter {
  /**
   * Execution language
   */
  private final String baseLanguage;
  /**
   * Interpreted language
   */
  private final String language;

  /**
   * Constructor
   * 
   * @param baseLanguage Execution language
   * @param language     Interpreted language
   */
  public Interpreter(String baseLanguage, String language) {
    this.baseLanguage = baseLanguage;
    this.language = language;
  }

  /**
   * Returns base language
   * 
   * @return base language
   */
  public String getBaseLanguage() {
    return baseLanguage;
  }

  /**
   * Get language
   * 
   * @return language
   */
  public String getLanguage() {
    return language;
  }
}
