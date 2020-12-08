package game.backend.level;

import game.backend.GameState;
import game.backend.element.Element;

import java.util.LinkedList;
import java.util.List;

// Time Bomb

public class Level3 extends Level {

    @Override
    protected GameState newState() {
        return new Level3.Level3State();
    }

    public class Level3State extends GameState {
        private final int maxMovements = 10;
        private final int maxBombs = 10;
        private int timeBombs = 10;
        private final int initialBombs = 3;
        private int generatedCounter = 0;
        private boolean playerLost = false;
        private final int step = 1; // Cada cuantos movimientos se genera una nueva TimeBomb
        private final List<Element> timeBombList = new LinkedList<>();

        @Override
        public boolean gameOver() {
            return playerWon() || playerLost;
        }

        public int getStep() {
            return step;
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

        public int getMaxBombs() {
            return maxBombs;
        }

        public void removeTimeBomb() {
            timeBombs--;
        }

        private int getRandomAmount(int min, int max) {
            return (int)(Math.random() * (max - min) + min);
        }

        @Override
        public boolean playerWon() {
            return getBombsLeft() == 0;
        }

        public void playerLost() {
            playerLost = true;
        }

        public void addTimeBomb(Element element) {
            timeBombList.add(element);
        }

        public List<Element> getTimeBombList() {
            return timeBombList;
        }
    }

}
