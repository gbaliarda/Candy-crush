package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import game.backend.level.Level4;
import game.frontend.level1.CandyFrameLevel1;
import game.frontend.level2.CandyFrameLevel2;
import game.frontend.level3.CandyFrameLevel3;
import game.frontend.level4.CandyFrameLevel4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;

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
		CandyGame gameLevel3 = new CandyGame(Level3.class); // Carga el nivel 3 como juego
		CandyGame gameLevel4 = new CandyGame(Level4.class); // Carga el nivel 4 como juego

		menu.getMenus().get(1).getItems().get(0).setOnAction(event -> primaryStage.setScene(createNewScene(new CandyFrameLevel1(gameLevel1))));

		menu.getMenus().get(1).getItems().get(1).setOnAction(event -> primaryStage.setScene(createNewScene(new CandyFrameLevel2(gameLevel2))));

		menu.getMenus().get(1).getItems().get(2).setOnAction(event -> primaryStage.setScene(createNewScene(new CandyFrameLevel3(gameLevel3))));

		menu.getMenus().get(1).getItems().get(3).setOnAction(event -> {
			if (CandyFrameLevel4.isTimerRunning()) {
				CandyFrameLevel4.getTimer().cancel();
				CandyFrameLevel4.cancelTimer();
				CandyFrameLevel4.setTimer(new Timer());
			}
			primaryStage.setScene(createNewScene(new CandyFrameLevel4(gameLevel4)));
		});

		mainFrame.getChildren().addAll(menu, new CandyFrameLevel1(gameLevel1));
		Scene scene = new Scene(mainFrame); // Carga la ventana en la aplicacion
		primaryStage.setResizable(false); // No deja que se achique o agrande
		primaryStage.setScene(scene); // La ventana mostrara la aplicacion
		primaryStage.setTitle("Candy Crush POO");
		primaryStage.getIcons().add(new Image("./images/LogoCandy.png"));
		primaryStage.show(); // Abre la ventana
	}

	private Scene createNewScene(CandyFrame frame){
		VBox newMainFrame = new VBox();
		newMainFrame.getChildren().addAll(menu, frame);
		return new Scene(newMainFrame);
	}
}
