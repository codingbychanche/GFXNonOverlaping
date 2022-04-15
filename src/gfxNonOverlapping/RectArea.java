package gfxNonOverlapping;

/**
 * A rectangular area.
 * 
 * @author Berthold
 *
 */
public class RectArea implements Comparable<RectArea> {

	int x, y, width, height;
	boolean didOverlap, didOverlapCouldNotBeShifted;

	public RectArea(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.didOverlap=false;
		this.didOverlapCouldNotBeShifted=false;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean didOverlap() {
		return didOverlap;
	}

	public void setDidOverlap(boolean didOverlap) {
		this.didOverlap = didOverlap;
	}

	public boolean didOverlapCouldNotBeShifted() {
		return didOverlapCouldNotBeShifted;
	}

	public void setDidOverlapCouldNotBeShifted(boolean didOverlapCouldNotBeShifted) {
		this.didOverlapCouldNotBeShifted = didOverlapCouldNotBeShifted;
	}

	/**
	 * Sorts a list by y- pos of this rectangular area in ascending order.
	 * 
	 */
	@Override
	public int compareTo(RectArea o) {

		if (o.getY()> this.y)
			return -1;
		else
			if (o.getY()<this.y)
				return 1;
		return 0;
	}

}
