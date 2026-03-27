/**
 * Westmont College Spring 2026
 * CS 030 Project D
 *
 * @author Assistant Professor Mike Ryu mryu@westmont.edu
 * @author Brennan Givens bgivens@westmont.edu
 */

package edu.westmont.cs030.maze;

import java.util.ArrayList;

/**
 * Represents a maze as a 2D array of {@link Cell}s.
 */
public class Maze {

  /**
   * 2D array of Cells to represent the maze.
   */
  public final Cell[][] cells;


  public Maze(int numRows, int numCols) {
    if(numRows < 1 || numCols < 1) {
      throw new IllegalArgumentException("Maze dimensions must be at least 1x1.");
    }
    cells = new Cell[numRows][numCols];
    initialize();
  }

  public void initialize() {
    for (int r =0; r < numRows(); r++) {
      for (int c = 0; c < numCols(); c++) {
        cells[r][c] = new Cell(r,c);
      }
    }
  }


  public int numRows() {
    return cells.length;
  }

  public int numCols() {
    return cells[0].length;
  }

  public Cell getNeighbor(Cell cell, Direction dir) {
    try {
      int neighborRow = cell.getNeighborRowIndex(dir, numRows());
      int neighborCol = cell.getNeighborColIndex(dir, numCols());
      return cells[neighborRow][neighborCol];
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }


  public ArrayList<Cell> getNeighbors(Cell cell) {
    ArrayList<Cell> neighbors = new ArrayList<>();
    for (Direction dir : Direction.values()) {
      Cell neighbor = getNeighbor(cell, dir);
      if (neighbor != null) {
        neighbors.add(neighbor);
      }
    }
    return neighbors;
  }

  public void connectNeighbors(Cell origin, Cell neighbor) {
    int wallRow = (origin.rowIndex() + neighbor.rowIndex()) / 2;
    int wallCol = (origin.colIndex() + neighbor.colIndex()) / 2;
    cells[wallRow][wallCol].setPath(true);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int borderWidth = numCols() + 2;

    sb.append(Cell.WALL_TEXT.repeat(borderWidth)).append("\n");

    for (int r = 0; r < numRows(); r++) {
      sb.append(Cell.WALL_TEXT);
      for (int c = 0; c < numCols(); c++) {
        sb.append(cells[r][c].getText());
      }
      sb.append(Cell.WALL_TEXT).append("\n");
    }

    sb.append(Cell.WALL_TEXT.repeat(borderWidth)).append("\n");

    return sb.toString();
  }
}

