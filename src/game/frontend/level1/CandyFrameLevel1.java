package game.frontend.level1;

import game.backend.CandyGame;
import game.frontend.CandyFrame;


public class CandyFrameLevel1 extends CandyFrame {

	private final ScorePanelLevel1 scorePanel;

	public CandyFrameLevel1(CandyGame game) {
		super(game);
		BoardPanelLevel1 boardPanel = new BoardPanelLevel1(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
		scorePanel = new ScorePanelLevel1(game.getState().getMaxMoves(), game.getState().getRequiredScore()); // Crea el panel de score
		getChildren().add(boardPanel);
		getChildren().add(scorePanel);
		gameListener(boardPanel);
		mouseEventHandler(scorePanel, boardPanel);
	}

	@Override
	public void updateMovesLeft() {
		scorePanel.updateMovesLeft();
	}

}
