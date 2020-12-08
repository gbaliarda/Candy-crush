package game.backend.level;

// Time Limit

import game.backend.GameState;
import game.backend.element.Element;

import java.util.LinkedList;
import java.util.List;

public class Level4 extends Level {

    @Override
    protected GameState newState() {
        return new Level4.Level4State();
    }

    public class Level4State extends GameState3y4 {
        private int seconds = 60;
        private boolean levelStarted = false;

        @Override
        public boolean gameOver() {
            return getSeconds() <= 0;
        }

        @Override
        public boolean playerWon() {
            return false;
        }

        public void startTimer() {
            levelStarted = true;
        }

        public boolean isLevelStarted() {
            return levelStarted;
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

