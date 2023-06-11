import java.awt.Color;

import graphics.MazeCanvas;

public class ExitCell extends EdgeCell {

    public ExitCell(MazeCanvas canvas, int row, int col, Color color) {
        super(canvas, row, col, color);
    }

    @Override
    public void draw() {
        canvas.drawCenter(row, col, Color.BLUE);
    }
}
