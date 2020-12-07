package game.frontend.level2;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;

import game.backend.level.Level2;
import game.frontend.AppMenu;
import game.frontend.BoardPanel;
import game.frontend.ImageManager;
import game.frontend.ScorePanel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CandyFrame2 extends VBox {

    private static final int CELL_SIZE = 65;

    private BoardPanelLevel2 boardPanel;
    private ScorePanelLevel2 scorePanel;
    private ImageManager images;
    private Point2D lastPoint;
    private CandyGame game;

    public CandyFrame2(CandyGame game) {
        this.game = game;
        getChildren().add(new AppMenu()); // Agrega el menu de arriba
        images = new ImageManager(); // Mapa de nombre de la bomba como key y la imagen como valor
        boardPanel = new BoardPanelLevel2(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
        getChildren().add(boardPanel); // Agrega el panel a la ventana
        scorePanel = new ScorePanelLevel2(game.getSize()); // Crea el panel de score
        getChildren().add(scorePanel); // Agrega el score
        game.initGame(); // Llena el tablero de caramelos
        GameListener listener; // Espera y lee lo que ocurre en el tablero
        game.addGameListener(listener = new GameListener() {
            @Override
            public void gridUpdated() {
                Timeline timeLine = new Timeline();
                Duration frameGap = Duration.millis(100);
                Duration frameTime = Duration.ZERO;
                for (int i = game().getSize() - 1; i >= 0; i--) {
                    for (int j = game().getSize() - 1; j >= 0; j--) {
                        int finalI = i;
                        int finalJ = j;
                        Cell cell = CandyFrame2.this.game.get(i, j);
                        Element element = cell.getContent();
                        Image image = images.getImage(element);
                        timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null)));
                        timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image)));
                    }
                    frameTime = frameTime.add(frameGap);
                }
                timeLine.play();
            }
            @Override
            public void cellExplosion(Element e) {
                //
            }
        });

        listener.gridUpdated();

        // Mover caramelos y si gano por x motivo
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (lastPoint == null) {
                lastPoint = translateCoords(event.getX(), event.getY());
                System.out.println("Get first = " +  lastPoint);
            } else {
                Point2D newPoint = translateCoords(event.getX(), event.getY());
                if (newPoint != null) {
                    System.out.println("Get second = " +  newPoint);
                    if(game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY())){
                        // Funcion que marque toda la fila o columna de dorado

                        if(Math.abs((int) lastPoint.getX() - (int) newPoint.getX()) == 0) {
                            boardPanel.setGoldenRow((int) newPoint.getX(), game.getState());
                        }
                        else {
                            boardPanel.setGoldenColumn((int) newPoint.getY(), game.getState());
                        }
                        scorePanel.updateCellsLeft( String.valueOf( ( (Level2.Level2State)game.getState() ).getNonGoldenCells()));
                    }   
                    String message = ((Long)game().getScore()).toString();
                    if (game().isFinished()) { // Si el game termina y es loser pero despues llegas a la condicion de ganado, ganas.
                        if (game().playerWon()) {
                            message = message + " Finished - Player Won!";
                        } else {
                            message = message + " Finished - Loser !";
                        }
                    }
                    scorePanel.updateScore(message);
                    lastPoint = null;
                }
            }
        });

    }

    private CandyGame game() {
        return game;
    }

    // Funcion que obtiene las coordenadas del click y traducirlas
    private Point2D translateCoords(double x, double y) {
        double i = x / CELL_SIZE;
        // Desfasaje en y por la altura del menu.
        double j = y / CELL_SIZE - ((AppMenu)getChildren().get(0)).getMenuHeight();
        return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
    }

}
