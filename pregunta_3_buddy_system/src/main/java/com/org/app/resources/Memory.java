package com.org.app.resources;

import java.util.ArrayList;
import java.util.HashMap;


public class Memory {
  private final int numberOfBlocks;

  public HashMap<Integer, ArrayList<Block>> mapOfSizes;

  public HashMap<String, Block> listOfAllocated;

  public Memory(int numberOfBlocks) {
    this.numberOfBlocks = numberOfBlocks;
    listOfAllocated = new HashMap<>();
    mapOfSizes = new HashMap<>();
    for (int i = 2; i <= numberOfBlocks; i = i * 2) {
      ArrayList<Block> listOfBlocks = new ArrayList<>();
      mapOfSizes.put(i, listOfBlocks);
    }

    Block root = new Block(0, numberOfBlocks - 1);
    mapOfSizes.get(numberOfBlocks).add(root);
  }

  public boolean allocateMemory(String name, int size) {
    if (size > numberOfBlocks)
      return false;

    if (listOfAllocated.get(name) != null){
      System.out.println("Ya se ha reservado memoria bajo este identificador");
      return false;
    }

    int blockSize = 2;
    while (blockSize < size)
      blockSize = blockSize * 2;

    ArrayList<Block> currentList = mapOfSizes.get(blockSize);

    int sizeOffset = 0;
    while (currentList.isEmpty()) {
      blockSize = blockSize * 2;
      if (blockSize > numberOfBlocks) {
        System.out.println("No hay espacios de memoria disponibles con el tamaño solicitado");
        return false;
      }
      currentList = mapOfSizes.get(blockSize);
      sizeOffset++;
    }

    Block availableBlock = currentList.getFirst();
    currentList.remove(availableBlock);

    while (sizeOffset > 0) {
      blockSize = blockSize / 2;
      int start = availableBlock.start;
      int end = availableBlock.end;
      Block leftBlock = new Block(start, start + blockSize - 1);
      Block rightBlock = new Block(start + blockSize, end);

      mapOfSizes.get(blockSize).add(rightBlock);

      availableBlock = leftBlock;
      sizeOffset--;
    }

    listOfAllocated.put(name, availableBlock);

    return true;
  }

  private void insertBuddies(Block block) {
    int size = block.end - block.start + 1;
    int buddyNumber = block.start / size;
    int buddyAddress = buddyNumber % 2 == 0 ? block.start + size : block.start - size;

    ArrayList<Block> listOfBlocks = mapOfSizes.get(size);
    Block blockInList = listOfBlocks.stream().filter(block::equals).findFirst().orElse(null);

    if (blockInList!=null)
      return;

    Block buddyBlock = listOfBlocks.stream().filter((bBlock) -> bBlock.start == buddyAddress).findFirst().orElse(null);

    if (buddyBlock != null) {
      Block coalescedBlock = buddyNumber % 2 == 0
          ? new Block(block.start, buddyBlock.end)
          : new Block(buddyBlock.start, block.end);
      listOfBlocks.remove(buddyBlock);
      insertBuddies(coalescedBlock);
    } else {
      mapOfSizes.get(size).add(block);
    }
  }

  public boolean deallocateMemory(String name) {
    Block block = listOfAllocated.get(name);

    if (block == null)
      return false;

    insertBuddies(block);
    listOfAllocated.remove(name);

    return true;
  }

  public int getNumberOfBlocks() {
    return numberOfBlocks;
  }
}
