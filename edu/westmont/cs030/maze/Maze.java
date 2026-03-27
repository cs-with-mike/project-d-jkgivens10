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


  /**
   * Initializes the maze given the number of rows and columns.
   *
   * @param numRows number of rows
   * @param numCols number of columns
   * @throws IllegalArgumentException if numRows or numCols is less than 1
   */
  public Maze(int numRows, int numCols) {
    if(numRows < 1 || numCols < 1) {
      throw new IllegalArgumentException("Maze dimensions must be at least 1x1.");
    }
    cells = new Cell[numRows][numCols];
    initialize();
  }

  /**
   * Resets every cell in this maze to a new Cell configured as a wall.
   */
  public void initialize() {
    for (int r =0; r < numRows(); r++) {
      for (int c = 0; c < numCols(); c++) {
        cells[r][c] = new Cell(r,c);
      }
    }
  }


  /**
   * Returns the number of rows
   *
   * @return number of rows
   */
  public int numRows() {
    return cells.length;
  }

  /**
   * Returns the number of columns
   *
   * @return number of columns
   */
  public int numCols() {
    return cells[0].length;
  }

  /**
   * Given a Cell, returns a neighboring Cell in the given Direction.
   * Returns null if there is no neighbor that exists within the maze bounds.
   *
   * @param cell Cell whose neighbor in the given Direction is to be retrieved
   * @param dir  Direction of the neighbor from the perspective of the Cell given
   * @return the neighboring Cell if one exists, null otherwise
   */
  public Cell getNeighbor(Cell cell, Direction dir) {
    try {
      int neighborRow = cell.getNeighborRowIndex(dir, numRows());
      int neighborCol = cell.getNeighborColIndex(dir, numCols());
      return cells[neighborRow][neighborCol];
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  /**
   * Given a Cell, returns an ArrayList of all existing neighbors in all Directions.
   *
   * @param cell the Cell whose neighbors are to be retrieved
   * @return ArrayList of neighboring cells that exist within the maze bounds
   */
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

  /**
   * Given two neighboring Cells, sets the Cell between them as a path,
   *  connecting the two neighboring Cells together.
   *
   * @param origin   one of the two neighboring Cells
   * @param neighbor another one of the two neighboring Cells
   */
  public void connectNeighbors(Cell origin, Cell neighbor) {
    int wallRow = (origin.rowIndex() + neighbor.rowIndex()) / 2;
    int wallCol = (origin.colIndex() + neighbor.colIndex()) / 2;
    cells[wallRow][wallCol].setPath(true);
  }

  /**
   * Returns a string representation of this maze used for rendering.
   * Gives the maze a wall around it.
   *
   * @return string representation of this maze
   */
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

