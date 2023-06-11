import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

/**
 * Maze represents the main structure of the maze with Cells
 */
public class Maze {

    public static final Color RED = Color.RED;
    public static final Color DARK_RED = new Color(150, 0, 0);

    private static final Color BlockCell = Color.LIGHT_GRAY;

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
        for (int row = 0; row < canvas.getRows(); row++) {
            for (int col = 0; col < canvas.getCols(); col++) {
                if (isOnEdge(row, col)) {
                    gridOfCells[row][col] = new EdgeCell(canvas, row, col, RED);
                    ((EdgeCell) gridOfCells[row][col]).draw();
                } else {
                    gridOfCells[row][col] = new Cell(canvas, row, col);
                }
            }
        }
        placeBlockCells();
        placeEntryAndExit();
    }

    private void placeBlockCells() {
        int totalInterorCells = (canvas.getRows() - 2) * (canvas.getCols() - 2);
        int count = (int) (0.05 * totalInterorCells);

        List<int[]> interiorCellLocations = new ArrayList<>();
        for (int row = 1; row < canvas.getRows() - 1; row++) {
            for (int col = 1; col < canvas.getCols() - 1; col++) {
                interiorCellLocations.add(new int[] { row, col });
            }
        }

        Random random = new Random();
        while (count > 0 && !interiorCellLocations.isEmpty()) {
            int index = random.nextInt(interiorCellLocations.size());
            int[] location = interiorCellLocations.get(index);
            int row = location[0];
            int col = location[1];

            gridOfCells[row][col] = new BlockCell(canvas, row, col, BlockCell);
            ((BlockCell) gridOfCells[row][col]).draw();
            interiorCellLocations.remove(index);
            count--;

            // remove adjacent interior cell locations
            List<int[]> adjacentCellLocations = getAdjacentLocations(row, col);
            interiorCellLocations.removeAll(adjacentCellLocations);
        }
    }

    /**
     * Places the entry and exit cells of the maze at random EdgeCell positions
     * the positions are not to be adjacent to a BlockCell and not to be on the same
     * edge
     * 
     * The entry cell is colored green and the exit cell is colored blue
     */
    private void placeEntryAndExit() {
        Random random = new Random();
        // computer random position for EntryCell and ExitCell, not on same edge
        int entryEdge = random.nextInt(4); // random edge
        int exitEdge;
        do {
            exitEdge = random.nextInt(4);
        } while (exitEdge == entryEdge);

        int entryPos, exitPos;
        int[] entryCoords, exitCoords;

        do {
            // Calculate random positions for EntryCell and ExitCell based on their edges
            entryPos = random.nextInt(canvas.getCols() - 2);
            exitPos = random.nextInt(canvas.getCols() - 2);

            entryCoords = getCoordinates(entryPos, entryEdge);
            exitCoords = getCoordinates(exitPos, exitEdge);
        } while (isNextToBlockCell(entryCoords[0], entryCoords[1]) || isNextToBlockCell(exitCoords[0], exitCoords[1]));

        gridOfCells[entryCoords[0]][entryCoords[1]] = new EntryCell(canvas, entryCoords[0], entryCoords[1],
                Color.GREEN);
        gridOfCells[exitCoords[0]][exitCoords[1]] = new ExitCell(canvas, exitCoords[0], exitCoords[1], Color.BLUE);
    }

    /**
     * Method to generate the snake pattern for testing the canvas
     */
    @Deprecated
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

    private List<int[]> getAdjacentLocations(int row, int col) {
        List<int[]> adjacentLocations = new ArrayList<>();
        adjacentLocations.add(new int[] { row - 1, col });
        adjacentLocations.add(new int[] { row + 1, col });
        adjacentLocations.add(new int[] { row, col - 1 });
        adjacentLocations.add(new int[] { row, col + 1 });
        return adjacentLocations;
    }

    /**
     * Calculates the coordinates of a cell on the given edge at the given position
     * 
     * @param pos
     * @param edge
     * @return
     */
    private int[] getCoordinates(int pos, int edge) {
        int row = 0, col = 0;
        switch (edge) {
            case 0: // top edge
                row = 0;
                col = pos;
                break;
            case 1: // right edge
                row = pos;
                col = canvas.getCols() - 1;
                break;
            case 2: // bottom edge
                row = canvas.getRows() - 1;
                col = pos;
                break;
            case 3:
                row = pos;
                col = 0;
                break;
        }
        return new int[] { row, col };
    }

    /**
     * Returns true if the cell is next to the given cell is a BlockCell
     * 
     * @param row
     * @param col
     * @param pos
     * @return
     */
    private boolean isNextToBlockCell(int row, int col) {
        // returns true if the cell next to the given cell is a block cell
        List<int[]> adjacentLocations = getAdjacentLocations(row, col);
        for (int[] location : adjacentLocations) {
            int adjRow = location[0];
            int adjCol = location[1];
            if (adjRow >= 0 && adjRow < canvas.getRows() && adjCol >= 0 && adjCol < canvas.getCols()
                    && gridOfCells[adjRow][adjCol] instanceof BlockCell) {
                return true;
            }
        }
        return false;
    }
}
