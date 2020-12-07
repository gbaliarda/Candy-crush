package game.frontend.level3;

import game.backend.GameState;
import game.backend.level.Level3;
import game.frontend.BoardPanel;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class BoardPanelLevel3 extends BoardPanel {

    public BoardPanelLevel3(final int rows, final int columns, final int cellSize){
        super(rows, columns, cellSize);
    }

    public void createWall(GameState state) {
        Level3.Level3State level3State = (Level3.Level3State)state;
        for (int i = level3State.getRowFrom(); i <= level3State.getRowTo(); i++) {
            for (int j = level3State.getColFrom(); j <= level3State.getColTo(); j++) {
                Light.Distant spotLight = new Light.Distant();
                spotLight.setColor(Color.SANDYBROWN);
                spotLight.setElevation(100);
                Lighting lighting = new Lighting(spotLight);
                getCells()[i][j].setEffect(lighting);
            }
        }
    }

}
