package game.frontend.level3;

import game.backend.CandyGame;
import game.backend.level.Level3;
import game.frontend.CandyFrame;
import game.frontend.level3.BoardPanelLevel3;
import game.frontend.level3.ScorePanelLevel3;
import javafx.geometry.Point2D;

public class CandyFrameLevel3 extends CandyFrame {

    private BoardPanelLevel3 boardPanel;
    private ScorePanelLevel3 scorePanel;

    public CandyFrameLevel3(CandyGame game) {
        super(game);

        boardPanel = new BoardPanelLevel3(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
        getChildren().add(boardPanel); // Agrega el panel a la ventana
        scorePanel = new ScorePanelLevel3(game.getState().getMaxMoves(), ((Level3.Level3State)game.getState()).getBlastWallsCellsLeft() ); // Crea el panel de score
        getChildren().add(scorePanel); // Agrega el score

        gameListener(boardPanel);

        boardPanel.createWall(game.getState());

        mouseEventHandler(scorePanel);
    }

    @Override
    public boolean checkMove(Point2D newPoint) {
        boolean isValid = game().tryMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
        if(isValid){
            
         }
        return isValid;
    }

}
