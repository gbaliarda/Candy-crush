package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.frontend.level2.CandyFrame2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		CandyGame game = new CandyGame(Level2.class); // Carga el nivel 1 como juego
		CandyFrame2 frame = new CandyFrame2(game); // Carga la ventana en frame
		Scene scene = new Scene(frame); // Carga la ventana en la aplicacion
		primaryStage.setResizable(false); // No deja que se achique o agrande
		primaryStage.setScene(scene); // La ventana mostrara la aplicacion
		primaryStage.show(); // Abre la ventana
	}

}
