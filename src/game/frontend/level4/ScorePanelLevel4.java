package game.frontend.level4;

import game.frontend.level3.ScorePanelLevel3;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScorePanelLevel4 extends ScorePanelLevel3 {

    private static final Label TIME_LEFT = new Label("Tiempo restante: ");

    private final Label timeLabel = new Label();
    private final HBox timeBox;

    public ScorePanelLevel4(long timeBombsLeft, int initialSeconds, long requiredScore) {
        super(timeBombsLeft, requiredScore);

        timeLabel.setText(String.valueOf(initialSeconds));
        timeBox= new HBox();
        timeBox.getChildren().addAll(TIME_LEFT, timeLabel);
        timeBox.setAlignment(Pos.CENTER);

        cellsBox.getChildren().add(timeBox);

    }

    public void updateTimer() {
        ((Label)timeBox.getChildren().get(1)).setText(String.valueOf(Integer.parseInt(timeLabel.getText()) - 1));
    }

    public void addSeconds(int seconds) {
        ((Label)timeBox.getChildren().get(1)).setText(String.valueOf(Integer.parseInt(timeLabel.getText()) + seconds));
    }

}
