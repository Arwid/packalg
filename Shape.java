package packalg;
/**
 * PackAlg
 * By Arwid Bancewicz, October 19, 2009
 */

// Imports
import java.util.Comparator;
import java.util.List;

/**
 * Shape.java - A class representing a 3-dimensional shape with position but 
 * 				empty dimensions.
 * 
 * @author 	Arwid Bancewicz
 * @version 1.1, (09/19/09)
 */
public abstract class Shape {
	
	// Fields
	protected int iiX, iiY, iiZ; // position (default 0)

	/**
	 * Sets the position according to the specified parameters
	 */
	public final void setPosition(int aiX, int aiY, int aiZ) {
		this.iiX = aiX;
		this.iiY = aiY;
		this.iiZ = aiZ;
	}
	
	/**
	 * Sets the position equal to that of the specified shape.
	 */
	public final void matchPositionOf(Shape aoShape) {
		this.setPosition(aoShape.iiX, aoShape.iiY, aoShape.iiZ);
	}
	
	/**
	 * Returns the object's bottom area (along XY plane).
	 */
	public abstract int getArea();

	/**
	 * Returns the object's volume.
	 */
	public abstract int getVolume();
	
	/**
	 * Rotate along the XY plane.
	 */
	public abstract void rotateXY();
	
	/**
	 * Whether this can contain the specified shape at its current rotation.
	 */
	public abstract boolean canContain(Shape aoShape);
	
	/**
	 * Whether this can contain the specified shape at any rotation. The said
	 * shape will be left using the fitted rotation. Otherwise, it's left as 
	 * before.
	 */
	public abstract boolean attemptToContain(Shape aoShape);
	
	/**
	 * Break this object up into unoccupied regions around the specified shape 
	 * (The shape must fit this object).
	 * @param  aoOccupied the occupied region
	 * @return 			  the unoccupied regions
	 */
	public abstract List<Shape> breakUp(Shape aoOccupied);
	
	/**
	 * Full string which includes toString() of super class.
	 */
	public abstract String toFullString();

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[X=" + iiX + ", Y=" + iiY + ", Z=" + iiZ + "]";
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Shape other = (Shape) obj;
		if (iiX != other.iiX || iiY != other.iiY || iiZ != other.iiZ)
			return false;
		return true;
	}
	
	// Other Members

	/**
	 * Compute total volume of specified shapes.
	 * 
	 * @param  aoShapes the shapes to compute
	 * @return 			the total volume of the shapes
	 */
	public static final int getTotalVolume(List<Shape> aoShapes) {
		int liTotalVolume = 0;
		for (Shape loShape : aoShapes) {
			liTotalVolume += loShape.getVolume();
		}
		return liTotalVolume;
	}
}

// Other Classes

/**
 * Dimension comparator for Shape area sort (ascending)
 * @see Comparator
 */
class shapeAreaComparator implements Comparator<Shape> {
	@Override
	public int compare(Shape o1, Shape o2) {
		return (o1.getArea() - o2.getArea());
	}
}

/**
 * Dimension comparator for Shape volume sort (ascending)
 * @see Comparator
 */
class shapeVolumeComparator implements Comparator<Shape> {
	@Override
	public int compare(Shape o1, Shape o2) {
		return (o1.getVolume() - o2.getVolume());
	}
}
