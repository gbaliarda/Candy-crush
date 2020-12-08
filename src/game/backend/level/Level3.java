package game.backend.level;

import game.backend.GameState;
import game.backend.element.Element;

import java.util.LinkedList;
import java.util.List;

// Time Bomb

public class Level3 extends Level {

    @Override
    protected GameState newState() {
        return new Level3.Level3State();
    }

    public class Level3State extends GameState3y4 {
        private boolean playerLost = false;

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


    }

}
