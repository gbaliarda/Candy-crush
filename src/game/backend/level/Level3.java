package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;

public class Level3 extends Level {

    private static final int MAX_MOVES = 2;

    @Override
    protected GameState newState() {
        return new Level3.Level3State(MAX_MOVES);
    }

    public class Level3State extends GameState {
        private long blastWallLeft;
        private final boolean[][] blastWall = new boolean[Grid.SIZE][Grid.SIZE];
        private int rowFrom, rowTo, colFrom, colTo;

        public Level3State(int maxMoves) {
            setMaxMoves(maxMoves);
            rowFrom = 3;
            rowTo = 5;
            colFrom = 3;
            colTo = 5;
            this.blastWallLeft = (rowTo - rowFrom + 1) * (colTo - colFrom + 1);
        }

        public int getRowFrom() {
            return rowFrom;
        }

        public int getRowTo() {
            return rowTo;
        }

        public int getColFrom() {
            return colFrom;
        }

        public int getColTo() {
            return colTo;
        }

        @Override
        public boolean gameOver() {
            return playerWon() || getMoves() >= getMaxMoves();
        }

        @Override
        public boolean playerWon() {
            return blastWallLeft == 0;
        }

        public void setGoldenCell(int row, int col){
            blastWall[row][col] = true;
            blastWallLeft--;
        }

        public long getBlastWallsCellsLeft(){
            return blastWallLeft;
        }

        public boolean getBlastWallCell(int row, int col){
            return blastWall[row][col];
        }
    }

}
