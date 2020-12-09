package game.frontend;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

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

    public Lighting getLightningEffect() {
        Light.Distant spotLight = new Light.Distant();
        spotLight.setColor(Color.AZURE);
        spotLight.setElevation(90);
        return new Lighting(spotLight);
    }
}
