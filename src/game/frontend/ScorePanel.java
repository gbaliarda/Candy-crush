package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ScorePanel extends BorderPane {

	private final static Label SCORE = new Label("Puntaje: ");
	protected final HBox scorePanel;
	private final HBox scoreBox;


	public ScorePanel(long requiredScore) {
		setStyle("-fx-background-color: #5490ff; -fx-padding: 5px");
		scoreBox = new HBox();

		Label scoreLabel = new Label("0");
		String requiredScoreText = requiredScore < 0 ? "" : "/" + requiredScore;
		Label requiredScoreLabel = new Label(requiredScoreText);

		scorePanel = new HBox(25);

		scoreBox.getChildren().addAll(SCORE, scoreLabel, requiredScoreLabel);
		scorePanel.getChildren().addAll(scoreBox);
		scorePanel.setAlignment(Pos.CENTER);
		scorePanel.setStyle("-fx-font-size: 20");
		setCenter(scorePanel);
	}
	
	public void updateScore(String text) {
		((Label)scoreBox.getChildren().get(1)).setText(text);
	}

}