package game.frontend.level3;

import game.frontend.BoardPanel;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class BoardPanelLevel3 extends BoardPanel {

    private final StackPane[][] level3Cells;

    public BoardPanelLevel3(final int rows, final int columns, final int cellSize){
        super(rows, columns, cellSize);
        this.level3Cells = new StackPane[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                level3Cells[i][j] = new StackPane();
                level3Cells[i][j].getChildren().add(new ImageView());
                DropShadow dropShadow = new DropShadow();
                dropShadow.setRadius(3.0);
                dropShadow.setOffsetX(3.0);
                dropShadow.setOffsetY(3.0);
                dropShadow.setColor(Color.ORANGERED);

                Text text = new Text();
                text.setFont(Font.font("Impact", FontWeight.BOLD, 40));
                text.setFill(Color.BLACK);
                text.setEffect(dropShadow);
                level3Cells[i][j].getChildren().add(text);
                getChildren().add(level3Cells[i][j]);
            }
        }
    }

    @Override
    public void setLastPointEffect(int row, int col) {
        level3Cells[row][col].getChildren().get(0).setEffect(getLightningEffect());
    }

    @Override
    public void removeLastPointEffect(int row, int col) {
        level3Cells[row][col].getChildren().get(0).setEffect(null);
    }

    @Override
    public void setImage(int row, int column, Image image) {
        ((ImageView)(level3Cells[row][column].getChildren().get(0))).setImage(image);
    }

    public void setText(int row, int column, String text) {
        ((Text)(level3Cells[row][column].getChildren().get(1))).setText(text);
    }

}
