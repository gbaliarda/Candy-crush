package game.frontend.level2;

import game.backend.GameState;
import game.backend.level.Level2;
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

    public void setGoldenRow(int row, GameState state){
        for(int i = 0; i < getPrefColumns(); i++){
            if(!((Level2.Level2State)state).getGoldenCell(row, i)) {
                ((Level2.Level2State) state).setGoldenCell(row, i);
                setGoldenImage(row, i);
            }
        }
    }

    public void setGoldenColumn(int column, GameState state){
        for(int i = 0; i < getPrefRows(); i++){
            if(!((Level2.Level2State)state).getGoldenCell(i, column)) {
                ((Level2.Level2State) state).setGoldenCell(i, column);
                setGoldenImage(i, column);
            }
        }
    }
}
