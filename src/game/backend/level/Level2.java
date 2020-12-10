package game.backend.level;

import game.backend.CandyGame;
import game.backend.GameState;
import game.backend.Grid;
import javafx.geometry.Point2D;

// Nivel GoldenBoard

public class Level2 extends Level {

    private static final int MAX_MOVES = 25;

    @Override
    protected GameState newState() {
        return new Level2State(MAX_MOVES);
    }

    private static class Level2State extends GameState {
        private int nonGoldenCells;
        private final boolean[][] goldenGrid = new boolean[Grid.SIZE][Grid.SIZE];

        private Level2State(int maxMoves) {
            setMaxMoves(maxMoves);
            this.nonGoldenCells = Grid.SIZE * Grid.SIZE;
        }

        @Override
        public int getScorePanelData(){
            return nonGoldenCells;
        }


        @Override
        public int doOnMove(CandyGame game, Point2D lastPoint, Point2D newPoint) {
            if(Math.abs((int) lastPoint.getX() - (int) newPoint.getX()) == 0)
                setGoldenRow((int) newPoint.getX());
            else
                setGoldenColumn((int) newPoint.getY());
            return 0;
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
                if(!isGoldenCell(row, i))
                    setGoldenCell(row, i);
        }

        public void setGoldenColumn(int col) {
            for(int i = 0; i < Grid.SIZE; i++)
                if(!isGoldenCell(i, col))
                    setGoldenCell(i, col);
        }

        public boolean isGoldenCell(int row, int col){
            return goldenGrid[row][col];
        }
    }

}
