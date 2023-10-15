package com.org.app.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests suite
 */
public class MemoryTest {

  @Test
  @DisplayName("Allocates memory correctly")
  public void allocatesCorrectly() {
    Memory memory = new Memory(128);
    boolean res = memory.allocateMemory("mock", 30);

    Assertions.assertTrue(res);
    Assertions.assertNotNull(memory.listOfAllocated.get("mock"));
    Assertions.assertFalse(memory.mapOfSizes.get(32).isEmpty());
    Assertions.assertTrue(memory.mapOfSizes.get(128).isEmpty());
  }

  @Test
  @DisplayName("Doesn't allocate more memory than available")
  public void doesntAllocatesAboveMax() {
    Memory memory = new Memory(64);
    boolean res = memory.allocateMemory("mock", 65);

    Assertions.assertFalse(res);
  }

  @Test
  @DisplayName("Doesn't reallocate memory")
  public void doesntReallocate() {
    Memory memory = new Memory(16);
    memory.allocateMemory("mock", 8);
    boolean res = memory.allocateMemory("mock", 16);

    Assertions.assertFalse(res);
  }

  @Test
  @DisplayName("No space available for request")
  public void noSpaceAvailable() {
    Memory memory = new Memory(16);
    memory.allocateMemory("mock-1", 8);
    boolean res = memory.allocateMemory("mock-2", 16);

    Assertions.assertFalse(res);
  }

  @Test
  @DisplayName("Returns number of blocks")
  public void returnsNumberOfBlocks() {
    int numberOfBlocks = 128;
    Memory memory = new Memory(numberOfBlocks);
    Assertions.assertEquals(numberOfBlocks, memory.getNumberOfBlocks());
  }

  @Test
  @DisplayName("Deallocates correctly")
  public void deallocatesCorrectly() {
    Memory memory = new Memory(128);
    memory.allocateMemory("mock", 64);
    boolean res = memory.deallocateMemory("mock");

    Assertions.assertTrue(res);
    Assertions.assertNull(memory.listOfAllocated.get("mock"));
    Assertions.assertTrue(memory.mapOfSizes.get(64).isEmpty());
    Assertions.assertEquals(1, memory.mapOfSizes.get(128).size());
  }

  @Test
  @DisplayName("Doesn't deallocate free space")
  public void doesntDeallocateFreeSpace() {
    Memory memory = new Memory(16);
    boolean res = memory.deallocateMemory("mock");
    Assertions.assertFalse(res);
    Assertions.assertFalse(memory.mapOfSizes.get(16).isEmpty());
  }

  @Test
  @DisplayName("Coalesce two blocks")
  public void coalesceBlocks() {
    Memory memory = new Memory(32);
    memory.allocateMemory("mock-1", 8);
    memory.allocateMemory("mock-2", 8);
    memory.allocateMemory("mock-3", 8);
    memory.allocateMemory("mock-4", 8);

    memory.deallocateMemory("mock-3");
    memory.deallocateMemory("mock-4");

    Assertions.assertFalse(memory.mapOfSizes.get(16).isEmpty());
  }

  @Test
  @DisplayName("Don't coalesce two blocks")
  public void dontCoalesceBlocks() {
    Memory memory = new Memory(32);
    memory.allocateMemory("mock-1", 8);
    memory.allocateMemory("mock-2", 8);
    memory.allocateMemory("mock-3", 8);
    memory.allocateMemory("mock-4", 8);

    memory.deallocateMemory("mock-2");
    memory.deallocateMemory("mock-4");

    Assertions.assertEquals(2, memory.mapOfSizes.get(8).size());
  }
}
