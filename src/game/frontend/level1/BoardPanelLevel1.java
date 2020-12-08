package game.frontend.level1;

import game.frontend.BoardPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardPanelLevel1 extends BoardPanel {

	private final ImageView[][] cells;

	public BoardPanelLevel1(final int rows, final int columns, final int cellSize) {
		super(rows, columns, cellSize);
		this.cells = new ImageView[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new ImageView();
				getChildren().add(cells[i][j]);
			}
		}
	}
	
	public void setImage(int row, int column, Image image) {
		cells[row][column].setImage(image);
	}

	public ImageView[][] getCells(){
		return cells;
	}

}