package com.org.app;

import com.org.app.input.Input;

public class BuddySystem {

  public static void main(String[] args) {
    int numberOfBlocks = Input.getParameter(args);
    if (numberOfBlocks < 0) {
      System.out.println("La cantidad de bloques debe ser un número positivo");
      System.exit(0);
    }
    if ((numberOfBlocks & (numberOfBlocks - 1)) != 0) {
      System.out.println("La cantidad de bloques debe ser una potencia de 2");
      System.exit(0);
    }

    Input.execute(numberOfBlocks);
  }
}
