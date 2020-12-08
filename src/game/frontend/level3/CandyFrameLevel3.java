package game.frontend.level3;

import game.backend.CandyGame;
import game.backend.Grid;
import game.backend.element.Element;
import game.backend.level.Level3;
import game.frontend.CandyFrame;
import javafx.geometry.Point2D;

import java.util.Iterator;
import java.util.Random;

public class CandyFrameLevel3 extends CandyFrame {

    private BoardPanelLevel3 boardPanel;
    private ScorePanelLevel3 scorePanel;
    private Level3.Level3State levelState;
    private int moveCounter;

    public CandyFrameLevel3(CandyGame game) {
        super(game);

        levelState = (Level3.Level3State)game.getState();

        boardPanel = new BoardPanelLevel3(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
        getChildren().add(boardPanel); // Agrega el panel a la ventana
        scorePanel = new ScorePanelLevel3(game.getState().getMaxMoves(), levelState.getBombsLeft()); // Crea el panel de score
        getChildren().add(scorePanel); // Agrega el score

        generateTimeBombs(levelState);

        gameListener(boardPanel);

        mouseEventHandler(scorePanel);

    }

    private void generateTimeBombs(Level3.Level3State levelState) {
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

    private int getRandPos() {
        Random rand = new Random();
        return rand.nextInt(Grid.SIZE);
    }

    @Override
    public boolean checkMove(Point2D newPoint) {
        boolean isValid = game().isValidMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
        if(isValid){
            moveCounter++;
            updateBombList();
            genNewBomb();
            game().tryMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
            removeExplodedBombs();
         }
        return isValid;
    }

    private void updateBombList() {
        levelState.getTimeBombList().forEach(bomb -> {
            int newValue = Integer.parseInt(bomb.getProperty()) - 1;
            bomb.setProperty(String.valueOf(newValue));
            if (newValue == 0)
                levelState.playerLost();
        });
    }

    private void removeExplodedBombs() {
        Iterator<Element> it = levelState.getTimeBombList().iterator();
        while (it.hasNext()) {
            if (it.next().getProperty().isEmpty()) {
                levelState.removeTimeBomb();
                scorePanel.updateBombsLeft(String.valueOf(levelState.getBombsLeft()));
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
