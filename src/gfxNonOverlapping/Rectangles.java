package gfxNonOverlapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A list of rectangular shapes which do not overlap or are outside of the
 * contraints of the canvas. Each shape added and overlaping with any of the
 * current elements or which is outside the canvas will be given new
 * coordinates. The result is a list, containing the new element either with its
 * adjusted coordinates or the old coordinates.
 * <p>
 * Upper right corner is the point of origin.
 * 
 * @author Berthold
 *
 */
public class Rectangles {

	int width, height;
	List<RectArea> rectangles = new ArrayList<>();

	/**
	 * A new object list for a given canvas.
	 * 
	 * @param width
	 *            Width of the constraining canvas.
	 * @param height
	 *            Height of the constraining canvas
	 */
	public Rectangles(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Adds a new rectangle to the list and, if necessary, changes it's
	 * coordinates in a way that it won't overlap other rectangles.
	 * 
	 * @param r
	 *            The {@link RectArea} to be added.
	 * 
	 * @returns The rectangle added with adjusted coordinates or unchanged, if
	 *          it did not overlap any of the rectangles in the current list.
	 * 
	 */
	public RectArea add(RectArea r) {
		rectangles.add(r);
		this.removeOverlaps();
		return this.rectangles.get(this.rectangles.size() - 1);
	}

	/**
	 * Changes the coordinates of the rectangles inside the list, so that they
	 * do not overlap.
	 * <p>
	 * 
	 * This method is invoked each time a new {@link RectArea} object is added
	 * to the list of rectangles of this instance.
	 * <p>
	 * 
	 * As long as the height of the enclosing canvas allows, each rectangle is
	 * stacked below each other, if they overlap. If the lower border of the
	 * canves is reached the recangle is moved to the right of its preceding
	 * rectangle.
	 * <p>
	 * 
	 * <b>Constraints</b>:A Rectangle which is to high or to wide for the screen
	 * to be set below or to the right of its preceding rectangle in the list,
	 * remains unchanged and thus overlap one or more rectangles.....
	 * 
	 * 
	 */
	private void removeOverlaps() {

		RectArea predecessor = rectangles.get(0);
		RectArea current;
		int predecessorsLowerBoundary, predecessorsRightBoundary;

		for (int i = 1; i <= rectangles.size() - 1; i++) {

			predecessorsLowerBoundary = predecessor.getY() + predecessor.getHeight();
			predecessorsRightBoundary = predecessor.getX() + predecessor.getWidth();

			current = rectangles.get(i);

			// Rectangles do overlap?
			if ((current.getY() < predecessorsLowerBoundary) && (current.getX() < predecessorsRightBoundary)) {

				current.setDidOverlap(true);

				// Yes, they overlap. Check if current rectangle can be
				// shifted below it's predecessor.
				if (predecessorsLowerBoundary + current.getHeight() < this.height)
					current.setY(predecessorsLowerBoundary);
				else {

					// Current rectangle can not be shifted below it's
					// predecessor, if possible, shift it to the right
					if (predecessorsRightBoundary + current.getWidth() < this.width)
						current.setX(predecessorsRightBoundary);
					else {

						// Current recangle could not be shifted either below or
						// to the right of it's predecessor. Coordinates remain
						// unchanged. Thus, it will overlap one ore more
						// rectangles in this instances list.
						current.setDidOverlapCouldNotBeShifted(true);
					}
				}
			}
			predecessor = current;
		}
	}

	/**
	 * The current list of rectangles with each rectangles coordinates adjusted
	 * in a way that it does not overlaps another one.
	 * 
	 * @return A list of non overlaping {@link RectArea} objects.
	 * 
	 */
	public List<RectArea> getObjectList() {
		return rectangles;
	}

	// ------------------------------------------------------------------------------------------
	// Some algorithms that may be usefull......

	/**
	 * Checks if two rectangles do overlap.
	 * 
	 * @param r1
	 * @param r2
	 * @return True if rectangles overlap, false if not.
	 */
	public boolean checkColissionRect(RectArea r1, RectArea r2) {
		if ((r1.x + r1.width < r2.x) || (r2.x + r2.width < r1.x) || (r1.y + r1.height < r2.y)
				|| (r2.y + r2.height < r1.y))
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @param r
	 * @return
	 */
	public Coordinates checkIfInsideCanvas(RectArea r) {
		int xOverlap = (r.x + r.width) - this.width;
		int yOverlap = (r.y + r.height) - this.height;

		System.out.println(xOverlap + "  " + yOverlap);

		Coordinates c;
		int newX = r.x;
		int newY = r.y;

		// Rect was inside the canvas.
		if (xOverlap <= 0 && yOverlap <= 0)
			c = new Coordinates(r.x, r.y, false);

		// Rect was outside, calc new coordinates so shift it inside.
		else {
			if (xOverlap > 0)
				newX = r.x - xOverlap - 1;
			if (yOverlap > 0)
				newY = r.y - yOverlap - 1;

			c = new Coordinates(newX, newY, true);
		}
		return c;
	}

}
