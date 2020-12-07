package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ScorePanel extends BorderPane {

	private final static Label PUNTAJE = new Label("Puntaje: ");
	private Label scoreLabel;
	private HBox puntaje;

	public ScorePanel() {
		setStyle("-fx-background-color: #5490ff");
		scoreLabel = new Label("0");
		puntaje = new HBox();
		puntaje.getChildren().addAll(PUNTAJE, scoreLabel);
		puntaje.setAlignment(Pos.CENTER);
		puntaje.setStyle("-fx-font-size: 20");
		setCenter(puntaje);
	}
	
	public void updateScore(String text) {
		((Label)puntaje.getChildren().get(1)).setText(text);
	}

}