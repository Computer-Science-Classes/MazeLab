import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import graphics.MazeCanvas;

/**
 * Generator class that uses DFS to generate a random maze
 */
public class Generator {
    private Maze maze;
    private MazeCanvas canvas;
    private Random random = new Random();

    public Generator(Maze maze) {
        this.maze = maze;
        this.canvas = maze.getCanvas();
        this.random = new Random();
    }

    /**
     * Uses stack-based DFS to generate a random maze
     * 
     * @param currentRow
     * @param currentCol
     */
    public void generateMaze(int startRow, int startCol) {
        Stack<Cell> stack = new Stack<>();
        Cell startCell = maze.getCell(startRow, startCol);
        startCell.setVisited(true);
        stack.push(startCell);

        while (!stack.isEmpty()) {
            Cell currentCell = stack.peek();
            List<MazeCanvas.Side> directions = getShuffledDirections();
            boolean moved = false;

            for (MazeCanvas.Side side : directions) {
                int newRow = getNewRow(currentCell.getRow(), side);
                int newCol = getNewCol(currentCell.getCol(), side);

                if (newRow >= 0 && newRow < canvas.getRows()
                        && newCol >= 0 && newCol < canvas.getCols() && !maze.getCell(newRow, newCol).getVisited()) {
                    if (currentCell.getWalls().contains(side)) {
                        canvas.eraseWall(currentCell.getRow(), currentCell.getCol(), side);
                        canvas.step(); // makes it a step by step process per DFS round
                        currentCell.getWalls().remove(side);

                        Cell nextCell = maze.getCell(newRow, newCol);
                        MazeCanvas.Side oppositeSide = getOpposite(side);
                        if (nextCell.getWalls().contains(oppositeSide)) {
                            canvas.eraseWall(newRow, newCol, oppositeSide);
                            nextCell.getWalls().remove(oppositeSide);
                        }
                        nextCell.setVisited(true);
                        stack.push(nextCell);
                        moved = true;
                        break;
                    }
                }
            }
            if (!moved) {
                stack.pop();
            }
        }
    }

    private List<MazeCanvas.Side> getShuffledDirections() {
        List<MazeCanvas.Side> directions = new ArrayList<>();
        directions.add(MazeCanvas.Side.Left);
        directions.add(MazeCanvas.Side.Right);
        directions.add(MazeCanvas.Side.Top);
        directions.add(MazeCanvas.Side.Bottom);

        Collections.shuffle(directions, random);

        return directions;
    }

    private int getNewRow(int currentRow, MazeCanvas.Side side) {
        switch (side) {
            case Top:
                return currentRow - 1;
            case Bottom:
                return currentRow + 1;
            default:
                return currentRow;
        }
    }

    private int getNewCol(int currentCol, MazeCanvas.Side side) {
        switch (side) {
            case Left:
                return currentCol - 1;
            case Right:
                return currentCol + 1;
            default:
                return currentCol;
        }
    }

    private MazeCanvas.Side getOpposite(MazeCanvas.Side side) {
        switch (side) {
            case Top:
                return MazeCanvas.Side.Bottom;
            case Bottom:
                return MazeCanvas.Side.Top;
            case Left:
                return MazeCanvas.Side.Right;
            case Right:
                return MazeCanvas.Side.Left;
            default:
                return null;
        }
    }
}
