package game.backend.level;

import game.backend.GameState;
import game.backend.element.Element;

import java.util.LinkedList;
import java.util.List;

public abstract class GameState3y4 extends GameState {

    private static final int minMovements = 7;
    private static final int maxMovements = 13;
    private static final int maxBombs = 10;
    private int timeBombs = 10;
    private static final int initialBombs = 3;
    private int generatedCounter = 0;
    private static final int step = 2; // Cada cuantos movimientos se genera una nueva TimeBomb
    private List<Element> timeBombList = new LinkedList<>();

    public int getStep() {
        return step;
    }

    public int getMaxBombs() {
        return maxBombs;
    }

    public int getBombsLeft() {
        return timeBombs;
    }

    public int getInitialBombs() {
        return initialBombs;
    }

    public int getGenerated() {
        return generatedCounter;
    }

    public void addGenerated() {
        generatedCounter++;
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

    public void removeTimeBomb() {
        timeBombs--;
    }
}
