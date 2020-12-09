package game.backend.level;

import game.backend.GameState;

// Time Bomb

public class Level3 extends Level {

    @Override
    protected GameState newState() {
        return new Level3State();
    }

    public static class Level3State extends GameState3y4 {
        private boolean playerLost;

        private Level3State() {
            this.playerLost = false;
        }

        @Override
        public boolean gameOver() {
            return playerWon() || playerLost;
        }

        @Override
        public boolean playerWon() {
            return getBombsLeft() == 0;
        }

        public void playerLost() {
            playerLost = true;
        }

        @Override
        public long getRequiredScore() {
            return -1;
        }

    }

}
