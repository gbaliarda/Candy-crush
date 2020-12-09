package game.backend.level;

// Time Limit

import game.frontend.level4.CandyFrameLevel4;

public class Level4 extends Level {

    private static final int REQUIRED_SCORE = 10000;

    @Override
    protected Level4State newState() {
        return new Level4.Level4State(REQUIRED_SCORE);
    }

    public class Level4State extends GameState3y4 {
        private int seconds = 15;
        private final long requiredScore;

        public Level4State(long requiredScore) {
            this.requiredScore = requiredScore;
        }

        @Override
        public boolean gameOver() {
            if(playerWon())
                CandyFrameLevel4.getTimer().cancel();
            return getSeconds() <= 0 || playerWon();
        }

        @Override
        public long getRequiredScore() {
            return requiredScore;
        }

        @Override
        public boolean playerWon() {
            return getScore() > requiredScore;
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

