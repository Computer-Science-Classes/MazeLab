import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

/**
 * Cell represents a single cell of the maze
 * 
 * @param side
 */
public class Cell {
    protected int row, col;
    protected MazeCanvas canvas;
    protected List<Side> walls;

    protected boolean visited = false;

    // Fields for A*
    private double gCost;
    private double hCost;
    private Cell parent;

    /**
     * Constructor for Cell class
     * 
     * @param canvas - the MazeCanvas
     * @param row    - row index of the cell
     * @param col    - column index of the cell
     */
    public Cell(MazeCanvas canvas, int row, int col) {
        this.canvas = canvas;
        this.row = row;
        this.col = col;

        canvas.drawCell(row, col);

        this.walls = new ArrayList<Side>(
                Arrays.asList(Side.Top, Side.Bottom, Side.Left, Side.Right));

        // Initialize A* fields
        this.gCost = Double.MAX_VALUE;
        this.hCost = 0;
        this.parent = null;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public List<Side> getWalls() {
        // System.out.println(this.walls);
        return new ArrayList<Side>(this.walls);
    }

    public void removeWall(Side side) {
        this.walls.remove(side); // internal board
        // System.out.println(side);
        canvas.eraseWall(row, col, side); // gui board
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getGCost() {
        return gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    public double getHCost() {
        return hCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    // Calculate F cost: G cost + H cost
    public double fCost() {
        return getGCost() + getHCost();
    }
}
