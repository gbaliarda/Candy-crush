package game.frontend.level2;

import game.backend.CandyGame;

import game.backend.level.Level2;
import game.frontend.*;

import javafx.geometry.Point2D;


public class CandyFrame2 extends CandyFrame {

    private BoardPanelLevel2 boardPanel;
    private ScorePanelLevel2 scorePanel;

    public CandyFrame2(CandyGame game) {
        super(game);

        boardPanel = new BoardPanelLevel2(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
        getChildren().add(boardPanel); // Agrega el panel a la ventana
        scorePanel = new ScorePanelLevel2(game.getState().getMaxMoves(),game.getSize()); // Crea el panel de score
        getChildren().add(scorePanel); // Agrega el score

        gameListener(boardPanel);

        mouseEventHandler(scorePanel);
    }

    @Override
    public boolean checkMove(Point2D newPoint) {
        boolean isValid = game().tryMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
        if(isValid){
            // Funcion que marque toda la fila o columna de dorado
            if(Math.abs((int) getLastPoint().getX() - (int) newPoint.getX()) == 0) {
                boardPanel.setGoldenRow((int) newPoint.getX(), game.getState());
            }
            else {
                boardPanel.setGoldenColumn((int) newPoint.getY(), game.getState());
            }
            scorePanel.updateCellsLeft( String.valueOf( ( (Level2.Level2State)game.getState() ).getNonGoldenCells()));
        }
        return isValid;
    }

}
