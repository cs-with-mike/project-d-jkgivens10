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

  /**
   * Constructs a Cell at the given row and column index.
   * Defaults to being a wall.
   *
   * @param r row index of this cell in the maze
   * @param c column index of this cell in the maze
   */
  public Cell(int r, int c) {
    this.r = r;
    this.c = c;
    this.isPath = false;
  }

  /**
   * Returns the row index of this cell.
   *
   * @return row index
   */
  public int rowIndex(){
    return r;
  }

  /**
   * Returns the column index of this cell.
   *
   * @return column index
   */
  public int colIndex(){
    return c;
  }

  /**
   * Returns whether this cell is a path.
   *
   * @return true if path, false if wall
   */
  public boolean isPath() {
    return isPath;
  }

  /**
   * Sets whether this cell is a path or wall.
   *
   * @param isPath true to make this cell a path, false for wall
   */
  public void setPath(boolean isPath) {
    this.isPath = isPath;
  }

  /**
   * Returns the display text for this cell based on its current state.
   *
   * @return PATH_TEXT if path, WALL_TEXT if wall
   */
  public String getText() {
    return isPath ? PATH_TEXT : WALL_TEXT;
  }

  /**
   * Returns the row index of the neighbor 2 steps in the given direction.
   * Throws IndexOutOfBoundsException if the result would be out of bounds.
   *
   * @param dir   direction to look
   * @param bound the size of the maze in the row dimension
   * @return row index of the neighbor cell
   * @throws IndexOutOfBoundsException if neighbor is out of bounds
   */
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

  /**
   * Returns the column index of the neighbor 2 steps in a given direction.
   * Throws IndexOutOfBoundsException if the result would be out of bounds.
   *
   * @param dir   direction to look
   * @param bound the size of the maze in the column dimension
   * @return column index of the neighbor cell
   * @throws IndexOutOfBoundsException if neighbor is out of bounds
   */
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

  /**
   * Returns a string representation of this cell showing its position and state.
   *
   * @return formatted string
   */
  @Override
  public String toString() {
    return String.format("Cell [%2d][%2d]: %s", r, c, isPath ? "PATH" : "WALL");
  }
}
