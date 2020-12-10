package game.frontend.level2;

import game.frontend.level1.BoardPanelLevel1;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class BoardPanelLevel2 extends BoardPanelLevel1 {

    public BoardPanelLevel2(final int rows, final int columns, final int cellSize){
        super(rows, columns, cellSize);
    }

    public void setGoldenImage(int row, int column){
        Light.Distant spotLight = new Light.Distant();
        spotLight.setColor(Color.YELLOW);
        spotLight.setElevation(100);
        Lighting lighting = new Lighting(spotLight);
        getCells()[row][column].setEffect(lighting);
    }

    @Override
    public void removeLastPointEffect(int row, int col) {
        if(getCells()[row][col].getEffect().getClass().equals(getFocusEffect().getClass()))
            super.removeLastPointEffect(row,col);
    }

    @Override
    public void setLastPointEffect(int row, int col){
        if(getCells()[row][col].getEffect() == null)
            super.setLastPointEffect(row,col);
    }

    public void setGoldenRow(int row){
        for(int i = 0; i < getPrefColumns(); i++)
                setGoldenImage(row, i);
    }

    public void setGoldenColumn(int column){
        for(int i = 0; i < getPrefRows(); i++)
                setGoldenImage(i, column);
    }
}
