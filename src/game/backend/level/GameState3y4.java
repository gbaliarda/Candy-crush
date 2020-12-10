package game.backend.level;

import game.backend.CandyGame;
import game.backend.GameState;
import game.backend.Grid;
import game.backend.element.Element;
import javafx.geometry.Point2D;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class GameState3y4 extends GameState {

    private static final int minMovements = 7;
    private static final int maxMovements = 13;
    private static final int maxBombs = 10;
    private int timeBombs = 10;
    private static final int initialBombs = 3;
    private int generatedCounter = 0;
    private static final int step = 2; // Cada cuantos movimientos se genera una nueva TimeBomb
    private final List<Element> timeBombList = new LinkedList<>();
    private int moveCounter = 0;

    public void generateTimeBombs(CandyGame game) {
        int row, col;
        for (int i = 0; i < initialBombs; i++) {
            row = getRandPos();
            col = getRandPos();
            if (!(game.get(row, col).getContent().getNumber() == null))
                i--;
            else
                addTimeBomb(game.get(row,col).getContent());
        }
    }

    @Override
    public int doOnMove(CandyGame game, Point2D lastPoint, Point2D newPoint) {
        moveCounter++;
        genNewBomb(game);
        updateState();
        game.tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());
        checkLose();
        return removeExplodedBombs();
    }

    public void checkLose(){
        //
    }

    private void genNewBomb(CandyGame game) {
        if (moveCounter % step == 0 && !(initialBombs + generatedCounter >= maxBombs)) {
            int randPos = getRandPos();
            while(!(game.get(0, randPos).getContent().getNumber() == null))
                randPos = getRandPos();
            game.get(0, randPos).getContent().setNumber(getRandomAmount());
            addTimeBomb(game.get(0, randPos).getContent());
            generatedCounter++;
        }
    }

    public void updateState(){
        //
    }

    public void updateTimerState(){
        //
    }

    public void manageExplosion(Element e) {
        e.setNumber(null);
    }

    private int removeExplodedBombs() {
        Iterator<Element> it = timeBombList.iterator();
        int number = 0;
        while (it.hasNext()) {
            Element e = it.next();
            if (removeCondition(e.getNumber())) {
                timeBombs--;
                number = additionalAction(e);
                it.remove();
            }
        }
        return number;
    }

    public int additionalAction(Element e) {
        return 0;
    }

    public abstract boolean removeCondition(Integer number);

    private int getRandPos() {
        Random rand = new Random();
        return rand.nextInt(Grid.SIZE);
    }

    public int getBombsLeft() {
        return timeBombs;
    }

    public int getRandomAmount() {
        return (int)(Math.random() * (maxMovements - minMovements) + minMovements);
    }

    public List<Element> getTimeBombList() {
        return timeBombList;
    }

    public void addTimeBomb(Element element) {
        timeBombList.add(element);
        element.setNumber(getRandomAmount());
    }

}
