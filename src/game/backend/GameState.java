package game.backend;

public abstract class GameState {
	
	private long score = 0;
	private int moves = 0;
	private long maxMoves;

	public void addScore(long value) {
		this.score = this.score + value;
	}
	
	public long getScore(){
		return score;
	}
	
	public void addMove() {
		moves++;
	}
	
	public int getMoves() {
		return moves;
	}

	public void setMaxMoves(long maxMoves){
		this.maxMoves = maxMoves;
	}

	public abstract boolean gameOver();
	
	public abstract boolean playerWon();

	public long getMaxMoves(){
		return maxMoves;
	}

	public abstract long getRequiredScore();

}
