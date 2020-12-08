package game.frontend.level3;

import game.frontend.ScorePanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ScorePanelLevel3 extends ScorePanel{

    private static final Label TIME_BOMB_LEFT = new Label("Bombas restantes: ");

    private final HBox bombBox;
    protected HBox cellsBox = new HBox(20);

    public ScorePanelLevel3(long timeBombsLeft){
        super();

        Label cellsLabel = new Label(String.valueOf(timeBombsLeft));
        bombBox = new HBox();
        bombBox.getChildren().addAll(TIME_BOMB_LEFT, cellsLabel);

        cellsBox.setStyle("-fx-font-size: 20");
        cellsBox.setAlignment(Pos.CENTER);
        cellsBox.getChildren().add(bombBox);
        setBottom(cellsBox);
    }

    public void updateBombsLeft(String bombs){
        ((Label)bombBox.getChildren().get(1)).setText(bombs);
    }

}
