package com.org.t_diagrams.components;

import java.util.ArrayList;
import java.util.List;

/**
 * T Diagram representation
 */
public class Diagram {
  private final ArrayList<Program> programs;
  private final ArrayList<Interpreter> interpreters;
  private final ArrayList<Translator> translators;

  /**
   * Constructor
   */
  public Diagram() {
    programs = new ArrayList<>();
    interpreters = new ArrayList<>();
    translators = new ArrayList<>();
  }

  /**
   * Defines a program
   * 
   * @param name     Name of the program
   * @param language Execution language
   */
  public void defineProgram(String name, String language) {
    if (programs.stream().filter((program -> program.getName().equals(name)))
        .findFirst()
        .orElse(null) != null) {
      System.out.println("Ya existe definido un programa con el mismo nombre");
      return;
    }

    Program program = new Program(name, language);
    programs.add(program);
    System.out.printf("Se definió el programa '%s', ejecutable en '%s'\n", name, language);
  }

  /**
   * Defines a interpreter
   * 
   * @param baseLanguage Execution language
   * @param language     Interpreted language
   */
  public void defineInterpreter(String baseLanguage, String language) {
    Interpreter definedInterpreter = interpreters
        .stream()
        .filter(interpreter -> interpreter.getBaseLanguage().equals(baseLanguage)
            && interpreter.getLanguage().equals(language))
        .findFirst()
        .orElse(null);
    if (definedInterpreter != null) {
      System.out.println("Ya existe un interprete igual definido");
      return;
    }

    Interpreter interpreter = new Interpreter(baseLanguage, language);
    interpreters.add(interpreter);
    System.out.printf("Se definió un intérprete para '%s', escrito en '%s'\n", language, baseLanguage);
  }

  /**
   * Deifnes a translator
   * 
   * @param baseLanguage Execution language
   * @param fromLanguage Initial language
   * @param toLanguage   Translated language
   */
  public void defineTranslator(String baseLanguage, String fromLanguage, String toLanguage) {
    Translator definedTranslator = translators
        .stream()
        .filter(translator -> translator.getFromLanguage().equals(fromLanguage)
            && translator.getToLanguage().equals(toLanguage) && translator.getBaseLanguage().equals(baseLanguage))
        .findFirst()
        .orElse(null);

    if (definedTranslator != null) {
      System.out.println("Ya existe un traductor igual definido");
      return;
    }

    Translator translator = new Translator(baseLanguage, fromLanguage, toLanguage);
    translators.add(translator);
    System.out.printf("Se definió un traductor de '%s' hacia '%s', escrito en '%s'\n", fromLanguage, toLanguage,
        baseLanguage);
  }

  /**
   * Checks if a language is executable
   * 
   * @param name  Name of the language
   * @param block Blocks langauges that are checked before (prevents loops)
   * @return true if language is executable, false otherwise
   */
  private boolean execRecursive(String name, String block) {
    if (name.equalsIgnoreCase("LOCAL"))
      return true;

    // Finds any executable interpreter
    List<Interpreter> interpreterList = interpreters
        .stream()
        .filter(interpreter -> interpreter.getLanguage().equals(name) && !interpreter.getBaseLanguage().equals(block))
        .toList();

    List<Interpreter> execInts = interpreterList
        .stream()
        .filter(interpreter -> execRecursive(interpreter.getBaseLanguage(), block))
        .toList();

    if (!execInts.isEmpty())
      return true;

    // Finds any executable translator
    List<Translator> translatorList = translators
        .stream()
        .filter(translator -> translator.getFromLanguage().equals(name))
        .toList();

    List<Translator> execTrans = translatorList
        .stream()
        .filter(translator -> execRecursive(translator.getBaseLanguage(), translator.getFromLanguage()))
        .toList();

    if (!execTrans.isEmpty()) {
      List<Translator> translated = execTrans
          .stream()
          .filter(translator -> execRecursive(translator.getToLanguage(), translator.getFromLanguage()))
          .toList();

      return !translated.isEmpty();
    }

    return false;
  }

  /**
   * Checks if a program is executable
   * 
   * @param programName Name of the program
   */
  public void executable(String programName) {
    Program program = programs.stream().filter(programS -> programS.getName().equals(programName)).findFirst()
        .orElse(null);
    if (program == null) {
      System.out.printf("No existe un programa '%s' definido\n", programName);
      return;
    }

    boolean isExecutable = execRecursive(program.getLanguage(), null);

    if (isExecutable) {
      System.out.printf("Sí, es posible ejecutar el programa '%s'\n", programName);
    } else {
      System.out.printf("No es posible ejecutar el programa '%s'\n", programName);
    }
  }

  public ArrayList<Program> getPrograms() {
    return programs;
  }

  public ArrayList<Interpreter> getInterpreters() {
    return interpreters;
  }

  public ArrayList<Translator> getTranslators() {
    return translators;
  }
}
