package packalg;
/**
 * PackAlg
 * By Arwid Bancewicz, October 19, 2009
 */

// Imports
import java.util.Arrays;
import java.util.List;

/**
 * Box.java - A simple class represents a 3-dimensional rectangular object
 * with position and dimensions.
 * 
 * @author 	Arwid Bancewicz
 * @version 1.2, (09/19/09)
 * @see 	Shape
 */
public class Box extends Shape {

	// Fields
	protected int iiWidth, iiLength, iiHeight; // dimensions

	/**
	 * Class constructor with specified dimensions.
	 */
	public Box(int aiWidth, int aiLength, int aiHeight) {
		super();
		this.iiWidth = aiWidth;
		this.iiLength = aiLength;
		this.iiHeight = aiHeight;
	}
	
	/**
	 * Class constructor with specified dimensions and position.
	 */
	public Box(int aiWidth, int aiLength, int aiHeight, int aiX, int aiY,int aiZ) {
		super.setPosition(aiX, aiY, aiZ);
		this.iiWidth = aiWidth;
		this.iiLength = aiLength;
		this.iiHeight = aiHeight;
	}
	
	/**
	 * @see Shape#getArea()
	 */
	@Override
	public int getArea() {
		return this.iiWidth * this.iiLength;
	}

	/**
	 * @see Shape#getVolume()
	 */
	@Override
	public int getVolume() {
		return this.iiWidth * this.iiLength * this.iiHeight;
	}

	/**
	 * @see Shape#rotateXY()
	 */
	@Override
	public void rotateXY() {
		// swap width and length
		int loWidth = this.iiWidth;
		this.iiWidth = this.iiLength;
		this.iiLength = loWidth;
	}

	/**
	 * @see Shape#canContain(Shape)
	 */
	@Override
	public boolean canContain(Shape aoShape) {
		if (aoShape instanceof Box)
			return canContain((Box) aoShape);
		return false;
	}
	
	/**
	 * Box implementation.
	 * @see Shape#canContain(Shape)
	 */
	public boolean canContain(Box aoBox) {
		return (aoBox.iiWidth <= this.iiWidth
				&& aoBox.iiLength <= this.iiLength && aoBox.iiHeight <= this.iiHeight);
	}
	
	/**
	 * @see Shape#attemptToContain(Shape)
	 */
	@Override
	public boolean attemptToContain(Shape aoShape) {
		if (aoShape instanceof Box)
			return attemptToContain((Box) aoShape);
		return false;
	}
	
	/**
	 * Box implementation.
	 * @see Shape#attemptToContain(Shape)
	 */
	public boolean attemptToContain(Box aoBox) {
		if (canContain(aoBox))
			return true;
		rotateXY();
		if (canContain(aoBox))
			return true;
		rotateXY(); // back to default
		return false;
	}

	/**
	 * @see Shape#breakUp(Shape)
	 */
	@Override
	public List<Shape> breakUp(Shape aoShape) {
		if (aoShape instanceof Box)
			return breakUp((Box) aoShape);
		return null;
	}
	
	/**
	 * Box implementation (Assumes this shape can fit into specified shape).
	 * @see Shape#breakUp(Shape)
	 */
	public List<Shape> breakUp(Box aoBox) {
		Shape loSubBoxX = new Box(aoBox.iiWidth - this.iiWidth, aoBox.iiLength,
				aoBox.iiHeight, this.iiX + this.iiWidth, aoBox.iiY, aoBox.iiZ);
		Shape loSubBoxY = new Box(this.iiWidth, aoBox.iiLength - this.iiLength,
				aoBox.iiHeight, this.iiX, aoBox.iiY + this.iiLength, aoBox.iiZ);
		Shape loSubBoxZ = new Box(this.iiWidth, this.iiLength, aoBox.iiHeight
				- this.iiHeight, this.iiX, aoBox.iiY, aoBox.iiZ + this.iiHeight);
		return Arrays.asList(loSubBoxX, loSubBoxY, loSubBoxZ);
	}
	
	/**
	 * Returns a string formated representation of box's dimensions and position.
	 * @see Shape#toFullString()
	 */
	@Override
	public String toFullString() {
		return "Box [Width=" + iiWidth + ", Length=" + iiLength + ", Height="
				+ iiHeight + ", Position=" + super.toString() + "]";
	}

	/**
	 * Returns a string formated representation of box's dimensions
	 * @see Shape#toString()
	 */
	@Override
	public String toString() {
		return "Box [Width=" + iiWidth + ", Length=" + iiLength + ", Height="
				+ iiHeight + "]";
	}

	/**
	 * Compares this box to the specified object.
	 * @see Shape#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		Box other = (Box) obj;
		if (iiWidth != other.iiWidth || iiLength != other.iiLength
				|| iiHeight != other.iiHeight)
			return false;
		return true;
	}
}
