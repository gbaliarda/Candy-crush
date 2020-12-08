package game.frontend.level4;

import game.backend.CandyGame;
import game.backend.level.Level4;
import game.frontend.CandyFrameLevel3y4;
import game.frontend.level3.ScorePanelLevel3;
import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;

public class CandyFrameLevel4 extends CandyFrameLevel3y4 {

        private final Level4.Level4State levelState;
        private static Timer timer = new Timer();
        private static boolean timerRunning = false;

        public CandyFrameLevel4(CandyGame game) {
            super(game);
            levelState = (Level4.Level4State)getLevelState();
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
            return new ScorePanelLevel4(getLevelState().getBombsLeft(), ((Level4.Level4State)getLevelState()).getSeconds());
        }

        @Override
        public void actionIfValid(){
            if (!CandyFrameLevel4.timerRunning)
                startTimer();
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
            }, 0, 1000);
            timerRunning = true;
        }

        public void additionalAction(int number){
            levelState.addSeconds(10);
            ((ScorePanelLevel4)getScorePanel()).addSeconds(10);
        }

    }
