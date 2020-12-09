package game.backend.level;

import game.backend.GameState;

public class Level1 extends Level {

	private static final int REQUIRED_SCORE = 5000;
	private static final int MAX_MOVES = 15;
	
	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE, MAX_MOVES);
	}
	
	public static class Level1State extends GameState {
		private final long requiredScore;
		
		private Level1State(long requiredScore, int maxMoves) {
			this.requiredScore = requiredScore;
			setMaxMoves(maxMoves);
		}

		@Override
		public long getRequiredScore() {
			return requiredScore;
		}

		@Override
		public boolean gameOver() {
			return playerWon() || getMoves() >= getMaxMoves();
		}
		
		@Override
		public boolean playerWon() {
			return getScore() > requiredScore;
		}
	}

}
