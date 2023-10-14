package com.org.app.input;

import com.org.app.resources.Block;
import com.org.app.resources.Memory;

import java.util.*;

public class Input {

  private static void printHelp() {
    System.out.println("usage: -b | --blocks <number_of_blocks>");
    System.exit(0);
  }

  public static int getParameter(String[] args) {
    if (args.length < 2)
      printHelp();

    int param = 0;
    if (args[0].equals("-b") || args[0].equals("--blocks")) {
      param = Integer.parseInt(args[1]);
    } else
      printHelp();

    return param;
  }
  public static void execute(int numberOfBlocks) {
    Scanner scanner = new Scanner(System.in);
    String action;

    Memory memory = new Memory(numberOfBlocks);

    loop:
    while (true) {
      System.out.println("Ingrese la próxima instrucción");
      String[] res = scanner.nextLine().split(" ");
      action = res[0].toUpperCase();

      String name;
      switch (action) {
        case "RESERVAR":
          if (res.length != 3) {
            System.out.println("Ingrese la instrucción correctamente: RESERVAR <cantidad> <nombre>");
            break;
          }
          int size = Integer.parseInt(res[1]);
          name = res[2];
          if (size > numberOfBlocks) {
            System.out.println("No se puede reservar más memoria de la disponible, intente nuevamente");
            break;
          }
          memory.allocateMemory(name, size);
          break;

        case "MOSTRAR":
          HashMap<String, Block> listOfAllocated = memory.listOfAllocated;
          HashMap<Integer, ArrayList<Block>> listOfBlocks = memory.mapOfSizes;

          System.out.println("Lista de bloques");
          Map<Integer, ArrayList<Block>> sortedList = new TreeMap<>(listOfBlocks);
          sortedList.forEach((sizeOfBlocks, list) -> {
            System.out.printf("%d: ", sizeOfBlocks);
            list.forEach((block) -> System.out.printf("(%d, %d) ", block.start, block.end));
            System.out.println();
          });

          System.out.println("Memoria asignada");
          listOfAllocated.forEach((blockName, block) -> System.out.printf("%s: %s bloques\n", blockName, block.end - block.start + 1));
          break;

        case "LIBERAR":
          if (res.length != 2) {
            System.out.println("Ingrese la instrucción correctamente: LIBERAR <nombre>");
            break;
          }
          name = res[1];
          if (!memory.deallocateMemory(name))
            System.out.println("Este identificador no tiene memoria reservada");
          break;

        case "SALIR":
          System.out.println("Fin de la ejecución");
          break loop;

        default:
          break;
      }
    }
    scanner.close();
  }
}
