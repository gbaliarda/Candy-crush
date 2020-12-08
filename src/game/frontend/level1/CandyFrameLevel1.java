package game.frontend.level1;

import game.backend.CandyGame;
import game.backend.element.Element;
import game.frontend.CandyFrame;
import javafx.geometry.Point2D;


public class CandyFrameLevel1 extends CandyFrame {

	private final ScorePanelLevel1 scorePanel;


	public CandyFrameLevel1(CandyGame game) {
		super(game);

		BoardPanelLevel1 boardPanel = new BoardPanelLevel1(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
		getChildren().add(boardPanel); // Agrega el panel a la ventana
		scorePanel = new ScorePanelLevel1(game.getState().getMaxMoves()); // Crea el panel de score
		getChildren().add(scorePanel); // Agrega el score


		gameListener(boardPanel);

		// Mover caramelos y si gano por x motivo

		mouseEventHandler(scorePanel);
	}

	@Override
	public void doOnExplosion(Element e) {
		//
	}

	@Override
	public void checkMoveAction() {
		scorePanel.updateMovesLeft();
	}

	@Override
	public boolean checkMove(Point2D newPoint) {
		return game().tryMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
	}
}
