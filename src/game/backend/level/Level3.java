package game.backend.level;

import game.backend.GameState;
import game.backend.element.Element;

import java.util.LinkedList;
import java.util.List;

// Time Bomb

public class Level3 extends Level {

    private static final int MAX_MOVES = 20;

    @Override
    protected GameState newState() {
        return new Level3.Level3State(MAX_MOVES);
    }

    public class Level3State extends GameState {
        private final int maxMovements = 10;
        private int timeBombs = 1;
        private final int initialBombs = 1;
        private int generatedBombs = 0;
        private boolean playerLost = false;
        private final int step = 3; // Cada cuantos movimientos se genera una nueva TimeBomb
        private final List<Element> timeBombList = new LinkedList<>();

        public Level3State(int maxMoves) {
            setMaxMoves(maxMoves);
        }

        @Override
        public boolean gameOver() {
            return playerWon() || getMoves() >= getMaxMoves() || playerLost;
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

        public int getMaxMovements() {
            return maxMovements;
        }

        public int getGeneratedBombs() {
            return generatedBombs;
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
            generatedBombs++;
        }

        public List<Element> getTimeBombList() {
            return timeBombList;
        }

        public long getBlastWallsCellsLeft(){
            return timeBombs;
        }
    }

}
