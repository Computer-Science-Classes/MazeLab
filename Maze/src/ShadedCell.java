import java.awt.*;

import graphics.MazeCanvas;

/**
 * ShadedCell represents a cell in the maze with a shade
 */
public class ShadedCell extends Cell {
    protected Color shade;

    /**
     * Constructor for ShadedCell class
     * 
     * @param canvas - MazeCanvas object
     * @param row    - row index of cell
     * @param col    - column index of cell
     * @param shade  - the color of the cell
     */
    public ShadedCell(MazeCanvas canvas, int row, int col, Color shade) {
        super(canvas, row, col);
        this.shade = shade;
    }

    /**
     * Draws a cell with its color on the canvas
     */
    public void draw() {
        this.canvas.drawCell(this.row, this.col, this.shade);
        this.walls.forEach(wall -> this.canvas.drawWall(this.row, this.col, wall));
    }
}