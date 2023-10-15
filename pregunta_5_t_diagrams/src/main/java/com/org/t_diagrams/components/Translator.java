package com.org.t_diagrams.components;

/**
 * Translator
 */
public class Translator {
  /**
   * Execution language
   */
  private final String baseLanguage;
  /**
   * Translatable language
   */
  private final String fromLanguage;
  /**
   * Final language
   */
  private final String toLanguage;

  /**
   * Constructor
   * 
   * @param baseLanguage Execution language
   * @param fromLanguage Language to be translated
   * @param toLanguage   Language after translation
   */
  public Translator(String baseLanguage, String fromLanguage, String toLanguage) {
    this.baseLanguage = baseLanguage;
    this.fromLanguage = fromLanguage;
    this.toLanguage = toLanguage;
  }

  /**
   * Gets execution language
   * 
   * @return base language
   */
  public String getBaseLanguage() {
    return baseLanguage;
  }

  /**
   * Gets language to be translated
   * 
   * @return Language to be translated
   */
  public String getFromLanguage() {
    return fromLanguage;
  }

  /**
   * Gets language after translation
   * 
   * @return Language after translation
   */
  public String getToLanguage() {
    return toLanguage;
  }
}
