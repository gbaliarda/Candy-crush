package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Wall;

// Nivel GoldenBoard

public class Level2 extends Grid {
    
    private static int MAX_MOVES = 20;

    private Cell wallCell;
    private Cell candyGenCell;

    @Override
    protected GameState newState() {
        return new Level2State(MAX_MOVES);
    }

    @Override
    protected void fillCells() {

        wallCell = new Cell(this);
        wallCell.setContent(new Wall());
        candyGenCell = new CandyGeneratorCell(this);

        //corners
        g()[0][0].setAround(candyGenCell, g()[1][0], wallCell, g()[0][1]);
        g()[0][SIZE-1].setAround(candyGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
        g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
        g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);

        //upper line cells
        for (int j = 1; j < SIZE-1; j++) {
            g()[0][j].setAround(candyGenCell,g()[1][j],g()[0][j-1],g()[0][j+1]);
        }
        //bottom line cells
        for (int j = 1; j < SIZE-1; j++) {
            g()[SIZE-1][j].setAround(g()[SIZE-2][j], wallCell, g()[SIZE-1][j-1],g()[SIZE-1][j+1]);
        }
        //left line cells
        for (int i = 1; i < SIZE-1; i++) {
            g()[i][0].setAround(g()[i-1][0],g()[i+1][0], wallCell ,g()[i][1]);
        }
        //right line cells
        for (int i = 1; i < SIZE-1; i++) {
            g()[i][SIZE-1].setAround(g()[i-1][SIZE-1],g()[i+1][SIZE-1], g()[i][SIZE-2], wallCell);
        }
        //central cells
        for (int i = 1; i < SIZE-1; i++) {
            for (int j = 1; j < SIZE-1; j++) {
                g()[i][j].setAround(g()[i-1][j],g()[i+1][j],g()[i][j-1],g()[i][j+1]);
            }
        }
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    public class Level2State extends GameState {
        private long nonGoldenCells;
        private boolean[][] goldenGrid = new boolean[Grid.SIZE][Grid.SIZE];

        public Level2State(int maxMoves) {
            setMaxMoves(maxMoves);
            this.nonGoldenCells = Grid.SIZE * Grid.SIZE;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= getMaxMoves();
        }

        public boolean playerWon() {
            return nonGoldenCells == 0;
        }

        public void setGoldenCell(int row, int col){
            goldenGrid[row][col] = true;
            nonGoldenCells--;
        }

        public long getNonGoldenCells(){
            return nonGoldenCells;
        }

        public boolean getGoldenCell(int row, int col){
            return goldenGrid[row][col];
        }
    }

}
