package game.frontend.level1;

import game.backend.CandyGame;
import game.frontend.BoardPanel;
import game.frontend.CandyFrame;
import game.frontend.ScorePanel;
import javafx.geometry.Point2D;


public class CandyFrameLevel1 extends CandyFrame {

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;


	public CandyFrameLevel1(CandyGame game) {
		super(game);

		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
		getChildren().add(boardPanel); // Agrega el panel a la ventana
		scorePanel = new ScorePanel(game.getState().getMaxMoves()); // Crea el panel de score
		getChildren().add(scorePanel); // Agrega el score


		gameListener(boardPanel);

		// Mover caramelos y si gano por x motivo

		mouseEventHandler(scorePanel);
	}

	@Override
	public boolean checkMove(Point2D newPoint) {
		return game().tryMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
	}
}
