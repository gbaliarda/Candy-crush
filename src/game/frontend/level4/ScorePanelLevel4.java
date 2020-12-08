package game.frontend.level4;

import game.frontend.ScorePanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScorePanelLevel4 extends ScorePanel {

    private static final Label BOMBS_LEFT = new Label("Bombas restantes: ");
    private static final Label TIME_LEFT = new Label("Tiempo restante: ");

    private Label cellsLabel;
    private Label timeLabel = new Label();
    private HBox cellsBox;

    public ScorePanelLevel4(long maxMoves, long timeBombsLeft, int initialSeconds) {
        super(maxMoves);

        cellsLabel = new Label(String.valueOf(timeBombsLeft));
        HBox cellHBox= new HBox();
        cellHBox.getChildren().addAll(BOMBS_LEFT, cellsLabel);
        cellHBox.setAlignment(Pos.CENTER);

        timeLabel.setText(String.valueOf(initialSeconds));
        HBox timeHBox= new HBox();
        timeHBox.getChildren().addAll(TIME_LEFT, timeLabel);
        timeHBox.setAlignment(Pos.CENTER);

        cellsBox = new HBox(20);
        cellsBox.getChildren().addAll(cellHBox, timeHBox);
        cellsBox.setAlignment(Pos.CENTER);
        cellsBox.setStyle("-fx-font-size: 20");
        setBottom(cellsBox);
    }

    public void updateTimer() {
        ((Label)(((HBox)cellsBox.getChildren().get(1))).getChildren().get(1)).setText(String.valueOf(Integer.parseInt(timeLabel.getText()) - 1));
    }

    public void addSeconds(int seconds) {
        ((Label)(((HBox)cellsBox.getChildren().get(1))).getChildren().get(1)).setText(String.valueOf(Integer.parseInt(timeLabel.getText()) + seconds));
    }

    public void updateBombsLeft(String bombs){
        ((Label)(((HBox)cellsBox.getChildren().get(0))).getChildren().get(1)).setText(bombs);
    }

}
