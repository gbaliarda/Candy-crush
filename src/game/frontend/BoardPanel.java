package game.frontend;

import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;

public abstract class BoardPanel extends TilePane {

    public BoardPanel(final int rows, final int columns, final int cellSize){
        setPrefRows(rows);
        setPrefColumns(columns);
        setPrefTileHeight(cellSize);
        setPrefTileWidth(cellSize);
    }

    public abstract void setImage(int row, int column, Image image);
}
