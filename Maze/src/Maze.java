import java.awt.*;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Maze {

    public static final Color RED = Color.RED;
    public static final Color DARK_RED = new Color(150, 0, 0);
    private MazeCanvas canvas;

    public Maze(MazeCanvas canvas) {
        this.canvas = canvas;
    }

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
}
