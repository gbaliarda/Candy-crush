package game.frontend.level4;

import game.frontend.level3.BoardPanelLevel3;

public class BoardPanelLevel4 extends BoardPanelLevel3 {

    public BoardPanelLevel4(int rows, int columns, int cellSize) {
        super(rows, columns, cellSize);
    }

    @Override
    public void setText(int row, int column, String text) {
        String textAux = text.isEmpty() ? text : "+" + text;
        super.setText(row, column, textAux);
    }
}
