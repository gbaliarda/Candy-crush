package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;

import game.frontend.level2.ScorePanelLevel2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private ImageManager images;
	private Point2D lastPoint;
	private CandyGame game;

	public CandyFrame(CandyGame game) {
		this.game = game;
		AppMenu menu = new AppMenu();
		getChildren().add(menu); // Agrega el menu de arriba
		images = new ImageManager(); // Mapa de nombre de la bomba como key y la imagen como valor
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
		getChildren().add(boardPanel); // Agrega el panel a la ventana
		scorePanel = new ScorePanel(); // Crea el panel de score
		getChildren().add(scorePanel); // Agrega el score
		game.initGame(); // Llena el tablero de caramelos
		GameListener listener; // Espera y lee lo que ocurre en el tablero
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(100);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);
						Element element = cell.getContent();
						Image image = images.getImage(element);
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null)));
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image)));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}
			@Override
			public void cellExplosion(Element e) {
				//
			}
		});

		listener.gridUpdated();

		// Mover caramelos y si gano por x motivo
		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (lastPoint == null) {
				lastPoint = translateCoords(event.getX(), event.getY());
				System.out.println("Get first = " +  lastPoint);
			} else {
				Point2D newPoint = translateCoords(event.getX(), event.getY());
				if (newPoint != null) {
					System.out.println("Get second = " +  newPoint);
					game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());
					String message = ((Long)game().getScore()).toString();
					if (game().isFinished()) { // Si el game termina y es loser pero despues llegas a la condicion de ganado, ganas.
						if (game().playerWon()) {
							message = message + " Finished - Player Won!";
						} else {
							message = message + " Finished - Loser !";
						}
					}
					scorePanel.updateScore(message);
					lastPoint = null;
				}
			}
		});

	}

	private CandyGame game() {
		return game;
	}

	// Funcion que obtiene las coordenadas del click y traducirlas
	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
