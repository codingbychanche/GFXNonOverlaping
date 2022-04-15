package gfxNonOverlapping;

/**
 * A set of coordinates.
 * 
 * @author Berthold
 *
 */
public class Coordinates {
	int x,y;
	boolean changed;
	
	/**
	 * Creates a new set of coordnates.
	 * 
	 * @param x
	 * @param y
	 * @param changed General purpose flag.
	 */
	public Coordinates(int x, int y, boolean changed) {
		super();
		this.x = x;
		this.y = y;
		this.changed = changed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean hasChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	

}
