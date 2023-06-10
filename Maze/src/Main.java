import graphics.MazeCanvas;

public class Main {
    public static int ROWS = 25;
    public static int COLS = 40;
    public static int SIZE = 20;

    public static void main(String[] args) {
        MazeCanvas canvas = new MazeCanvas(ROWS, COLS, SIZE);
        Maze maze = new Maze(canvas);

        canvas.open();
        maze.genSnake();

        canvas.pause();
        canvas.close();
    }
}
