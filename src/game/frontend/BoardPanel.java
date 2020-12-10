package game.frontend;

import javafx.scene.effect.Glow;
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

    public abstract void setLastPointEffect(int row, int col);

    public abstract void removeLastPointEffect(int row, int col);

    public Glow getFocusEffect() {
        Glow glow = new Glow();
        glow.setLevel(0.35);
        return glow;
    }
}
