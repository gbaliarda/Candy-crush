package game.frontend;

import game.backend.CandyGame;
import game.backend.Grid;
import game.backend.element.Element;
import game.backend.level.GameState3y4;
import game.frontend.level3.BoardPanelLevel3;
import game.frontend.level3.ScorePanelLevel3;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.Random;

public abstract class CandyFrameLevel3y4 extends CandyFrame {

    private final ScorePanelLevel3 scorePanel;
    private final GameState3y4 levelState;
    private int moveCounter;

    public CandyFrameLevel3y4(CandyGame game) {
        super(game);
        levelState = (GameState3y4) game.getState();
        BoardPanelLevel3 boardPanel = setBoardPanel(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
        scorePanel = setScorePanel();
        generateTimeBombs();
        getChildren().add(boardPanel);
        getChildren().add(scorePanel);
        gameListener(boardPanel);
        mouseEventHandler(scorePanel);
    }

    @Override
    public void addKeyFrames(Timeline timeLine, Duration frameTime, BoardPanel boardPanel, int i, int j, Element element){
        super.addKeyFrames(timeLine, frameTime, boardPanel, i, j, element);
        String text = element.getNumber() == null ? "" : String.valueOf(element.getNumber() - 1);
        timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> ((BoardPanelLevel3)boardPanel).setText(i, j, text)));
    }

    public ScorePanelLevel3 getScorePanel(){
        return scorePanel;
    }

    public abstract BoardPanelLevel3 setBoardPanel(int sizeX, int sizeY, int cellSize);

    public abstract ScorePanelLevel3 setScorePanel();

    public GameState3y4 getLevelState(){
        return levelState;
    }

    private void generateTimeBombs() {
        int row, col;
        for (int i = 0; i < levelState.getInitialBombs(); i++) {
            row = getRandPos();
            col = getRandPos();
            if (!(game.get(row, col).getContent().getNumber() == null))
                i--;
            else
                levelState.addTimeBomb(game.get(row,col).getContent());
        }
    }

    @Override
    public void checkMoveAction() {
        //
    }

    @Override
    public void doOnExplosion(Element e) {
        e.setNumber(null);
    }

    private int getRandPos() {
        Random rand = new Random();
        return rand.nextInt(Grid.SIZE);
    }

    @Override
    public boolean checkMove(Point2D newPoint) {
        boolean isValid = game().isValidMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
        if(isValid){
            moveCounter++;
            genNewBomb();
            game().tryMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
            actionIfValid();
            removeExplodedBombs();
        }
        return isValid;
    }

    public abstract void actionIfValid();

    private void removeExplodedBombs() {
        Iterator<Element> it = levelState.getTimeBombList().iterator();
        while (it.hasNext()) {
            Element e = it.next();
            if (removeCondition(e.getNumber())) {
                levelState.removeTimeBomb();
                scorePanel.updateBombsLeft(String.valueOf(levelState.getBombsLeft()));
                additionalAction(e);
                it.remove();
            }
        }
    }

    public abstract void additionalAction(Element e);

    public abstract boolean removeCondition(Integer number);

    private void genNewBomb() {
        if (moveCounter % levelState.getStep() == 0 && !(levelState.getInitialBombs() + levelState.getGenerated() >= levelState.getMaxBombs())) {
            int randPos = getRandPos();
            while(!(game.get(0, randPos).getContent().getNumber() == null))
                randPos = getRandPos();
            game.get(0, randPos).getContent().setNumber(levelState.getRandomAmount());
            levelState.addTimeBomb(game.get(0, randPos).getContent());
            levelState.addGenerated();
        }
    }

}
