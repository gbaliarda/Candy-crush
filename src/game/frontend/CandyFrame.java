package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public abstract class CandyFrame extends VBox {

    protected static final int CELL_SIZE = 65;
    protected final CandyGame game;
    private ImageManager images;
    private Point2D lastPoint;

    public CandyFrame(CandyGame game){
        this.game = game;
        setImages(new ImageManager());
        game.initGame(); // Llena el tablero de caramelos
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
                        int finalI = i;
                        int finalJ = j;
                        Cell cell = game.get(i, j);
                        Element element = cell.getContent();
                        Image image = getImages().getImage(element);
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
    }

    public void mouseEventHandler(ScorePanel scorePanel){
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (getLastPoint() == null) {
                setLastPoint(translateCoords(event.getX(), event.getY()));
                System.out.println("Get first = " +  getLastPoint());
            } else {
                Point2D newPoint = translateCoords(event.getX(), event.getY());
                if (newPoint != null) {
                    System.out.println("Get second = " +  newPoint);
                    if(checkMove(newPoint)){
                        scorePanel.updateMovesLeft();
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
                    setLastPoint(null);
                }
            }
        });
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
        // Desfasaje en y por la altura del menu.
        double j = y / CELL_SIZE;
        return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
    }
}
