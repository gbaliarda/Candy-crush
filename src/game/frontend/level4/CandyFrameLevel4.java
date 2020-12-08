package game.frontend.level4;

import game.backend.CandyGame;
import game.backend.Grid;
import game.backend.element.Element;
import game.backend.level.Level4;
import game.frontend.CandyFrame;
import javafx.application.Platform;
import javafx.geometry.Point2D;

import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CandyFrameLevel4 extends CandyFrame {

        private BoardPanelLevel4 boardPanel;
        private ScorePanelLevel4 scorePanel;
        private Level4.Level4State levelState;
        private int moveCounter;

        public CandyFrameLevel4(CandyGame game) {
            super(game);

            levelState = (Level4.Level4State)game.getState();

            boardPanel = new BoardPanelLevel4(game.getSize(), game.getSize(), CELL_SIZE); // Crea el tablero de size x size con el tamaño de cada cell
            getChildren().add(boardPanel); // Agrega el panel a la ventana
            scorePanel = new ScorePanelLevel4(game.getState().getMaxMoves(), levelState.getBombsLeft(), levelState.getSeconds()); // Crea el panel de score
            getChildren().add(scorePanel); // Agrega el score

            generateTimeBombs(levelState);

            gameListener(boardPanel);

            mouseEventHandler(scorePanel);

        }

        private void generateTimeBombs(Level4.Level4State levelState) {
            int row, col;
            for (int i = 0; i < levelState.getInitialBombs(); i++) {
                row = getRandPos();
                col = getRandPos();
                if (!game.get(row, col).getContent().getProperty().isEmpty())
                    i--;
                else {
                    game.get(row,col).getContent().setProperty(String.valueOf(levelState.getMaxMovements()));
                    levelState.addTimeBomb(game.get(row,col).getContent());
                }
            }
        }

        private int getRandPos() {
            Random rand = new Random();
            return rand.nextInt(Grid.SIZE);
        }

        @Override
        public boolean checkMove(Point2D newPoint) {
            boolean isValid = game().isValidMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
            if(isValid){
                if (!levelState.isLevelStarted())
                    startTimer();
                moveCounter++;
                genNewBomb();
                game().tryMove((int)getLastPoint().getX(), (int)getLastPoint().getY(), (int)newPoint.getX(), (int)newPoint.getY());
                removeExplodedBombs();
            }
            return isValid;
        }

        private void startTimer() {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new TimerTask() {
                        @Override
                        public void run() {
                            scorePanel.updateTimer();
                            levelState.reduceTimer();
                            if(levelState.getSeconds() == 0) {
                                timer.cancel();
                                endScreen("Perdido");
                            }
                        }
                    });
                }
            }, 0, 1000);
            levelState.startTimer();
        }

        private void removeExplodedBombs() {
            Iterator<Element> it = levelState.getTimeBombList().iterator();
            while (it.hasNext()) {
                if (it.next().getProperty().isEmpty()) {
                    levelState.removeTimeBomb();
                    scorePanel.updateBombsLeft(String.valueOf(levelState.getBombsLeft()));
                    levelState.addSeconds(10);
                    scorePanel.addSeconds(10);
                    it.remove();
                }
            }
        }

        private void genNewBomb() {
            if (levelState.getBombsLeft() <= levelState.getInitialBombs() + levelState.getGeneratedBombs())
                return;
            if (moveCounter % levelState.getStep() == 0) {
                int randPos = getRandPos();
                game.get(0, randPos).getContent().setProperty(String.valueOf(levelState.getMaxMovements()));
                levelState.addTimeBomb(game.get(0, randPos).getContent());
            }
        }

    }
