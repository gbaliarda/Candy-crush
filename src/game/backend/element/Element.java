package game.backend.element;

import game.backend.move.Direction;

public abstract class Element {

	private String property = "";

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public abstract boolean isMovable();
	
	public abstract String getKey();
	
	public String getFullKey() {
		return getKey();
	}

	public boolean isSolid() {
		return true;
	}
	
	public Direction[] explode() {
		return null;
	}
	
	public long getScore() {
		return 0;
	}
	
}
