package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

// Nivel GoldenBoard

public class Level2 extends Level {

    private static final int MAX_MOVES = 25;

    @Override
    protected GameState newState() {
        return new Level2State(MAX_MOVES);
    }

    public static class Level2State extends GameState {
        private long nonGoldenCells;
        private final boolean[][] goldenGrid = new boolean[Grid.SIZE][Grid.SIZE];

        private Level2State(int maxMoves) {
            setMaxMoves(maxMoves);
            this.nonGoldenCells = Grid.SIZE * Grid.SIZE;
        }

        @Override
        public boolean gameOver() {
            return playerWon() || getMoves() >= getMaxMoves();
        }

        @Override
        public boolean playerWon() {
            return nonGoldenCells == 0;
        }

        @Override
        public long getRequiredScore() {
            return -1;
        }

        public void setGoldenCell(int row, int col){
            goldenGrid[row][col] = true;
            nonGoldenCells--;
        }

        public void setGoldenRow(int row) {
            for(int i = 0; i < Grid.SIZE; i++)
                if(!getGoldenCell(row, i))
                    setGoldenCell(row, i);
        }

        public void setGoldenColumn(int col) {
            for(int i = 0; i < Grid.SIZE; i++)
                if(!getGoldenCell(i, col))
                    setGoldenCell(i, col);
        }

        public long getNonGoldenCells(){
            return nonGoldenCells;
        }

        public boolean getGoldenCell(int row, int col){
            return goldenGrid[row][col];
        }
    }

}
