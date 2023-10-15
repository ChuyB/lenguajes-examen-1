package com.org.t_diagrams.components;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Tests suite
 */
public class DiagramTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  @DisplayName("Define new program")
  public void defineNewProgram() {
    Diagram diagram = new Diagram();

    String programName = "mock";
    String progLanguage = "mockLang";

    diagram.defineProgram(programName, progLanguage);
    List<Program> programs = diagram.getPrograms();
    Program newProgram = programs
        .stream()
        .filter(
            program -> program.getName().equals(programName) && program.getLanguage().equals(progLanguage))
        .findFirst()
        .orElse(null);
    Assertions.assertNotNull(newProgram);
  }

  @Test
  @DisplayName("Catch create new program when already exists")
  public void catchCreateNewProgramWhenAlreadyExists() {
    Diagram diagram = new Diagram();

    String name = "mock";
    String language = "mockLanguage";

    Program program = new Program(name, language);
    diagram.getPrograms().add(program);

    diagram.defineProgram(name, language);
    Assertions.assertEquals(
        "Ya existe definido un programa con el mismo nombre",
        outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Define new interpreter")
  public void defineNewInterpreter() {
    Diagram diagram = new Diagram();

    String baseLanguage = "mockBaseLanguage";
    String language = "mockLanguage";

    diagram.defineInterpreter(baseLanguage, language);
    List<Interpreter> interpreters = diagram.getInterpreters();
    Interpreter newInterpreter = interpreters
        .stream()
        .filter(
            interpreter -> interpreter.getBaseLanguage().equals(baseLanguage)
                && interpreter.getLanguage().equals(language))
        .findFirst()
        .orElse(null);
    Assertions.assertNotNull(newInterpreter);
  }

  @Test
  @DisplayName("Catch create new interpreter when already exists")
  public void catchCreateNewInterpreterWhenAlreadyExists() {
    Diagram diagram = new Diagram();

    String baseLanguage = "mockBaseLanguage";
    String language = "mockLanguage";

    Interpreter interpreter = new Interpreter(baseLanguage, language);
    diagram.getInterpreters().add(interpreter);

    diagram.defineInterpreter(baseLanguage, language);
    Assertions.assertEquals(
        "Ya existe un interprete igual definido",
        outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Define new translator")
  public void defineNewTranslator() {
    Diagram diagram = new Diagram();

    String baseLanguage = "mockLang";
    String fromLanguage = "mockFrom";
    String toLanguage = "mockTo";

    diagram.defineTranslator("mockLang", "mockFrom", "mockTo");
    List<Translator> translators = diagram.getTranslators();
    Translator newTranslator = translators
        .stream()
        .filter(
            translator -> translator.getBaseLanguage().equals(baseLanguage)
                && translator.getFromLanguage().equals(fromLanguage)
                && translator.getToLanguage().equals(toLanguage))
        .findFirst()
        .orElse(null);
    Assertions.assertNotNull(newTranslator);
  }

  @Test
  @DisplayName("Catch create new translator when already exists")
  public void catchCreateNewTranslatorWhenAlreadyExists() {
    Diagram diagram = new Diagram();

    String baseLanguage = "mockLang";
    String fromLanguage = "mockFrom";
    String toLanguage = "mockTo";

    Translator translator = new Translator(baseLanguage, fromLanguage, toLanguage);
    diagram.getTranslators().add(translator);

    diagram.defineTranslator(baseLanguage, fromLanguage, toLanguage);
    Assertions.assertEquals(
        "Ya existe un traductor igual definido",
        outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Determines if a program is executable")
  public void determinesIfProgramIsExecutable() {
    Diagram diagram = new Diagram();
    String programName = "prog";
    String languageA = "a";
    String languageB = "b";

    Program program = new Program(programName, languageA);
    Interpreter interpreter = new Interpreter(languageB, languageA);
    Translator translator = new Translator("LOCAL", languageB, "LOCAL");
    diagram.getPrograms().add(program);
    diagram.getInterpreters().add(interpreter);
    diagram.getTranslators().add(translator);

    diagram.executable(programName);
    Assertions.assertEquals(
        "Sí, es posible ejecutar el programa 'prog'",
        outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Determines if a program is not executable")
  public void determinesIfProgramIsNotExecutable() {
    Diagram diagram = new Diagram();
    String programName = "prog";
    String languageA = "a";

    Program program = new Program(programName, languageA);
    diagram.getPrograms().add(program);

    diagram.executable(programName);
    Assertions.assertEquals(
        "No es posible ejecutar el programa 'prog'",
        outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Program not found")
  public void programNotFound() {
    Diagram diagram = new Diagram();
    String programName = "prog";
    diagram.executable(programName);
    Assertions.assertEquals(
        "No existe un programa 'prog' definido",
        outputStreamCaptor.toString().trim());
  }

  @Test
  @DisplayName("Interpreter and translator don't go in loop")
  public void interpreterAndTranslatorNotInLoop() {
    Diagram diagram = new Diagram();
    String programName = "prog";
    String languageA = "a";
    String languageB = "b";

    Program program = new Program(programName, languageA);
    Interpreter interpreter = new Interpreter(languageB, languageA);
    Translator translator = new Translator("LOCAL", languageB, languageA);
    diagram.getPrograms().add(program);
    diagram.getInterpreters().add(interpreter);
    diagram.getTranslators().add(translator);

    diagram.executable(programName);
    Assertions.assertEquals(
        "No es posible ejecutar el programa 'prog'",
        outputStreamCaptor.toString().trim());
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }
}
