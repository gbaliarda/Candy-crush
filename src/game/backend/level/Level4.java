package game.backend.level;

// Time Limit

import game.backend.GameState;

public class Level4 extends Level {

    @Override
    protected GameState newState() {
        return new Level4.Level4State();
    }

    public class Level4State extends GameState3y4 {
        private int seconds = 10;

        @Override
        public boolean gameOver() {
            return getSeconds() <= 0;
        }

        @Override
        public boolean playerWon() {
            return false;
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

