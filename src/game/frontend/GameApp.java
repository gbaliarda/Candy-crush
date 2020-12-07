package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.frontend.level1.CandyFrameLevel1;
import game.frontend.level2.CandyFrameLevel2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private static AppMenu menu;

	@Override
	public void start(Stage primaryStage) {
		menu = new AppMenu();

		VBox mainFrame = new VBox();

		CandyGame gameLevel1 = new CandyGame(Level1.class); // Carga el nivel 1 como juego
		CandyGame gameLevel2 = new CandyGame(Level2.class); // Carga el nivel 2 como juego

		menu.getMenus().get(1).getItems().get(0).setOnAction(event -> primaryStage.setScene(createNewScene(new CandyFrameLevel1(gameLevel1))));

		menu.getMenus().get(1).getItems().get(1).setOnAction(event -> primaryStage.setScene(createNewScene(new CandyFrameLevel2(gameLevel2))));

		mainFrame.getChildren().addAll(menu, new CandyFrameLevel1(gameLevel1));
		Scene scene = new Scene(mainFrame); // Carga la ventana en la aplicacion
		primaryStage.setResizable(false); // No deja que se achique o agrande
		primaryStage.setScene(scene); // La ventana mostrara la aplicacion
		primaryStage.setTitle("Candy Crush POO");
		primaryStage.show(); // Abre la ventana
	}

	private Scene createNewScene(CandyFrame frame){
		VBox newMainFrame = new VBox();
		newMainFrame.getChildren().addAll(menu, frame);
		return new Scene(newMainFrame);
	}
}
