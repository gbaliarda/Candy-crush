package game.backend.level;

// Time Limit

import game.backend.GameState3y4;
import game.backend.element.Element;
import game.frontend.level4.CandyFrameLevel4;

public class Level4 extends Level {

    private static final int REQUIRED_SCORE = 10000;

    @Override
    protected Level4State newState() {
        return new Level4State(REQUIRED_SCORE);
    }

    private static class Level4State extends GameState3y4 {
        private int seconds = 15;
        private final long requiredScore;

        private Level4State(long requiredScore) {
            this.requiredScore = requiredScore;
        }

        @Override
        public boolean gameOver() {
            if(playerWon())
                CandyFrameLevel4.getTimer().cancel();
            return getSeconds() <= 0 || playerWon();
        }

        @Override
        public int getScorePanelData(){
            return seconds;
        }

        @Override
        public void updateTimerState() {
            reduceTimer();
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

        @Override
        public int additionalAction(Element e){
            int number = Math.abs(e.getNumber());
            addSeconds(number);
            e.setNumber(null);
            return number;
        }

        @Override
        public boolean removeCondition(Integer number) {
            return number < 0;
        }
    }

}

