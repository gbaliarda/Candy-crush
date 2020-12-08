package game.frontend;

import game.backend.CandyGame;
import game.backend.Grid;
import game.backend.element.Element;
import game.backend.level.GameState3y4;
import game.frontend.level3.ScorePanelLevel3;
import javafx.geometry.Point2D;

import java.util.Iterator;
import java.util.Random;

public abstract class CandyFrameLevel3y4 extends CandyFrame {

    private final ScorePanelLevel3 scorePanel;
    private final GameState3y4 levelState;
    private int moveCounter;

    public CandyFrameLevel3y4(CandyGame game) {
        super(game);

        levelState = (GameState3y4) game.getState();

        BoardPanel boardPanel = new BoardPanelLevel3y4(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
        getChildren().add(boardPanel); // Agrega el panel a la ventana

        scorePanel = setScorePanel();
        getChildren().add(scorePanel);

        generateTimeBombs();

        gameListener(boardPanel);

        mouseEventHandler(scorePanel);
    }

    public ScorePanelLevel3 getScorePanel(){
        return scorePanel;
    }

    public abstract ScorePanelLevel3 setScorePanel();

    public GameState3y4 getLevelState(){
        return levelState;
    }

    private void generateTimeBombs() {
        int row, col;
        for (int i = 0; i < levelState.getInitialBombs(); i++) {
            row = getRandPos();
            col = getRandPos();
            if (!game.get(row, col).getContent().getProperty().isEmpty())
                i--;
            else {
                game.get(row,col).getContent().setProperty(String.valueOf(levelState.getMaxMovements()));
                levelState.addTimeBomb(game.get(row,col).getContent());
            }
        }
    }

    @Override
    public void checkMoveAction() {
        //
    }

    private int getRandPos() {
        Random rand = new Random();
        return rand.nextInt(Grid.SIZE);
    }

    public abstract void actionIfValid();

    @Override
    public boolean checkMove(Point2D newPoint) {
        boolean isValid = game().isValidMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
        if(isValid){
            actionIfValid();
            moveCounter++;
            genNewBomb();
            game().tryMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
            removeExplodedBombs();
        }
        return isValid;
    }

    public abstract void additionalAction(int number);

    private void removeExplodedBombs() {
        Iterator<Element> it = levelState.getTimeBombList().iterator();
        while (it.hasNext()) {
            if (it.next().getProperty().isEmpty()) {
                levelState.removeTimeBomb();
                scorePanel.updateBombsLeft(String.valueOf(levelState.getBombsLeft()));
                additionalAction(10);
                it.remove();
            }
        }
    }

    private void genNewBomb() {
        if (moveCounter % levelState.getStep() == 0 && !(levelState.getInitialBombs() + levelState.getGenerated() >= levelState.getMaxBombs())) {
            int randPos = getRandPos();
            while(!game.get(0, randPos).getContent().getProperty().isEmpty())
                randPos = getRandPos();
            game.get(0, randPos).getContent().setProperty(String.valueOf(levelState.getMaxMovements()));
            levelState.addTimeBomb(game.get(0, randPos).getContent());
            levelState.addGenerated();
        }
    }

}
