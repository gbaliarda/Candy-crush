package game.frontend.level4;

import game.backend.CandyGame;
import game.backend.element.Element;
import game.backend.level.Level4;
import game.frontend.level3.CandyFrameLevel3;
import game.frontend.level3.BoardPanelLevel3;
import game.frontend.level3.ScorePanelLevel3;
import javafx.application.Platform;
import javafx.geometry.Point2D;

import java.util.Timer;
import java.util.TimerTask;

public class CandyFrameLevel4 extends CandyFrameLevel3 {

        private final Level4.Level4State levelState;
        private static Timer timer = new Timer();
        private static boolean timerRunning = false;

        public CandyFrameLevel4(CandyGame game) {
            super(game);
            levelState = (Level4.Level4State)getLevelState();
        }

        @Override
        public BoardPanelLevel3 setBoardPanel(int sizeX, int sizeY, int cellSize) {
            return new BoardPanelLevel4(sizeX, sizeY, cellSize);
        }

        public static Timer getTimer() {
            return timer;
        }

        public static void setTimer(Timer timer) {
            CandyFrameLevel4.timer = timer;
        }

        public static boolean isTimerRunning() {
            return timerRunning;
        }

        public static void cancelTimer() {
            timerRunning = false;
        }

        @Override
        public ScorePanelLevel3 setScorePanel() {
            return new ScorePanelLevel4(getLevelState().getBombsLeft(), ((Level4.Level4State)getLevelState()).getSeconds(), game.getState().getRequiredScore());
        }

        @Override
        public void doOnExplosion(Element e) {
            if (e.getNumber() != null)
                e.setNumber(e.getNumber() * -1);
        }

        @Override
        public boolean makeMove(Point2D newPoint) {
            boolean isValid = super.makeMove(newPoint);
            if(isValid && !timerRunning)
                startTimer();
            return isValid;
        }

        private void startTimer() {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new TimerTask() {
                        @Override
                        public void run() {
                            ((ScorePanelLevel4)getScorePanel()).updateTimer();
                            levelState.reduceTimer();
                            if(levelState.getSeconds() == 0) {
                                timer.cancel();
                                endScreen("Perdido");
                            }
                        }
                    });
                }
            }, 1000, 1000);
            timerRunning = true;
        }

        @Override
        public void updateScorePanelAux(int seconds) {
            ((ScorePanelLevel4)getScorePanel()).addSeconds(seconds);
        }

    }
