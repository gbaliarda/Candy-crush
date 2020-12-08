package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Optional;

public abstract class CandyFrame extends VBox {

    protected static final int CELL_SIZE = 65;
    protected final CandyGame game;
    private ImageManager images;
    private Point2D lastPoint;
    private boolean stopped = false;

    public CandyFrame(CandyGame game){
        this.game = game;
        setImages(new ImageManager());
        game.initGame(); // Llena el tablero de caramelos
    }

    public void addKeyFrames(Timeline timeLine, Duration frameTime, BoardPanel boardPanel, int i, int j, Element element){
        timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(i, j, getImages().getImage(element))));
    }

    public void gameListener(BoardPanel boardPanel){
        GameListener listener; // Espera y lee lo que ocurre en el tablero
        game.addGameListener(listener = new GameListener() {
            @Override
            public void gridUpdated() {
                Timeline timeLine = new Timeline();
                Duration frameGap = Duration.millis(100);
                Duration frameTime = Duration.ZERO;
                for (int i = game().getSize() - 1; i >= 0; i--) {
                    for (int j = game().getSize() - 1; j >= 0; j--) {
                        Cell cell = game.get(i, j);
                        Element element = cell.getContent();
                        addKeyFrames(timeLine, frameTime, boardPanel, i, j, element);
                    }
                    frameTime = frameTime.add(frameGap);
                }
                timeLine.play();
            }
            @Override
            public void cellExplosion(Element e) {
                doOnExplosion(e);
            }
        });

        listener.gridUpdated();
    }

    public abstract void doOnExplosion(Element e);

    public abstract void checkMoveAction();

    public void mouseEventHandler(ScorePanel scorePanel){
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(!stopped){
                if (getLastPoint() == null) {
                    setLastPoint(translateCoords(event.getX(), event.getY()));
                } else {
                    Point2D newPoint = translateCoords(event.getX(), event.getY());
                    if (newPoint != null) {
                        if (checkMove(newPoint)) {
                            checkMoveAction();
                        }
                        String message = ((Long) game().getScore()).toString();
                        scorePanel.updateScore(message);
                        if (game().isFinished()) { // Si el game termina y es loser pero despues llegas a la condicion de ganado, ganas.
                            if (game().playerWon()) {
                                endScreen("Ganado");
                            } else {
                                endScreen("Perdido");
                            }
                        }
                        setLastPoint(null);
                    }
                }
            }
        });
    }

    public void endScreen(String message){
        Alert loser = new Alert(Alert.AlertType.CONFIRMATION);
        loser.setTitle("Juego Terminado");
        loser.setHeaderText("Has "+message+"! Puntaje: " + game().getState().getScore());
        Optional<ButtonType> result = loser.showAndWait();
        if(result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                stopped = true;
            } else {
                Platform.exit();
            }
        } else {
            Platform.exit();
        }
    }

    public abstract boolean checkMove(Point2D newPoint);

    public ImageManager getImages(){
        return images;
    }

    public Point2D getLastPoint(){
        return lastPoint;
    }

    public void setImages(ImageManager images){
        this.images = images;
    }

    public void setLastPoint(Point2D lastPoint){
        this.lastPoint = lastPoint;
    }

    public CandyGame game() {
        return game;
    }

    // Funcion que obtiene las coordenadas del click y traducirlas
    protected Point2D translateCoords(double x, double y) {
        double i = x / CELL_SIZE;
        double j = y / CELL_SIZE;
        return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
    }
}
