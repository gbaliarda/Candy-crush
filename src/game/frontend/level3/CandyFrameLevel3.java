package game.frontend.level3;

import game.backend.CandyGame;
import game.backend.element.Element;
import game.backend.level.GameState3y4;
import game.frontend.BoardPanel;
import game.frontend.CandyFrame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class CandyFrameLevel3 extends CandyFrame {

    private final ScorePanelLevel3 scorePanel;
    private final GameState3y4 levelState;

    public CandyFrameLevel3(CandyGame game) {
        super(game);
        levelState = (GameState3y4) game.getState();
        BoardPanelLevel3 boardPanel = setBoardPanel(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
        scorePanel = setScorePanel();
        generateTimeBombs();
        getChildren().add(boardPanel);
        getChildren().add(scorePanel);
        gameListener(boardPanel);
        mouseEventHandler(scorePanel, boardPanel);
    }

    @Override
    public void addKeyFrames(Timeline timeLine, Duration frameTime, BoardPanel boardPanel, int i, int j, Element element){
        super.addKeyFrames(timeLine, frameTime, boardPanel, i, j, element);
        String text = element.getNumber() == null ? "" : String.valueOf(element.getNumber());
        timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> ((BoardPanelLevel3)boardPanel).setText(i, j, text)));
    }

    public ScorePanelLevel3 getScorePanel(){
        return scorePanel;
    }

    public BoardPanelLevel3 setBoardPanel(int sizeX, int sizeY, int cellSize) {
        return new BoardPanelLevel3(sizeX, sizeY, cellSize);
    }

    public ScorePanelLevel3 setScorePanel() {
        return new ScorePanelLevel3(levelState.getBombsLeft(), game.getState().getRequiredScore());
    }

    public GameState3y4 getLevelState(){
        return levelState;
    }

    private void generateTimeBombs() {
        levelState.generateTimeBombs(game);
    }

    @Override
    public void updateMovesLeft() {
        scorePanel.updateBombsLeft(String.valueOf(levelState.getBombsLeft()));
    }

    @Override
    public void doOnExplosion(Element e) {
        levelState.manageExplosion(e);
    }

    @Override
    public boolean makeMove(Point2D newPoint) {
        boolean isValid = game().isValidMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
        if(isValid){
            int aux = game().getState().doOnMove(game, getLastPoint(), newPoint);
            updateScorePanelAux(aux);
        }
        return isValid;
    }

    public void updateScorePanelAux(int seconds) {
        //
    }

}
