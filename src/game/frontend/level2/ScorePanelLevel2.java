package game.frontend.level2;

import game.frontend.ScorePanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScorePanelLevel2 extends ScorePanel {

    private static final Label CELLS_LEFT = new Label("Celdas restantes: ");
    private Label cellsLabel;
    private VBox cellsBox;

    public ScorePanelLevel2(long maxMoves, int size){
        super(maxMoves);
        cellsLabel = new Label(String.valueOf(size * size));
        HBox cellHBox= new HBox();
        cellHBox.getChildren().addAll(CELLS_LEFT, cellsLabel);
        cellHBox.setAlignment(Pos.CENTER);
        cellsBox = new VBox();
        cellsBox.getChildren().addAll(cellHBox);
        cellsBox.setAlignment(Pos.CENTER);
        cellsBox.setStyle("-fx-font-size: 20");
        setBottom(cellsBox);
    }

    public void updateCellsLeft(String cells){
        ((Label)(((HBox)cellsBox.getChildren().get(0))).getChildren().get(1)).setText(cells);
    }
}
