package game.frontend.level3;

import game.frontend.ScorePanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ScorePanelLevel3 extends ScorePanel{

    private static final Label TIME_BOMB_LEFT = new Label("Bombas restantes: ");

    private Label cellsLabel;
    private VBox cellsBox;

    public ScorePanelLevel3(long maxMoves, long timeBombsLeft){
        super(maxMoves);
        cellsLabel = new Label(String.valueOf(timeBombsLeft));
        HBox cellHBox= new HBox();
        cellHBox.getChildren().addAll(TIME_BOMB_LEFT, cellsLabel);
        cellHBox.setAlignment(Pos.CENTER);
        cellsBox = new VBox();
        cellsBox.getChildren().addAll(cellHBox);
        cellsBox.setAlignment(Pos.CENTER);
        cellsBox.setStyle("-fx-font-size: 20");
        setBottom(cellsBox);
    }

    public void updateBombsLeft(String bombs){
        ((Label)(((HBox)cellsBox.getChildren().get(0))).getChildren().get(1)).setText(bombs);
    }

}
