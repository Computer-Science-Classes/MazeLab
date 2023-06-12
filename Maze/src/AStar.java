import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

/**
 * Uses A* alg to solve for maze
 */
public class AStar {
    // Initialize the open and closed sets
    private Maze maze;
    private PriorityQueue<Cell> openSet;
    private boolean[][] closedSet;
    private Cell[][] cells;
    private Cell start;
    private Cell goal;

    public AStar(Maze maze) {
        this.maze = maze;
        this.cells = new Cell[maze.getCanvas().getRows()][maze.getCanvas().getCols()];
        this.openSet = new PriorityQueue<>((cell1, cell2) -> Double.compare(cell1.fCost(), cell2.fCost()));
        this.closedSet = new boolean[maze.getCanvas().getRows()][maze.getCanvas().getCols()];
        this.start = maze.getStart();
        this.goal = maze.getGoal();
        initializeCells(maze);
    }

    /**
     * Sets up internal grid representation
     * 
     */
    private void initializeCells(Maze maze) {
        for (int row = 0; row < maze.getCanvas().getRows(); row++) {
            for (int col = 0; col < maze.getCanvas().getCols(); col++) {
                Cell cell = maze.getCell(row, col);
                cell.setHCost(heuristic(cell));
                this.cells[row][col] = cell;
            }
        }
        System.out.println("Start:" + start + "Goal:" + goal);
    }

    /**
     * Uses Manhattan distance as hueristic
     * 
     * @param cell
     * @return the distance as double
     */
    private double heuristic(Cell cell) {
        return Math.abs(cell.getRow() - goal.getRow()) + Math.abs(cell.getCol() - goal.getCol());
    }

    /**
     * Uses opening and closed sets to solve for the path to goal
     * 
     * @return
     */
    public List<Cell> findPath() {
        openSet.add(start);
        maze.getCanvas().step(500);

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();
            closedSet[current.getRow()][current.getCol()] = true;
            maze.getCanvas().drawCenter(current.getRow(), current.getCol(), Color.RED);
            maze.getCanvas().step(500);

            if (current.equals(goal)) {
                return reconstructPath();
            }

            for (Cell neighbor : getNeighbors(current)) {
                if (closedSet[neighbor.getRow()][neighbor.getCol()])
                    continue;
                double tentativeGCost = current.getGCost() + 1; // Cost to move is 1

                if (tentativeGCost < neighbor.getGCost()) {
                    neighbor.setParent(current);
                    neighbor.setGCost(tentativeGCost);
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                        maze.getCanvas().drawCenter(neighbor.getRow(), neighbor.getCol(), Color.YELLOW);
                    }
                }
                maze.getCanvas().step(500);
            }
        }
        // If no path is found, return an empty list
        return new ArrayList<>();
    }

    /**
     * Gets the neighboring cells for the current cell
     * 
     * @param cell - current cell
     * @return the neighboring cells in a list
     */
    private List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        // Directions: top, down, left, right
        int[] rowOffsets = { -1, 1, 0, 0 };
        int[] colOffsets = { 0, 0, -1, 1 };
        MazeCanvas.Side[] sides = { MazeCanvas.Side.Top, MazeCanvas.Side.Bottom, MazeCanvas.Side.Left,
                MazeCanvas.Side.Right };

        for (int i = 0; i < 4; i++) {
            int row = cell.getRow() + rowOffsets[i];
            int col = cell.getCol() + colOffsets[i];

            if (row >= 0 && row < cells.length && col >= 0 && col < cells[0].length) {
                Cell neighbor = cells[row][col];
                if (!cell.getWalls().contains(sides[i])) { // Check if there is a wall
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    /**
     * A* to reconstruct the path for every given call
     * 
     * @return the path back to findPath
     */
    private List<Cell> reconstructPath() {
        List<Cell> path = new ArrayList<>();
        Cell current = goal;

        while (current != null) {
            path.add(current);
            // Render path on the maze
            if (current.getParent() != null) {
                this.maze.getCanvas().drawPath(current.getRow(), current.getCol(), directionFromParent(current),
                        Color.RED);
            }
            current = current.getParent();
        }
        Collections.reverse(path);

        return path;
    }

    /**
     * Get the direction from the current cell
     */
    private Side directionFromParent(Cell current) {
        Cell parent = current.getParent();
        if (parent.getRow() < current.getRow()) {
            return Side.Top;
        } else if (parent.getRow() > current.getRow()) {
            return Side.Bottom;
        } else if (parent.getCol() < current.getCol()) {
            return Side.Left;
        } else {
            return Side.Right;
        }
    }

    public Maze getMaze() {
        return this.maze;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
}
