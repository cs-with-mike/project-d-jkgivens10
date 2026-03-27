/**
 * Westmont College Spring 2026
 * CS 030 Project D
 *
 * @author Assistant Professor Mike Ryu mryu@westmont.edu
 * @author Brennan Givens bgivens@westmont.edu
 */

package edu.westmont.cs030.maze;

/**
 * Represents a cell of a maze, which may be a wall or path.
 * <br>
 * This class has three (3) private instance variables:
 * <ul>
 *   <li><code>private boolean isPath</code> indicates whether this cell is a path or a wall (mutable)</li>
 *   <li><code>private final int r</code>this cell's row index in the maze</li>
 *   <li><code>private final int c</code>this cell's column index in the maze</li>
 * </ul>
 */
public class Cell {

  /**
   * Text to render for the cell if it's a "wall."
   */
  public static final String WALL_TEXT = "██";  //

  /**
   * Text to render for the cell if it's a "path."
   */
  public static final String PATH_TEXT = "  ";

  private boolean isPath;
  private final int r;
  private final int c;

  public Cell(int r, int c) {
    this.r = r;
    this.c = c;
    this.isPath = false;
  }

  public int rowIndex(){
    return r;
  }

  public int colIndex(){
    return c;
  }

  public boolean isPath() {
    return isPath;
  }

  public void setPath(boolean isPath) {
    this.isPath = isPath;
  }

  public String getText() {
    return isPath ? PATH_TEXT : WALL_TEXT;
  }

  public int getNeighborRowIndex(Direction dir, int bound) {
    int neighborRow = r;
    if (dir == Direction.NORTH) {
      neighborRow = r - 2;
    }
    if (dir == Direction.SOUTH) {
      neighborRow = r + 2;
    }

    if (neighborRow < 0 || neighborRow >= bound) {
      throw new IndexOutOfBoundsException("Neighbor row out of bounds: " + neighborRow);
    }
    return neighborRow;
  }


  public int getNeighborColIndex(Direction dir, int bound) {
    int neighborCol = c;
    if (dir == Direction.EAST) {
      neighborCol = c + 2;
    }
    if (dir == Direction.WEST) {
      neighborCol = c - 2;
    }

    if (neighborCol < 0 || neighborCol >= bound) {
      throw new IndexOutOfBoundsException("Neighbor row out of bounds: " + neighborCol);
    }
    return neighborCol;
  }

  @Override
  public String toString() {
    return String.format("Cell [%2d][%2d]: %s", r, c, isPath ? "PATH" : "WALL");
  }
}
