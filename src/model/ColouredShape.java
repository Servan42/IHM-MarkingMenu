package model;

import java.awt.Color;
import java.awt.Shape;

/**
 * Representation of a shape and its color.
 */
public class ColouredShape {
	private Color color;
	private Shape shape;
	private boolean filled;

	public ColouredShape(Color color, Shape shape, boolean fill) {
		this.color = color;
		this.shape = shape;
		filled = fill;
	}

	public Color getColor() {
		return color;
	}

	public Shape getShape() {
		return shape;
	}

	public boolean isFilled() {
		return filled;
	}
}
