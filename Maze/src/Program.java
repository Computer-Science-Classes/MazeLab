import graphics.MazeCanvas;

public class Program {
    public static int ROWS = 25;
    public static int COLS = 40;
    public static int SIZE = 20;

    public static void main(String[] args) {
        MazeCanvas canvas = new MazeCanvas(ROWS, COLS, SIZE);
        Maze maze = new Maze(canvas);

        canvas.open();

        System.out.println("Initializers' maze");
        maze.initialize();
        maze.printMaze();

        maze.generateMaze();

        System.out.println("A*s' Maze");
        maze.solveMaze();
        maze.printMaze();

        canvas.pause();
        canvas.close();
    }
}
