package game.frontend.level2;

import game.frontend.level1.ScorePanelLevel1;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScorePanelLevel2 extends ScorePanelLevel1 {

    private static final Label CELLS_LEFT = new Label("Celdas restantes: ");
    private final HBox cellsBox;

    public ScorePanelLevel2(long maxMoves, int size){
        super(maxMoves);
        Label cellsLabel = new Label(String.valueOf(size * size));
        cellsBox = new HBox();
        cellsBox.getChildren().addAll(CELLS_LEFT, cellsLabel);
        cellsBox.setAlignment(Pos.CENTER);
        cellsBox.setStyle("-fx-font-size: 20");
        setBottom(cellsBox);
    }

    public void updateCellsLeft(String cells){
        ((Label)cellsBox.getChildren().get(1)).setText(cells);
    }
}
