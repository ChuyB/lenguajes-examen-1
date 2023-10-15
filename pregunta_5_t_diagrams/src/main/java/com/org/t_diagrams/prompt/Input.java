package com.org.t_diagrams.prompt;

import com.org.t_diagrams.components.Diagram;
import java.util.Scanner;

/**
 * Manages user input
 */
public class Input {

  /**
   * Defines a new program
   * 
   * @param diagram T Diagram
   * @param args    Input arguments
   */
  private static void define(Diagram diagram, String[] args) {
    if (args.length > 3) {
      String program = args[1];
      if (program.equalsIgnoreCase("PROGRAMA")) {
        diagram.defineProgram(args[2], args[3]);
        return;
      }
      if (program.equalsIgnoreCase("INTERPRETE")) {
        diagram.defineInterpreter(args[2], args[3]);
        return;
      }
      if (program.equalsIgnoreCase("TRADUCTOR"))
        diagram.defineTranslator(args[2], args[3], args[4]);

    } else
      System.out.println("Uso: DEFINIR <tipo> [<argumentos>]");

  }

  /**
   * Checks if a program is executable
   * 
   * @param diagram T Diagram
   * @param args    Input arguments
   */
  private static void executable(Diagram diagram, String[] args) {
    if (args.length == 2) {
      String name = args[1];
      diagram.executable(name);
    } else
      System.out.println("Uso: EJECUTABLE <nombre>");
  }

  /**
   * Asks for user input, executes corresponding actions
   */
  public static void exec() {
    Scanner scanner = new Scanner(System.in);
    String[] res;
    String action = "";
    Diagram diagram = new Diagram();

    while (!action.equalsIgnoreCase("SALIR")) {
      System.out.println("Ingrese una acción");
      res = scanner.nextLine().split(" ");
      action = res[0].toUpperCase();

      switch (action) {
        case "DEFINIR":
          define(diagram, res);
          break;

        case "EJECUTABLE":
          executable(diagram, res);
          break;

        case "SALIR":
        default:
          break;
      }

    }

    scanner.close();
    System.out.println("Fin de la ejecución");
  }
}
