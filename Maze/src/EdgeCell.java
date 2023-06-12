import java.awt.Color;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class EdgeCell extends ShadedCell {

    public EdgeCell(MazeCanvas canvas, int row, int col, Color color) {
        super(canvas, row, col, color);

        // para rows
        if (this.row == 0)
            this.walls.remove(Side.Top); // si las rows al principio
        if (this.row == canvas.getRows() - 1) // si rows al final
            this.walls.remove(Side.Bottom);

        // para cols
        if (this.col == 0)
            this.walls.remove(Side.Left); // si las cols al principio
        if (this.col == canvas.getCols() - 1)
            this.walls.remove(Side.Right); // si las cols al final
    }
}
