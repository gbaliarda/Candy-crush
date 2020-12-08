package game.frontend.level1;

import game.frontend.ScorePanel;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScorePanelLevel1 extends ScorePanel {

    private final static Label MOVES_LEFT = new Label("Movimientos restantes: ");
    private final HBox movesLeft;
    private long moves;

    public ScorePanelLevel1(long maxMoves) {
        super();

        movesLeft = new HBox();
        moves = maxMoves;

        Label movesLabel = new Label(String.valueOf(moves));
        movesLeft.getChildren().addAll(MOVES_LEFT, movesLabel);

        scorePanel.getChildren().add(movesLeft);
    }

    public void updateMovesLeft() {
        ((Label)movesLeft.getChildren().get(1)).setText(String.valueOf(--moves));
    }
}
