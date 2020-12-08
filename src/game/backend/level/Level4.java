package game.backend.level;

// Time Limit

import game.backend.GameState;
import game.frontend.level4.CandyFrameLevel4;

public class Level4 extends Level {

    @Override
    protected GameState newState() {
        return new Level4.Level4State();
    }

    public class Level4State extends GameState3y4 {
        private int seconds = 15;
        private static final int REQUIRED_SCORE = 10000;

        @Override
        public boolean gameOver() {
            if(playerWon())
                CandyFrameLevel4.getTimer().cancel();
            return getSeconds() <= 0 || playerWon();
        }

        @Override
        public boolean playerWon() {
            return getScore() > REQUIRED_SCORE;
        }

        public int getSeconds(){
            return seconds;
        }

        public void reduceTimer() {
            seconds--;
        }

        public void addSeconds(int seconds) {
            this.seconds += seconds;
        }

    }

}

