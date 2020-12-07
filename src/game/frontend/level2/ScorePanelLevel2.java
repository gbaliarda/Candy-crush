package game.frontend.level2;

import game.frontend.ScorePanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScorePanelLevel2 extends ScorePanel {

    private static final Label CELDAS_RESTANTES = new Label("Celdas restantes: ");
    private Label cellsLeft;
    private HBox celdasBox;

    public ScorePanelLevel2(long maxMoves, int size){
        super(maxMoves);
        cellsLeft = new Label(String.valueOf(size * size));

        celdasBox = new HBox();
        celdasBox.getChildren().addAll(CELDAS_RESTANTES, cellsLeft);
        celdasBox.setAlignment(Pos.CENTER);
        celdasBox.setStyle("-fx-font-size: 20");
        setLeft(celdasBox);
    }

    public void updateCellsLeft(String cells){
        ((Label)celdasBox.getChildren().get(1)).setText(cells);
    }
}
