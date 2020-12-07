package game.frontend.level3;

import game.frontend.ScorePanel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ScorePanelLevel3 extends ScorePanel{

    private static final Label BLAST_WALL_CELLS_LEFT = new Label("Celdas restantes: ");
    private Label blastWallCellsLabel;
    private VBox blastCellsBox;

    public ScorePanelLevel3(long maxMoves, long blastWallCells){
        super(maxMoves);
        blastWallCellsLabel = new Label(String.valueOf(blastWallCells));
        HBox blastCellHBox= new HBox();
        blastCellHBox.getChildren().addAll(BLAST_WALL_CELLS_LEFT, blastWallCellsLabel);
        blastCellHBox.setAlignment(Pos.CENTER);
        blastCellsBox = new VBox();
        blastCellsBox.getChildren().addAll(blastCellHBox);
        blastCellsBox.setAlignment(Pos.CENTER);
        blastCellsBox.setStyle("-fx-font-size: 20");
        setBottom(blastCellsBox);
    }

    public void updateCellsLeft(String blastCells) {
        ((Label) (((HBox) blastCellsBox.getChildren().get(0))).getChildren().get(1)).setText(blastCells);
    }
}
