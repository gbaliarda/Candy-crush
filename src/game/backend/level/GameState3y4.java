package game.backend.level;

import game.backend.GameState;
import game.backend.element.Element;

import java.util.LinkedList;
import java.util.List;

public abstract class GameState3y4 extends GameState {

    private final int maxMovements = 10;
    private final int maxBombs = 10;
    private int timeBombs = 10;
    private final int initialBombs = 3;
    private int generatedCounter = 0;
    private final int step = 1; // Cada cuantos movimientos se genera una nueva TimeBomb
    private final List<Element> timeBombList = new LinkedList<>();

//    private int getRandomAmount(int min, int max) {
//        return (int)(Math.random() * (max - min) + min);
//    }

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

    public int getMaxMovements() {
        return maxMovements;
    }

    public List<Element> getTimeBombList() {
        return timeBombList;
    }

    public void addTimeBomb(Element element) {
        timeBombList.add(element);
    }

    public void removeTimeBomb() {
        timeBombs--;
    }
}
