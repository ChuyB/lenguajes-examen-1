package com.org.app.resources;

/**
 * Memory block
 */
public class Block {
  /**
   * Start index
   */
  public int start;
  /**
   * End index
   */
  public int end;

  /**
   * Constructor
   * 
   * @param start start index
   * @param end   end index
   */
  public Block(int start, int end) {
    this.start = start;
    this.end = end;
  }
}
