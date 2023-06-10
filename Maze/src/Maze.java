import java.awt.*;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

/**
 * Maze represents the main structure of the maze with Cells
 */
public class Maze {

    public static final Color RED = Color.RED;
    public static final Color DARK_RED = new Color(150, 0, 0);

    private MazeCanvas canvas;
    private Cell[][] gridOfCells;

    /**
     * Constructor for Maze class
     * 
     * @param canvas - the canvas to draw the maze
     */
    public Maze(MazeCanvas canvas) {
        this.canvas = canvas;
        this.gridOfCells = new Cell[canvas.getRows()][canvas.getCols()];
        initialize();
    }

    /**
     * Initializes the maze with Cells and EdgeCells
     */
    public void initialize() {
        for (int i = 0; i < canvas.getRows(); i++) {
            for (int j = 0; j < canvas.getCols(); j++) {
                if (isOnEdge(i, j)) {
                    gridOfCells[i][j] = new EdgeCell(canvas, i, j, RED);
                    ((EdgeCell) gridOfCells[i][j]).draw();
                } else {
                    gridOfCells[i][j] = new Cell(canvas, i, j);
                }
            }
        }
    }

    /**
     * Method to generate the snake pattern for testing the canvas
     */
    public void genSnake() {
        for (int row = 0; row < canvas.getRows(); row++) {
            for (int col = 0; col < canvas.getCols(); col++) {
                canvas.drawCell(row, col);

                canvas.eraseWall(row, col, Side.Top);
                canvas.eraseWall(row, col, Side.Bottom);

                if (row == 0) {
                    canvas.drawCenter(row, col, DARK_RED);
                    canvas.drawPath(row, col, Side.Bottom, RED);

                    if (col % 2 == 0) {
                        canvas.eraseWall(row, col, Side.Left);
                        canvas.drawPath(row, col, Side.Left, RED);
                    } else {
                        canvas.eraseWall(row, col, Side.Right);
                        canvas.drawPath(row, col, Side.Right, RED);
                    }
                } else if (row == canvas.getRows() - 1) {
                    canvas.drawCenter(row, col, DARK_RED);
                    canvas.drawPath(row, col, Side.Top, RED);

                    if (col % 2 == 1) {
                        canvas.eraseWall(row, col, Side.Right);
                        canvas.drawPath(row, col, Side.Right, RED);
                    } else {
                        canvas.eraseWall(row, col, Side.Left);
                        canvas.drawPath(row, col, Side.Left, RED);
                    }
                } else {
                    canvas.drawCenter(row, col, RED);
                    canvas.drawPath(row, col, Side.Top, RED);
                    canvas.drawPath(row, col, Side.Bottom, RED);
                }

            }
        }
    }

    public Cell getCell(int row, int col) {
        return this.gridOfCells[row][col];
    }

    /**
     * Check if a cell is on the edge of the grid
     * 
     * @param row
     * @param col
     * @return
     */
    private boolean isOnEdge(int row, int col) {
        return row == 0 || row == canvas.getRows() - 1 || col == 0 || col == canvas.getCols() - 1;
    }
}
