package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ScorePanel extends BorderPane {

	private final static Label SCORE = new Label("Puntaje:");
	private final static Label MOVES_LEFT = new Label("Movimientos restantes:");
	private Label scoreLabel, movesLabel;
	private HBox scorePanel;
	private long moves;

	public ScorePanel(long maxMoves) {
		setStyle("-fx-background-color: #5490ff");
		HBox movesLeft = new HBox();
		HBox score = new HBox();

		scoreLabel = new Label("0");
		moves = maxMoves;
		movesLabel = new Label(String.valueOf(moves));
		scorePanel = new HBox(25);
		movesLeft.getChildren().addAll(MOVES_LEFT, movesLabel);
		score.getChildren().addAll(SCORE, scoreLabel);
		scorePanel.getChildren().addAll(score,movesLeft);
		scorePanel.setAlignment(Pos.CENTER);
		scorePanel.setStyle("-fx-font-size: 20");
		setCenter(scorePanel);
	}
	
	public void updateScore(String text) {
		((Label)((HBox)scorePanel.getChildren().get(0)).getChildren().get(1)).setText(text);
	}

	public void updateMovesLeft() {
		((Label)((HBox)scorePanel.getChildren().get(1)).getChildren().get(1)).setText(String.valueOf(--moves));
	}

}