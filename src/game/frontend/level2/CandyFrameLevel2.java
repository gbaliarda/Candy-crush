package game.frontend.level2;

import game.backend.CandyGame;

import game.frontend.*;
import javafx.geometry.Point2D;


public class CandyFrameLevel2 extends CandyFrame {

    private final BoardPanelLevel2 boardPanel;
    private final ScorePanelLevel2 scorePanel;

    public CandyFrameLevel2(CandyGame game) {
        super(game);
        boardPanel = new BoardPanelLevel2(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
        scorePanel = new ScorePanelLevel2(game.getState().getMaxMoves(),game.getSize(), game.getState().getRequiredScore()); // Crea el panel de score
        getChildren().add(boardPanel);
        getChildren().add(scorePanel);
        gameListener(boardPanel);
        mouseEventHandler(scorePanel, boardPanel);
    }

    @Override
    public void updateMovesLeft() {
        scorePanel.updateMovesLeft();
    }

    @Override
    public boolean makeMove(Point2D newPoint) {
        boolean isValid = game.isValidMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
        if(isValid){
            super.makeMove(newPoint);
            // Funcion que marque toda la fila o columna de dorado
            if(Math.abs((int) getLastPoint().getX() - (int) newPoint.getX()) == 0) {
                boardPanel.setGoldenRow((int) newPoint.getX());
            }
            else {
                boardPanel.setGoldenColumn((int) newPoint.getY());
            }
            game.getState().doOnMove(game, getLastPoint(), newPoint);
            scorePanel.updateCellsLeft(String.valueOf(game.getState().getScorePanelData()));
        }
        return isValid;
    }

}
