package game.backend.level;

import game.backend.GameState;

// Time Bomb

public class Level3 extends Level {

    @Override
    protected GameState newState() {
        return new Level3State();
    }

    private static class Level3State extends GameState3y4 {
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

        @Override
        public void updateState() {
            getTimeBombList().forEach(bomb -> {
                if (!(bomb.getNumber() == null)) {
                    int newValue = bomb.getNumber() - 1;
                    bomb.setNumber(newValue);
                }
            });
        }

        @Override
        public void checkLose(){
            getTimeBombList().forEach(bomb -> {
                if(bomb.getNumber() != null)
                    if(bomb.getNumber() == 0)
                        playerLost();
            });
        }

        @Override
        public boolean removeCondition(Integer number) {
            return number == null;
        }
    }

}
