package game.backend.level;

// Time Limit

import game.backend.GameState;
import game.backend.element.Element;

import java.util.LinkedList;
import java.util.List;

public class Level4 extends Level {

    @Override
    protected GameState newState() {
        return new Level4.Level4State();
    }

    public class Level4State extends GameState {
        private int seconds = 60;
        private boolean levelStarted = false;
        private final int maxMovements = 10;
        private final int maxBombs = 10;
        private int timeBombs = 10;
        private final int initialBombs = 3;
        private int generatedCounter = 0;
        private final int step = 1; // Cada cuantos movimientos se genera una nueva TimeBomb
        private final List<Element> timeBombList = new LinkedList<>();

        @Override
        public boolean gameOver() {
            return getSeconds() <= 0;
        }

        public int getStep() {
            return step;
        }

        public int getMaxBombs() {
            return maxBombs;
        }

        public void startTimer() {
            levelStarted = true;
        }

        public boolean isLevelStarted() {
            return levelStarted;
        }

        public int getSeconds(){
            return seconds;
        }

        public void reduceTimer() {
            seconds--;
        }

        public void addSeconds(int seconds) {
            this.seconds += seconds;
        }

        public int getBombsLeft() {
            return timeBombs;
        }

        public int getInitialBombs() {
            return initialBombs;
        }

        public int getMaxMovements() {
            return maxMovements;
        }

        public int getGenerated() {
            return generatedCounter;
        }

        public void addGenerated() {
            generatedCounter++;
        }

        public void removeTimeBomb() {
            timeBombs--;
        }

//        private int getRandomAmount(int min, int max) {
//            return (int)(Math.random() * (max - min) + min);
//        }

        @Override
        public boolean playerWon() {
            return false;
        }

        public void addTimeBomb(Element element) {
            timeBombList.add(element);
        }

        public List<Element> getTimeBombList() {
            return timeBombList;
        }

    }

}

